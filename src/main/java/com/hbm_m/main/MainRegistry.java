package com.hbm_m.main;

import org.slf4j.Logger;

import com.hbm_m.block.ModBlocks;
import com.hbm_m.block.entity.ModBlockEntities;
import com.hbm_m.block.entity.doors.DoorDeclRegistry;
import com.hbm_m.config.ModClothConfig;
import com.hbm_m.inventory.menu.ModMenuTypes;
import com.hbm_m.item.ModItems;
import com.hbm_m.lib.RefStrings;
import com.hbm_m.network.ModPacketHandler;
import com.hbm_m.sound.ModSounds;
import com.mojang.logging.LogUtils;

import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(RefStrings.MODID)
public class MainRegistry {

    public static final Logger LOGGER = LogUtils.getLogger();
    public static final String MOD_ID = "hbm_m";

    static {
        ModClothConfig.register();
    }

    public MainRegistry(FMLJavaModLoadingContext context) {
        LOGGER.info("Initializing " + RefStrings.NAME);

        IEventBus modEventBus = context.getModEventBus();

        DoorDeclRegistry.init();

        ModBlocks.BLOCKS.register(modEventBus);
        ModItems.ITEMS.register(modEventBus);
        ModBlockEntities.register(modEventBus);
        ModMenuTypes.MENUS.register(modEventBus);
        ModCreativeTabs.CREATIVE_MODE_TABS.register(modEventBus);
        ModSounds.SOUND_EVENTS.register(modEventBus);

        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::addCreative);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            ModPacketHandler.register();
        });
    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTab() == ModCreativeTabs.NTM_DOORS_TAB.get() || event.getTabKey() == CreativeModeTabs.SEARCH) {
            // Standard doors
            event.accept(ModBlocks.DOOR_OFFICE);
            event.accept(ModBlocks.DOOR_BUNKER);
            event.accept(ModBlocks.METAL_DOOR);

            // Custom multiblock doors
            event.accept(ModBlocks.LARGE_VEHICLE_DOOR);
            event.accept(ModBlocks.ROUND_AIRLOCK_DOOR);
            event.accept(ModBlocks.FIRE_DOOR);
            event.accept(ModBlocks.SLIDING_SEAL_DOOR);
            event.accept(ModBlocks.SECURE_ACCESS_DOOR);
            event.accept(ModBlocks.QE_CONTAINMENT);
            event.accept(ModBlocks.QE_SLIDING);
            event.accept(ModBlocks.WATER_DOOR);
            event.accept(ModBlocks.SILO_HATCH);
            event.accept(ModBlocks.SILO_HATCH_LARGE);
            event.accept(ModBlocks.VAULT_DOOR);
            event.accept(ModBlocks.TRANSITION_SEAL);
            event.accept(ModBlocks.SLIDE_DOOR);
        }
    }
}

