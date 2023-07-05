package io.siuolplex.soulice.fabric.item;

import io.siuolplex.soulice.registry.SoulIceItems;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import org.intellij.lang.annotations.Identifier;

public class SoulIceItemGroup {
    public static final CreativeModeTab SOUL_ICE = FabricItemGroup.builder()
            .icon(SoulIceItems.SOUL_ICE::getDefaultInstance)
            .title(Component.translatable("itemGroup.soul_ice.soul_ice"))
            .displayItems((itemDisplayParameters, output) -> {
                for (Item item : SoulIceItems.SOUL_ICE_ITEM_GROUP) {
                    output.accept(item);
                }
            }).build();

    public static void init() {
        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, new ResourceLocation("soul_ice", "soul_ice"), SOUL_ICE);
    }
}
