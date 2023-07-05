package io.siuolplex.soulice.fabric;

import io.siuolplex.soulice.SoulIce;
import io.siuolplex.soulice.fabric.item.SoulIceItemGroup;
import io.siuolplex.soulice.registry.SoulIceItems;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.client.gui.components.tabs.Tab;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;

import static net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup.builder;

public class SoulIceFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        SoulIce.init();
        SoulIceItemGroup.init();
    }
}
