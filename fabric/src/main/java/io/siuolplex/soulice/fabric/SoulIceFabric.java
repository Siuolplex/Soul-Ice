package io.siuolplex.soulice.fabric;

import io.siuolplex.soulice.SoulIce;
import io.siuolplex.soulice.fabric.item.SoulIceItemGroup;
import io.siuolplex.soulice.registry.SoulIceItems;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.impl.biome.modification.BuiltInRegistryKeys;
import net.minecraft.client.gui.components.tabs.Tab;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.GenerationStep;

import static net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup.builder;

public class SoulIceFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        SoulIce.init();
        SoulIceItemGroup.init();
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Decoration.UNDERGROUND_ORES, ResourceKey.create(Registries.PLACED_FEATURE, new ResourceLocation("soul_ice", "lightstone")));
        BiomeModifications.addFeature(BiomeSelectors.tag(BiomeTags.HAS_DESERT_PYRAMID), GenerationStep.Decoration.VEGETAL_DECORATION, ResourceKey.create(Registries.PLACED_FEATURE, new ResourceLocation("soul_ice", "rujone_berries")));
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(Biomes.PLAINS, Biomes.WINDSWEPT_HILLS, Biomes.GROVE, Biomes.FOREST, Biomes.FLOWER_FOREST), GenerationStep.Decoration.VEGETAL_DECORATION, ResourceKey.create(Registries.PLACED_FEATURE, new ResourceLocation("soul_ice", "orange_rose")));

    }
}
