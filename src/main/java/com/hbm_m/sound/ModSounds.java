package com.hbm_m.sound;

import com.hbm_m.lib.RefStrings;
import com.hbm_m.main.MainRegistry;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS = 
            DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, RefStrings.MODID);

    // Door sounds
    public static final RegistryObject<SoundEvent> GARAGE_MOVE = registerSoundEvents("block.garage_move");
    public static final RegistryObject<SoundEvent> GARAGE_STOP = registerSoundEvents("block.garage_stop");
    public static final RegistryObject<SoundEvent> ALARM_6 = registerSoundEvents("block.alarm_6");
    public static final RegistryObject<SoundEvent> DOOR_WGH_BIG_START = registerSoundEvents("block.door_wgh_big_start");
    public static final RegistryObject<SoundEvent> DOOR_WGH_BIG_STOP = registerSoundEvents("block.door_wgh_big_stop");
    public static final RegistryObject<SoundEvent> DOOR_MOVE_2 = registerSoundEvents("block.door_move_2");
    public static final RegistryObject<SoundEvent> DOOR_SHUT_1 = registerSoundEvents("block.door_shut_1");
    public static final RegistryObject<SoundEvent> DOOR_SLIDE_OPENED_1 = registerSoundEvents("block.door_slide_opened_1");
    public static final RegistryObject<SoundEvent> DOOR_SLIDE_OPENING_1 = registerSoundEvents("block.door_slide_opening_1");
    public static final RegistryObject<SoundEvent> LEVER_1 = registerSoundEvents("block.lever_1");
    public static final RegistryObject<SoundEvent> METAL_STOP_1 = registerSoundEvents("block.metal_stop_1");
    public static final RegistryObject<SoundEvent> SLIDING_DOOR_OPENED = registerSoundEvents("block.sliding_door_opened");
    public static final RegistryObject<SoundEvent> SLIDING_DOOR_OPENING = registerSoundEvents("block.sliding_door_opening");
    public static final RegistryObject<SoundEvent> SLIDING_DOOR_SHUT = registerSoundEvents("block.sliding_door_shut");
    public static final RegistryObject<SoundEvent> TRANSITION_SEAL_OPEN = registerSoundEvents("block.transition_seal_open");
    public static final RegistryObject<SoundEvent> TRANSITION_SEAL_CLOSE = registerSoundEvents("block.transition_seal_close");
    public static final RegistryObject<SoundEvent> WGH_START = registerSoundEvents("block.wgh_start");
    public static final RegistryObject<SoundEvent> WGH_STOP = registerSoundEvents("block.wgh_stop");
    public static final RegistryObject<SoundEvent> VAULT_SCRAPE = registerSoundEvents("block.vault_scrape");
    public static final RegistryObject<SoundEvent> VAULT_THUD = registerSoundEvents("block.vault_thud");

    public static final RegistryObject<SoundEvent> METAL_BOX_OPEN = registerSoundEvents("block.metal_box_open");
    public static final RegistryObject<SoundEvent> METAL_BOX_CLOSE = registerSoundEvents("block.metal_box_close");

    private static RegistryObject<SoundEvent> registerSoundEvents(String name) {
        ResourceLocation id = ResourceLocation.fromNamespaceAndPath(RefStrings.MODID, name);
        return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(id));
    }

    public static void register(IEventBus eventBus) {
        SOUND_EVENTS.register(eventBus);
        MainRegistry.LOGGER.info("Registered SoundEvents for " + RefStrings.MODID);
    }
}