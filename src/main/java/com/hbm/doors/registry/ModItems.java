package com.hbm.doors.registry;

import com.hbm.doors.HbmDoorsMod;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {
    private static BlockItem regItem(String name, Block block) {
        BlockItem item = new BlockItem(block, new Item.Settings());
        Registry.register(Registries.ITEM, new Identifier(HbmDoorsMod.MOD_ID, name), item);
        return item;
    }

    public static void register() {
        regItem("large_vehicle_door", ModBlocks.LARGE_VEHICLE_DOOR);
        regItem("round_airlock_door", ModBlocks.ROUND_AIRLOCK_DOOR);
        regItem("transition_seal", ModBlocks.TRANSITION_SEAL);
        regItem("fire_door", ModBlocks.FIRE_DOOR);
        regItem("sliding_blast_door", ModBlocks.SLIDING_BLAST_DOOR);
        regItem("sliding_seal_door", ModBlocks.SLIDING_SEAL_DOOR);
        regItem("secure_access_door", ModBlocks.SECURE_ACCESS_DOOR);
        regItem("qe_sliding_door", ModBlocks.QE_SLIDING_DOOR);
        regItem("qe_containment_door", ModBlocks.QE_CONTAINMENT_DOOR);
        regItem("water_door", ModBlocks.WATER_DOOR);
        regItem("silo_hatch", ModBlocks.SILO_HATCH);
        regItem("silo_hatch_large", ModBlocks.SILO_HATCH_LARGE);
        regItem("vault_door", ModBlocks.VAULT_DOOR);
        regItem("metal_door", ModBlocks.METAL_DOOR);
        regItem("door_bunker", ModBlocks.DOOR_BUNKER);
        regItem("door_office", ModBlocks.DOOR_OFFICE);
    }
}
