package io.siuolplex.soul_ice.fabric.registry;

import com.google.common.collect.ImmutableSet;
import io.siuolplex.soul_ice.SoulIceConfig;
import io.siuolplex.soul_ice.util.SoulIceIDHandler;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.block.Blocks;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.Heightmap;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placementmodifier.CountPlacementModifier;
import net.minecraft.world.gen.placementmodifier.HeightRangePlacementModifier;
import net.minecraft.world.gen.placementmodifier.SquarePlacementModifier;
import net.minecraft.world.gen.stateprovider.SimpleBlockStateProvider;

import java.util.Arrays;

public class SoulIceWorldGen {
    private static final ConfiguredFeature<?, ?> LIGHTSTONE_GENERATION_CONFIGURED = new ConfiguredFeature(Feature.ORE, new OreFeatureConfig(OreConfiguredFeatures.DEEPSLATE_ORE_REPLACEABLES, SoulIceBlocks.LIGHTSTONE.getDefaultState(), 32));

    public static PlacedFeature LIGHTSTONE_GENERATION_PLACED = new PlacedFeature(
            RegistryEntry.of(LIGHTSTONE_GENERATION_CONFIGURED),
            Arrays.asList(CountPlacementModifier.of(2), SquarePlacementModifier.of(), HeightRangePlacementModifier.uniform(YOffset.getBottom(), YOffset.fixed(-8))));


    public static void register(ConfiguredFeature<?, ?> configured, PlacedFeature placed, String id) {
        Identifier featureID = SoulIceIDHandler.idFormatter(id);
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, featureID, configured);
        Registry.register(BuiltinRegistries.PLACED_FEATURE, featureID, placed);
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, RegistryKey.of(Registry.PLACED_FEATURE_KEY, featureID));
    }

    public static void init() {
        if (SoulIceConfig.instance().enableLightstoneGeneration) register(LIGHTSTONE_GENERATION_CONFIGURED, LIGHTSTONE_GENERATION_PLACED, "lightstone");
    }
}
