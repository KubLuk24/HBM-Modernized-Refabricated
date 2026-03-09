package com.hbm_m.block;

import java.util.function.Supplier;

import com.hbm_m.block.decorations.DoorBlock;
import com.hbm_m.block.machines.UniversalMachinePartBlock;
import com.hbm_m.item.ModItems;
import com.hbm_m.lib.RefStrings;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, RefStrings.MODID);

    //---------------------------<PHANTOM BLOCK>-------------------------------

    public static final RegistryObject<Block> UNIVERSAL_MACHINE_PART = registerBlockWithoutItem("universal_machine_part",
            () -> new UniversalMachinePartBlock(
                    BlockBehaviour.Properties.of()
                            .strength(-1.0F, 3600000.0F)
                            .noLootTable()
                            .noOcclusion()
                            .isViewBlocking((state, level, pos) -> false)
            ));

    //---------------------------<ДВЕРИ>-------------------------------------

    public static final RegistryObject<DoorBlock> LARGE_VEHICLE_DOOR = registerBlockWithoutItem("large_vehicle_door",
            () -> new DoorBlock(
                    BlockBehaviour.Properties.of()
                            .strength(10.0F, 1000.0F)
                            .requiresCorrectToolForDrops()
                            .sound(SoundType.METAL)
                            .noOcclusion()
                            .isViewBlocking((state, level, pos) -> false),
                    "large_vehicle_door"
            ));

    public static final RegistryObject<DoorBlock> ROUND_AIRLOCK_DOOR = registerBlockWithoutItem("round_airlock_door",
            () -> new DoorBlock(
                    BlockBehaviour.Properties.of()
                            .strength(10.0F, 1000.0F)
                            .requiresCorrectToolForDrops()
                            .sound(SoundType.METAL)
                            .noOcclusion()
                            .isViewBlocking((state, level, pos) -> false),
                    "round_airlock_door"
            ));

    public static final RegistryObject<Block> TRANSITION_SEAL = registerBlockWithoutItem("transition_seal",
            () -> new DoorBlock(
                    BlockBehaviour.Properties.of()
                            .strength(10.0F, 1000.0F)
                            .requiresCorrectToolForDrops()
                            .sound(SoundType.METAL)
                            .noOcclusion()
                            .isViewBlocking((state, level, pos) -> false),
                    "transition_seal"
            ));

    public static final RegistryObject<Block> FIRE_DOOR = registerBlockWithoutItem("fire_door",
            () -> new DoorBlock(
                    BlockBehaviour.Properties.of()
                            .strength(10.0F, 1000.0F)
                            .requiresCorrectToolForDrops()
                            .sound(SoundType.METAL)
                            .noOcclusion()
                            .isViewBlocking((state, level, pos) -> false),
                    "fire_door"
            ));

    public static final RegistryObject<Block> SLIDE_DOOR = registerBlockWithoutItem("sliding_blast_door",
            () -> new DoorBlock(
                    BlockBehaviour.Properties.of()
                            .strength(10.0F, 1000.0F)
                            .requiresCorrectToolForDrops()
                            .sound(SoundType.METAL)
                            .noOcclusion()
                            .isViewBlocking((state, level, pos) -> false),
                    "sliding_blast_door"
            ));

    public static final RegistryObject<Block> SLIDING_SEAL_DOOR = registerBlockWithoutItem("sliding_seal_door",
            () -> new DoorBlock(
                    BlockBehaviour.Properties.of()
                            .strength(10.0F, 1000.0F)
                            .requiresCorrectToolForDrops()
                            .sound(SoundType.METAL)
                            .noOcclusion()
                            .isViewBlocking((state, level, pos) -> false),
                    "sliding_seal_door"
            ));

    public static final RegistryObject<Block> SECURE_ACCESS_DOOR = registerBlockWithoutItem("secure_access_door",
            () -> new DoorBlock(
                    BlockBehaviour.Properties.of()
                            .strength(10.0F, 1000.0F)
                            .requiresCorrectToolForDrops()
                            .sound(SoundType.METAL)
                            .noOcclusion()
                            .isViewBlocking((state, level, pos) -> false),
                    "secure_access_door"
            ));

    public static final RegistryObject<Block> QE_SLIDING = registerBlockWithoutItem("qe_sliding_door",
            () -> new DoorBlock(
                    BlockBehaviour.Properties.of()
                            .strength(10.0F, 1000.0F)
                            .requiresCorrectToolForDrops()
                            .sound(SoundType.METAL)
                            .noOcclusion()
                            .isViewBlocking((state, level, pos) -> false),
                    "qe_sliding_door"
            ));

    public static final RegistryObject<Block> QE_CONTAINMENT = registerBlockWithoutItem("qe_containment_door",
            () -> new DoorBlock(
                    BlockBehaviour.Properties.of()
                            .strength(10.0F, 1000.0F)
                            .requiresCorrectToolForDrops()
                            .sound(SoundType.METAL)
                            .noOcclusion()
                            .isViewBlocking((state, level, pos) -> false),
                    "qe_containment_door"
            ));

    public static final RegistryObject<Block> WATER_DOOR = registerBlockWithoutItem("water_door",
            () -> new DoorBlock(
                    BlockBehaviour.Properties.of()
                            .strength(10.0F, 1000.0F)
                            .requiresCorrectToolForDrops()
                            .sound(SoundType.METAL)
                            .noOcclusion()
                            .isViewBlocking((state, level, pos) -> false),
                    "water_door"
            ));

    public static final RegistryObject<Block> SILO_HATCH = registerBlockWithoutItem("silo_hatch",
            () -> new DoorBlock(
                    BlockBehaviour.Properties.of()
                            .strength(10.0F, 1000.0F)
                            .requiresCorrectToolForDrops()
                            .sound(SoundType.METAL)
                            .noOcclusion()
                            .isViewBlocking((state, level, pos) -> false),
                    "silo_hatch"
            ));

    public static final RegistryObject<Block> SILO_HATCH_LARGE = registerBlockWithoutItem("silo_hatch_large",
            () -> new DoorBlock(
                    BlockBehaviour.Properties.of()
                            .strength(10.0F, 1000.0F)
                            .requiresCorrectToolForDrops()
                            .sound(SoundType.METAL)
                            .noOcclusion()
                            .isViewBlocking((state, level, pos) -> false),
                    "silo_hatch_large"
            ));

    public static final RegistryObject<DoorBlock> VAULT_DOOR = registerBlockWithoutItem("vault_door",
            () -> new DoorBlock(
                    BlockBehaviour.Properties.of()
                            .strength(10.0F, 1000.0F)
                            .requiresCorrectToolForDrops()
                            .sound(SoundType.METAL)
                            .noOcclusion()
                            .isViewBlocking((state, level, pos) -> false),
                    "vault_door"
            ));

    public static final RegistryObject<Block> DOOR_BUNKER = registerBlock("door_bunker",
            () -> new net.minecraft.world.level.block.DoorBlock(BlockBehaviour.Properties.copy(Blocks.NETHERITE_BLOCK).sound(SoundType.NETHERITE_BLOCK).noOcclusion(), BlockSetType.STONE));

    public static final RegistryObject<Block> DOOR_OFFICE = registerBlock("door_office",
            () -> new net.minecraft.world.level.block.DoorBlock(BlockBehaviour.Properties.copy(Blocks.CHERRY_WOOD).sound(SoundType.CHERRY_WOOD).noOcclusion(), BlockSetType.CHERRY));

    public static final RegistryObject<Block> METAL_DOOR = registerBlock("metal_door",
            () -> new net.minecraft.world.level.block.DoorBlock(BlockBehaviour.Properties.copy(Blocks.CHAIN).sound(SoundType.CHAIN).noOcclusion(), BlockSetType.BIRCH));








    // ==================== Helper Methods ====================

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<T> registerBlockWithoutItem(String name, Supplier<T> block) {
        return BLOCKS.register(name, block);
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }
}