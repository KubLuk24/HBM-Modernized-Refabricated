package com.hbm.doors.registry;

import com.hbm.doors.HbmDoorsMod;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class ModSounds {
    public static SoundEvent GARAGE_MOVE;
    public static SoundEvent GARAGE_STOP;
    public static SoundEvent DOOR_WGH_BIG_START;
    public static SoundEvent DOOR_WGH_BIG_STOP;
    public static SoundEvent DOOR_MOVE_2;
    public static SoundEvent DOOR_SHUT_1;
    public static SoundEvent DOOR_SLIDE_OPENED_1;
    public static SoundEvent DOOR_SLIDE_OPENING_1;
    public static SoundEvent LEVER_1;
    public static SoundEvent METAL_STOP_1;
    public static SoundEvent SLIDING_DOOR_OPENED;
    public static SoundEvent SLIDING_DOOR_OPENING;
    public static SoundEvent SLIDING_DOOR_SHUT;
    public static SoundEvent TRANSITION_SEAL_OPEN;
    public static SoundEvent TRANSITION_SEAL_CLOSE;
    public static SoundEvent WGH_START;
    public static SoundEvent WGH_STOP;
    public static SoundEvent VAULT_SCRAPE;
    public static SoundEvent VAULT_THUD;
    public static SoundEvent ALARM_6;

    private static SoundEvent reg(String name) {
        Identifier id = new Identifier(HbmDoorsMod.MOD_ID, name);
        return Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(id));
    }

    public static void register() {
        GARAGE_MOVE = reg("block.door.garage_move");
        GARAGE_STOP = reg("block.door.garage_stop");
        DOOR_WGH_BIG_START = reg("block.door.door_wgh_big_start");
        DOOR_WGH_BIG_STOP = reg("block.door.door_wgh_big_stop");
        DOOR_MOVE_2 = reg("block.door.door_move_2");
        DOOR_SHUT_1 = reg("block.door.door_shut_1");
        DOOR_SLIDE_OPENED_1 = reg("block.door.door_slide_opened_1");
        DOOR_SLIDE_OPENING_1 = reg("block.door.door_slide_opening_1");
        LEVER_1 = reg("block.door.lever_1");
        METAL_STOP_1 = reg("block.door.metal_stop_1");
        SLIDING_DOOR_OPENED = reg("block.door.sliding_door_opened");
        SLIDING_DOOR_OPENING = reg("block.door.sliding_door_opening");
        SLIDING_DOOR_SHUT = reg("block.door.sliding_door_shut");
        TRANSITION_SEAL_OPEN = reg("block.door.transition_seal_open");
        TRANSITION_SEAL_CLOSE = reg("block.door.transition_seal_close");
        WGH_START = reg("block.door.wgh_start");
        WGH_STOP = reg("block.door.wgh_stop");
        VAULT_SCRAPE = reg("block.door.vault_scrape");
        VAULT_THUD = reg("block.door.vault_thud");
        ALARM_6 = reg("block.door.alarm_6");
    }
}
