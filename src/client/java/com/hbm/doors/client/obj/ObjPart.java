package com.hbm.doors.client.obj;

import java.util.List;

public class ObjPart {
    public final String name;
    public final List<float[]> vertices;
    public final List<float[]> normals;
    public final List<float[]> texCoords;
    public final List<int[][]> faces;
    public final String material;

    public ObjPart(String name, List<float[]> vertices, List<float[]> normals,
                   List<float[]> texCoords, List<int[][]> faces, String material) {
        this.name = name;
        this.vertices = vertices;
        this.normals = normals;
        this.texCoords = texCoords;
        this.faces = faces;
        this.material = material;
    }
}
