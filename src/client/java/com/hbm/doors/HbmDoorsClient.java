package com.hbm.doors;

import com.hbm.doors.client.DoorBlockEntityRenderer;
import com.hbm.doors.network.DoorTogglePacket;
import com.hbm.doors.registry.ModBlockEntities;
import com.hbm.doors.registry.ModBlocks;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.BlockEntityRendererRegistry;
import net.minecraft.client.render.RenderLayer;

public class HbmDoorsClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockEntityRendererRegistry.register(ModBlockEntities.DOOR_BLOCK_ENTITY, DoorBlockEntityRenderer::new);
        DoorTogglePacket.registerClient();
        BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutout(),
            ModBlocks.METAL_DOOR, ModBlocks.DOOR_BUNKER, ModBlocks.DOOR_OFFICE);
    }
}
