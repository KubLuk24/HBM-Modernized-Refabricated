package com.hbm.doors.door;

import com.hbm.doors.registry.ModSounds;
import net.minecraft.sound.SoundEvent;
import org.jetbrains.annotations.Nullable;

public enum DoorType {
    LARGE_VEHICLE_DOOR("large_vehicle_door", 60, new String[]{"frame", "door"}),
    ROUND_AIRLOCK_DOOR("round_airlock_door", 60, new String[]{"frame", "door"}),
    TRANSITION_SEAL("transition_seal", 480, new String[]{
        "frame", "door", "Cylinder.001", "Cylinder.003", "Cylinder.005", "Cube.006",
        "Cylinder.007", "Cylinder.008", "Circle", "Cylinder.009", "Cylinder.010",
        "Cylinder.011", "door.005", "door.002", "door.008", "ring.001",
        "door.003", "door.004", "ring.002", "door.006"
    }),
    FIRE_DOOR("fire_door", 160, new String[]{"frame", "door"}),
    SLIDING_BLAST_DOOR("sliding_blast_door", 24, new String[]{"DoorFrame", "DoorLeft", "DoorRight", "Window", "DoorCircleLeft", "DoorCircleRight"}),
    SLIDING_SEAL_DOOR("sliding_seal_door", 20, new String[]{"frame", "door"}),
    SECURE_ACCESS_DOOR("secure_access_door", 120, new String[]{"frame", "door"}),
    QE_SLIDING_DOOR("qe_sliding_door", 10, new String[]{"frame", "door"}),
    QE_CONTAINMENT_DOOR("qe_containment_door", 20, new String[]{"frame", "door"}),
    WATER_DOOR("water_door", 20, new String[]{"frame", "door", "spinny_upper", "spinny_lower"}),
    SILO_HATCH("silo_hatch", 60, new String[]{"frame", "door"}),
    SILO_HATCH_LARGE("silo_hatch_large", 60, new String[]{"frame", "door"}),
    VAULT_DOOR("vault_door", 40, new String[]{"frame", "door", "Label"});

    private final String id;
    private final int openTime;
    private final String[] partNames;

    DoorType(String id, int openTime, String[] partNames) {
        this.id = id;
        this.openTime = openTime;
        this.partNames = partNames;
    }

    public String getId() { return id; }
    public int getOpenTime() { return openTime; }
    public String[] getPartNames() { return partNames; }

    public static @Nullable DoorType fromId(String id) {
        for (DoorType type : values()) {
            if (type.id.equals(id)) return type;
        }
        return null;
    }

    private static float normTime(float openTicks, int openTime) {
        return Math.min(1.0f, openTicks / openTime);
    }

    public void getTranslation(String partName, float openTicks, float[] trans) {
        float t = normTime(openTicks, openTime);
        switch (this) {
            case LARGE_VEHICLE_DOOR -> {
                if ("door".equals(partName)) { trans[0]=0; trans[1] = 3.0f*t; trans[2]=0; }
                else { trans[0]=0; trans[1]=0; trans[2]=0; }
            }
            case ROUND_AIRLOCK_DOOR -> {
                if ("door".equals(partName)) { trans[0]=0; trans[1] = 1.5f*t; trans[2]=0; }
                else { trans[0]=0; trans[1]=0; trans[2]=0; }
            }
            case TRANSITION_SEAL -> {
                if ("frame".equals(partName)) { trans[0]=0; trans[1]=0; trans[2]=0; }
                else { trans[0]=0; trans[1] = 3.5f*t; trans[2]=0; }
            }
            case FIRE_DOOR -> {
                if ("frame".equals(partName)) { trans[0]=0; trans[1]=0; trans[2]=0; }
                else { trans[0]=0; trans[1] = 3.0f*t; trans[2]=0; }
            }
            case SLIDING_BLAST_DOOR -> {
                if ("DoorFrame".equals(partName)) { trans[0]=0; trans[1]=0; trans[2]=0; }
                else if ("DoorLeft".equals(partName)) { trans[0] = 2.125f*t; trans[1]=0; trans[2]=0; }
                else if ("DoorRight".equals(partName)) { trans[0] = -2.125f*t; trans[1]=0; trans[2]=0; }
                else { trans[0]=0; trans[1]=0; trans[2]=0; }
            }
            case SLIDING_SEAL_DOOR -> {
                if ("door".equals(partName)) { trans[0]=0; trans[1]=0; trans[2]=t; }
                else { trans[0]=0; trans[1]=0; trans[2]=0; }
            }
            case SECURE_ACCESS_DOOR -> {
                if ("door".equals(partName)) { trans[0]=0; trans[1] = 3.5f*t; trans[2]=0; }
                else { trans[0]=0; trans[1]=0; trans[2]=0; }
            }
            case QE_SLIDING_DOOR -> {
                if ("door".equals(partName)) { trans[0]=0; trans[1]=0; trans[2] = 0.99f*t; }
                else { trans[0]=0; trans[1]=0; trans[2]=0; }
            }
            case QE_CONTAINMENT_DOOR -> {
                if ("door".equals(partName)) { trans[0]=0; trans[1] = t; trans[2]=0; }
                else { trans[0]=0; trans[1]=0; trans[2]=0; }
            }
            case WATER_DOOR -> {
                trans[0]=0; trans[1]=0; trans[2]=0;
            }
            case SILO_HATCH -> {
                if ("door".equals(partName)) { trans[0] = 2.5f*t; trans[1]=0; trans[2]=0; }
                else { trans[0]=0; trans[1]=0; trans[2]=0; }
            }
            case SILO_HATCH_LARGE -> {
                if ("door".equals(partName)) { trans[0] = 3.5f*t; trans[1]=0; trans[2]=0; }
                else { trans[0]=0; trans[1]=0; trans[2]=0; }
            }
            case VAULT_DOOR -> {
                if ("door".equals(partName) || "Label".equals(partName)) {
                    float angle = (float)Math.toRadians(90f * t);
                    float radius = 0.5f;
                    trans[0] = radius * (float)Math.sin(angle);
                    trans[1] = 0;
                    trans[2] = radius * (1.0f - (float)Math.cos(angle));
                } else { trans[0]=0; trans[1]=0; trans[2]=0; }
            }
            default -> { trans[0]=0; trans[1]=0; trans[2]=0; }
        }
    }

