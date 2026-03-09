package com.hbm.doors.registry;

import com.hbm.doors.HbmDoorsMod;
import com.hbm.doors.block.entity.DoorBlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlockEntities {
    public static BlockEntityType<DoorBlockEntity> DOOR_BLOCK_ENTITY;

    public static void register() {
        DOOR_BLOCK_ENTITY = Registry.register(
            Registries.BLOCK_ENTITY_TYPE,
            new Identifier(HbmDoorsMod.MOD_ID, "door_block_entity"),
            BlockEntityType.Builder.create(
                DoorBlockEntity::new,
                ModBlocks.LARGE_VEHICLE_DOOR,
                ModBlocks.ROUND_AIRLOCK_DOOR,
                ModBlocks.TRANSITION_SEAL,
                ModBlocks.FIRE_DOOR,
                ModBlocks.SLIDING_BLAST_DOOR,
                ModBlocks.SLIDING_SEAL_DOOR,
                ModBlocks.SECURE_ACCESS_DOOR,
                ModBlocks.QE_SLIDING_DOOR,
                ModBlocks.QE_CONTAINMENT_DOOR,
                ModBlocks.WATER_DOOR,
                ModBlocks.SILO_HATCH,
                ModBlocks.SILO_HATCH_LARGE,
                ModBlocks.VAULT_DOOR
            ).build(null)
        );
    }
}
