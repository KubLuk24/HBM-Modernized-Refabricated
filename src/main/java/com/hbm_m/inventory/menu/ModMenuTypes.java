package com.hbm_m.inventory.menu;

import com.hbm_m.lib.RefStrings;

import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModMenuTypes {

    public static final DeferredRegister<MenuType<?>> MENUS =
            DeferredRegister.create(ForgeRegistries.MENU_TYPES, RefStrings.MODID);

    public static void register(IEventBus eventBus) {
        MENUS.register(eventBus);
    }
}