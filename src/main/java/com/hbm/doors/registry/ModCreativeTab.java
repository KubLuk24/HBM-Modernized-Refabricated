package com.hbm.doors.registry;

import com.hbm.doors.HbmDoorsMod;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModCreativeTab {
    public static ItemGroup HBM_DOORS_TAB;

    public static void register() {
        HBM_DOORS_TAB = Registry.register(Registries.ITEM_GROUP,
            new Identifier(HbmDoorsMod.MOD_ID, "doors"),
            FabricItemGroup.builder()
                .icon(() -> new ItemStack(ModBlocks.LARGE_VEHICLE_DOOR))
                .displayName(Text.translatable("itemgroup.hbm_doors.doors"))
                .entries((context, entries) -> {
                    entries.add(ModBlocks.LARGE_VEHICLE_DOOR);
                    entries.add(ModBlocks.ROUND_AIRLOCK_DOOR);
                    entries.add(ModBlocks.TRANSITION_SEAL);
                    entries.add(ModBlocks.FIRE_DOOR);
                    entries.add(ModBlocks.SLIDING_BLAST_DOOR);
                    entries.add(ModBlocks.SLIDING_SEAL_DOOR);
                    entries.add(ModBlocks.SECURE_ACCESS_DOOR);
                    entries.add(ModBlocks.QE_SLIDING_DOOR);
                    entries.add(ModBlocks.QE_CONTAINMENT_DOOR);
                    entries.add(ModBlocks.WATER_DOOR);
                    entries.add(ModBlocks.SILO_HATCH);
                    entries.add(ModBlocks.SILO_HATCH_LARGE);
                    entries.add(ModBlocks.VAULT_DOOR);
                })
                .build()
        );
    }
}
