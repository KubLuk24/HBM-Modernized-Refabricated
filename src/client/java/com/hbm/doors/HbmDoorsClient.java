package com.hbm.doors;

import com.hbm.doors.client.DoorBlockEntityRenderer;
import com.hbm.doors.network.DoorTogglePacket;
import com.hbm.doors.registry.ModBlockEntities;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.BlockEntityRendererRegistry;

public class HbmDoorsClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockEntityRendererRegistry.register(ModBlockEntities.DOOR_BLOCK_ENTITY, DoorBlockEntityRenderer::new);
        DoorTogglePacket.registerClient();
    }
}
