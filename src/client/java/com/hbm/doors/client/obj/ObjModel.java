package com.hbm.doors.client.obj;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ObjModel {
    private final Map<String, ObjPart> parts;

    public ObjModel(Map<String, ObjPart> parts) {
        this.parts = Collections.unmodifiableMap(new HashMap<>(parts));
    }

    public ObjPart getPart(String name) {
        return parts.get(name);
    }

    public Map<String, ObjPart> getParts() {
        return parts;
    }

    public boolean hasPart(String name) {
        return parts.containsKey(name);
    }
}
