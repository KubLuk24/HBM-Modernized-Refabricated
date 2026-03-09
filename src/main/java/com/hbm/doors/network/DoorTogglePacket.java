package com.hbm.doors.network;

import com.hbm.doors.HbmDoorsMod;
import com.hbm.doors.block.entity.DoorBlockEntity;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;

public class DoorTogglePacket {
    public static final Identifier ID = new Identifier(HbmDoorsMod.MOD_ID, "door_toggle");

    public static PacketByteBuf create(BlockPos pos) {
        PacketByteBuf buf = PacketByteBufs.create();
        buf.writeBlockPos(pos);
        return buf;
    }

    public static void registerServer() {
        ServerPlayNetworking.registerGlobalReceiver(ID, (server, player, handler, buf, responseSender) -> {
            BlockPos pos = buf.readBlockPos();
            server.execute(() -> handleOnServer(player, pos));
        });
    }

    public static void registerClient() {
        // Client-side: nothing needed for C2S packet receiving
    }

    private static void handleOnServer(ServerPlayerEntity player, BlockPos pos) {
        var world = player.getWorld();
        if (player.getBlockPos().getManhattanDistance(pos) > 10) return;
        if (world.getBlockEntity(pos) instanceof DoorBlockEntity doorBE) {
            doorBE.toggle(world, pos, world.getBlockState(pos));
        }
    }
}