    public void getRotation(String partName, float openTicks, float[] rot) {
        float t = normTime(openTicks, openTime);
        rot[0]=0; rot[1]=1; rot[2]=0; rot[3]=0;
        switch (this) {
            case WATER_DOOR -> {
                if ("spinny_upper".equals(partName)) {
                    rot[0]=0; rot[1]=0; rot[2]=1; rot[3] = 360f * t;
                } else if ("spinny_lower".equals(partName)) {
                    rot[0]=0; rot[1]=0; rot[2]=1; rot[3] = -360f * t;
                }
            }
            case SILO_HATCH, SILO_HATCH_LARGE -> {
                if ("door".equals(partName)) {
                    rot[0]=0; rot[1]=0; rot[2]=1; rot[3] = -90f * t;
                }
            }
            case VAULT_DOOR -> {
                if ("door".equals(partName) || "Label".equals(partName)) {
                    rot[0]=0; rot[1]=1; rot[2]=0; rot[3] = 90f * t;
                }
            }
            default -> {}
        }
    }

    public void getOrigin(String partName, float[] orig) {
        orig[0]=0; orig[1]=0; orig[2]=0;
        switch (this) {
            case WATER_DOOR -> {
                if ("spinny_upper".equals(partName) || "spinny_lower".equals(partName)) {
                    orig[0]=0; orig[1]=1.0f; orig[2]=0;
                }
            }
            case SILO_HATCH, SILO_HATCH_LARGE -> {
                if ("door".equals(partName)) {
                    orig[0]=-2.0f; orig[1]=0; orig[2]=0;
                }
            }
            case VAULT_DOOR -> {
                if ("door".equals(partName) || "Label".equals(partName)) {
                    orig[0]=0; orig[1]=0; orig[2]=0.5f;
                }
            }
            default -> {}
        }
    }

    public @Nullable SoundEvent getOpenSoundStart() {
        return switch (this) {
            case TRANSITION_SEAL -> ModSounds.TRANSITION_SEAL_OPEN;
            default -> null;
        };
    }

    public @Nullable SoundEvent getOpenSoundEnd() {
        return switch (this) {
            case LARGE_VEHICLE_DOOR, ROUND_AIRLOCK_DOOR, SECURE_ACCESS_DOOR -> ModSounds.GARAGE_STOP;
            case SLIDING_SEAL_DOOR -> ModSounds.METAL_STOP_1;
            case QE_SLIDING_DOOR -> ModSounds.SLIDING_DOOR_OPENED;
            case QE_CONTAINMENT_DOOR -> ModSounds.SLIDING_DOOR_OPENED;
            case FIRE_DOOR -> ModSounds.WGH_STOP;
            case SILO_HATCH, SILO_HATCH_LARGE -> ModSounds.DOOR_WGH_BIG_STOP;
            default -> null;
        };
    }

    public @Nullable SoundEvent getOpenSoundLoop() {
        return switch (this) {
            case LARGE_VEHICLE_DOOR, ROUND_AIRLOCK_DOOR, SECURE_ACCESS_DOOR -> ModSounds.GARAGE_MOVE;
            case QE_SLIDING_DOOR, QE_CONTAINMENT_DOOR -> ModSounds.SLIDING_DOOR_OPENING;
            case FIRE_DOOR -> ModSounds.WGH_START;
            case SILO_HATCH, SILO_HATCH_LARGE -> ModSounds.DOOR_WGH_BIG_START;
            default -> null;
        };
    }

    public @Nullable SoundEvent getCloseSoundStart() {
        return switch (this) {
            case SLIDING_SEAL_DOOR -> ModSounds.DOOR_MOVE_2;
            default -> getOpenSoundStart();
        };
    }

    public @Nullable SoundEvent getCloseSoundEnd() {
        return switch (this) {
            case QE_SLIDING_DOOR -> ModSounds.SLIDING_DOOR_SHUT;
            case TRANSITION_SEAL -> ModSounds.TRANSITION_SEAL_CLOSE;
            default -> getOpenSoundEnd();
        };
    }

    public @Nullable SoundEvent getCloseSoundLoop() {
        return getOpenSoundLoop();
    }
}
