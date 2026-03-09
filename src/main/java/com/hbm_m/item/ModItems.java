package com.hbm_m.item;

import static com.hbm_m.lib.RefStrings.MODID;

import com.hbm_m.block.ModBlocks;
import com.hbm_m.multiblock.DoorBlockItem;

import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

// Registers door-related items using DeferredRegister.
public class ModItems {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, MODID);

    public static final RegistryObject<Item> LARGE_VEHICLE_DOOR = ITEMS.register("large_vehicle_door",
        () -> new DoorBlockItem(ModBlocks.LARGE_VEHICLE_DOOR.get(), new Item.Properties()));

    public static final RegistryObject<Item> ROUND_AIRLOCK_DOOR = ITEMS.register("round_airlock_door",
        () -> new DoorBlockItem(ModBlocks.ROUND_AIRLOCK_DOOR.get(), new Item.Properties()));

    public static final RegistryObject<Item> TRANSITION_SEAL = ITEMS.register("transition_seal",
        () -> new DoorBlockItem(ModBlocks.TRANSITION_SEAL.get(), new Item.Properties()));

    public static final RegistryObject<Item> SILO_HATCH = ITEMS.register("silo_hatch",
        () -> new DoorBlockItem(ModBlocks.SILO_HATCH.get(), new Item.Properties()));

    public static final RegistryObject<Item> SILO_HATCH_LARGE = ITEMS.register("silo_hatch_large",
        () -> new DoorBlockItem(ModBlocks.SILO_HATCH_LARGE.get(), new Item.Properties()));

    public static final RegistryObject<Item> QE_CONTAINMENT = ITEMS.register("qe_containment_door",
        () -> new DoorBlockItem(ModBlocks.QE_CONTAINMENT.get(), new Item.Properties()));

    public static final RegistryObject<Item> WATER_DOOR = ITEMS.register("water_door",
        () -> new DoorBlockItem(ModBlocks.WATER_DOOR.get(), new Item.Properties()));

    public static final RegistryObject<Item> FIRE_DOOR = ITEMS.register("fire_door",
        () -> new DoorBlockItem(ModBlocks.FIRE_DOOR.get(), new Item.Properties()));

    public static final RegistryObject<Item> SLIDE_DOOR = ITEMS.register("sliding_blast_door",
        () -> new DoorBlockItem(ModBlocks.SLIDE_DOOR.get(), new Item.Properties()));

    public static final RegistryObject<Item> SLIDING_SEAL_DOOR = ITEMS.register("sliding_seal_door",
        () -> new DoorBlockItem(ModBlocks.SLIDING_SEAL_DOOR.get(), new Item.Properties()));

    public static final RegistryObject<Item> SECURE_ACCESS_DOOR = ITEMS.register("secure_access_door",
        () -> new DoorBlockItem(ModBlocks.SECURE_ACCESS_DOOR.get(), new Item.Properties()));

    public static final RegistryObject<Item> QE_SLIDING = ITEMS.register("qe_sliding_door",
        () -> new DoorBlockItem(ModBlocks.QE_SLIDING.get(), new Item.Properties()));

    public static final RegistryObject<Item> VAULT_DOOR = ITEMS.register("vault_door",
        () -> new DoorBlockItem(ModBlocks.VAULT_DOOR.get(), new Item.Properties()));
}
