package com.hbm_m.particle;

import com.hbm_m.lib.RefStrings;
import net.minecraft.core.particles.ParticleType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModParticleTypes {
    public static final DeferredRegister<ParticleType<?>> PARTICLES =
            DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, RefStrings.MODID);

    public static void register(IEventBus eventBus) {
        PARTICLES.register(eventBus);
    }
}