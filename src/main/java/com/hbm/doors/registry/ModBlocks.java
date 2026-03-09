package com.hbm.doors.registry;

import com.hbm.doors.HbmDoorsMod;
import com.hbm.doors.block.CustomDoorBlock;
import com.hbm.doors.block.SimpleDoorBlock;
import com.hbm.doors.door.DoorType;
import net.minecraft.block.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlocks {
    public static CustomDoorBlock LARGE_VEHICLE_DOOR;
    public static CustomDoorBlock ROUND_AIRLOCK_DOOR;
    public static CustomDoorBlock TRANSITION_SEAL;
    public static CustomDoorBlock FIRE_DOOR;
    public static CustomDoorBlock SLIDING_BLAST_DOOR;
    public static CustomDoorBlock SLIDING_SEAL_DOOR;
    public static CustomDoorBlock SECURE_ACCESS_DOOR;
    public static CustomDoorBlock QE_SLIDING_DOOR;
    public static CustomDoorBlock QE_CONTAINMENT_DOOR;
    public static CustomDoorBlock WATER_DOOR;
    public static CustomDoorBlock SILO_HATCH;
    public static CustomDoorBlock SILO_HATCH_LARGE;
    public static CustomDoorBlock VAULT_DOOR;

    // Simple vanilla-style doors
    public static SimpleDoorBlock METAL_DOOR;
    public static SimpleDoorBlock DOOR_BUNKER;
    public static SimpleDoorBlock DOOR_OFFICE;

    private static SimpleDoorBlock regSimple(String name) {
        SimpleDoorBlock block = new SimpleDoorBlock(AbstractBlock.Settings.create()
            .strength(3.0f, 6.0f)
            .sounds(BlockSoundGroup.METAL)
            .requiresTool(),
            BlockSetType.IRON);
        Registry.register(Registries.BLOCK, new Identifier(HbmDoorsMod.MOD_ID, name), block);
        return block;
    }

    private static CustomDoorBlock regCustom(String name, DoorType type) {
        CustomDoorBlock block = new CustomDoorBlock(type, AbstractBlock.Settings.create()
            .strength(5.0f, 1200.0f)
            .sounds(BlockSoundGroup.METAL)
            .nonOpaque()
            .requiresTool());
        Registry.register(Registries.BLOCK, new Identifier(HbmDoorsMod.MOD_ID, name), block);
        return block;
    }

    public static void register() {
        LARGE_VEHICLE_DOOR = regCustom("large_vehicle_door", DoorType.LARGE_VEHICLE_DOOR);
        ROUND_AIRLOCK_DOOR = regCustom("round_airlock_door", DoorType.ROUND_AIRLOCK_DOOR);
        TRANSITION_SEAL = regCustom("transition_seal", DoorType.TRANSITION_SEAL);
        FIRE_DOOR = regCustom("fire_door", DoorType.FIRE_DOOR);
        SLIDING_BLAST_DOOR = regCustom("sliding_blast_door", DoorType.SLIDING_BLAST_DOOR);
        SLIDING_SEAL_DOOR = regCustom("sliding_seal_door", DoorType.SLIDING_SEAL_DOOR);
        SECURE_ACCESS_DOOR = regCustom("secure_access_door", DoorType.SECURE_ACCESS_DOOR);
        QE_SLIDING_DOOR = regCustom("qe_sliding_door", DoorType.QE_SLIDING_DOOR);
        QE_CONTAINMENT_DOOR = regCustom("qe_containment_door", DoorType.QE_CONTAINMENT_DOOR);
        WATER_DOOR = regCustom("water_door", DoorType.WATER_DOOR);
        SILO_HATCH = regCustom("silo_hatch", DoorType.SILO_HATCH);
        SILO_HATCH_LARGE = regCustom("silo_hatch_large", DoorType.SILO_HATCH_LARGE);
        VAULT_DOOR = regCustom("vault_door", DoorType.VAULT_DOOR);

        METAL_DOOR = regSimple("metal_door");
        DOOR_BUNKER = regSimple("door_bunker");
        DOOR_OFFICE = regSimple("door_office");
    }
}
