package com.hbm.doors;

import com.hbm.doors.registry.ModBlockEntities;
import com.hbm.doors.registry.ModBlocks;
import com.hbm.doors.registry.ModCreativeTab;
import com.hbm.doors.registry.ModItems;
import com.hbm.doors.registry.ModSounds;
import com.hbm.doors.network.DoorTogglePacket;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HbmDoorsMod implements ModInitializer {
    public static final String MOD_ID = "hbm_doors";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        ModSounds.register();
        ModBlocks.register();
        ModBlockEntities.register();
        ModItems.register();
        ModCreativeTab.register();
        DoorTogglePacket.registerServer();
        LOGGER.info("HBM Doors initialized!");
    }
}
