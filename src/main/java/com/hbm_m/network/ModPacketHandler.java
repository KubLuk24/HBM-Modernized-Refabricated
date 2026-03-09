package com.hbm_m.network;

import com.hbm_m.lib.RefStrings;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

public class ModPacketHandler {

    private static final String PROTOCOL_VERSION = "1";
    private static boolean REGISTERED = false;

    public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(
            ResourceLocation.fromNamespaceAndPath(RefStrings.MODID, "main_channel"),
            () -> PROTOCOL_VERSION,
            PROTOCOL_VERSION::equals,
            PROTOCOL_VERSION::equals
    );

    public static void register() {
        if (REGISTERED) {
            return;
        }
        REGISTERED = true;

        int id = 0;

        INSTANCE.registerMessage(id++,
            ServerboundDoorModelPacket.class,
            ServerboundDoorModelPacket::encode,
            ServerboundDoorModelPacket::decode,
            ServerboundDoorModelPacket::handle
        );
    }
}