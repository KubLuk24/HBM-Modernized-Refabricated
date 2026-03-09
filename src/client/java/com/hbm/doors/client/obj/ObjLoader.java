package com.hbm.doors.client.obj;

import com.hbm.doors.HbmDoorsMod;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class ObjLoader {
    private static final Map<Identifier, ObjModel> CACHE = new HashMap<>();

    public static void clearCache() {
        CACHE.clear();
    }

    public static ObjModel load(ResourceManager manager, Identifier id) {
        return CACHE.computeIfAbsent(id, k -> {
            try {
                return parse(manager, k);
            } catch (IOException e) {
                HbmDoorsMod.LOGGER.error("Failed to load OBJ model: {}", k, e);
                return new ObjModel(Collections.emptyMap());
            }
        });
    }

    private static ObjModel parse(ResourceManager manager, Identifier id) throws IOException {
        List<float[]> allVerts = new ArrayList<>();
        List<float[]> allNormals = new ArrayList<>();
        List<float[]> allTexCoords = new ArrayList<>();
        Map<String, ObjPart> parts = new LinkedHashMap<>();

        String currentGroup = "default";
        List<int[][]> currentFaces = new ArrayList<>();
        String currentMat = "";

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(manager.getResource(id).orElseThrow().getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty() || line.startsWith("#")) continue;

                String[] tokens = line.split("\\s+");
                switch (tokens[0]) {
                    case "v" -> allVerts.add(new float[]{
                        Float.parseFloat(tokens[1]),
                        Float.parseFloat(tokens[2]),
                        Float.parseFloat(tokens[3])
                    });
                    case "vn" -> allNormals.add(new float[]{
                        Float.parseFloat(tokens[1]),
                        Float.parseFloat(tokens[2]),
                        Float.parseFloat(tokens[3])
                    });
                    case "vt" -> allTexCoords.add(new float[]{
                        Float.parseFloat(tokens[1]),
                        tokens.length > 2 ? Float.parseFloat(tokens[2]) : 0f
                    });
                    case "usemtl" -> currentMat = tokens.length > 1 ? tokens[1] : "";
                    case "o", "g" -> {
                        if (!currentFaces.isEmpty()) {
                            ObjPart part = buildPart(currentGroup, allVerts, allNormals, allTexCoords, currentFaces, currentMat);
                            parts.put(currentGroup, part);
                        }
                        currentGroup = tokens.length > 1 ? tokens[1] : "default";
                        currentFaces = new ArrayList<>();
                    }
                    case "f" -> {
                        int[][] faceVerts = new int[tokens.length - 1][];
                        for (int i = 1; i < tokens.length; i++) {
                            String[] parts2 = tokens[i].split("/", -1);
                            int v = Integer.parseInt(parts2[0]);
                            int vt = parts2.length > 1 && !parts2[1].isEmpty() ? Integer.parseInt(parts2[1]) : 0;
                            int vn = parts2.length > 2 && !parts2[2].isEmpty() ? Integer.parseInt(parts2[2]) : 0;
                            faceVerts[i - 1] = new int[]{v, vt, vn};
                        }
                        currentFaces.add(faceVerts);
                    }
                }
            }
        }
        if (!currentFaces.isEmpty()) {
            ObjPart part = buildPart(currentGroup, allVerts, allNormals, allTexCoords, currentFaces, currentMat);
            parts.put(currentGroup, part);
        }
        return new ObjModel(parts);
    }

    private static ObjPart buildPart(String name, List<float[]> allVerts, List<float[]> allNormals,
                                     List<float[]> allTexCoords, List<int[][]> faces, String mat) {
        return new ObjPart(name,
            Collections.unmodifiableList(new ArrayList<>(allVerts)),
            Collections.unmodifiableList(new ArrayList<>(allNormals)),
            Collections.unmodifiableList(new ArrayList<>(allTexCoords)),
            Collections.unmodifiableList(new ArrayList<>(faces)),
            mat);
    }
}
