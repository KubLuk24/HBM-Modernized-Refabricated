package com.hbm_m.main;

import com.hbm_m.item.ModItems;
import com.hbm_m.lib.RefStrings;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = 
        DeferredRegister.create(Registries.CREATIVE_MODE_TAB, RefStrings.MODID);

    public static final RegistryObject<CreativeModeTab> NTM_DOORS_TAB = CREATIVE_MODE_TABS.register("ntm_doors_tab",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup." + RefStrings.MODID + ".ntm_doors_tab"))
                    .icon(() -> new ItemStack(ModItems.VAULT_DOOR.get()))
                    .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}