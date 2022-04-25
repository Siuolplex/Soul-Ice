package io.siuolplex.soul_ice.quilt.registry;

import io.siuolplex.soul_ice.SoulIceConfig;
import io.siuolplex.soul_ice.util.SoulIceIDHandler;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.util.Holder;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.decorator.CountPlacementModifier;
import net.minecraft.world.gen.decorator.HeightRangePlacementModifier;
import net.minecraft.world.gen.decorator.InSquarePlacementModifier;
import net.minecraft.world.gen.feature.*;

import java.util.Arrays;

public class SoulIceWorldGen {
    private static final ConfiguredFeature<?, ?> LIGHTSTONE_GENERATION_CONFIGURED = new ConfiguredFeature(Feature.ORE, new OreFeatureConfig(OreConfiguredFeatures.DEEPSLATE_ORE_REPLACEABLES, SoulIceBlocks.LIGHTSTONE.getDefaultState(), 48));

    public static PlacedFeature LIGHTSTONE_GENERATION_PLACED = new PlacedFeature(
            Holder.createDirect(LIGHTSTONE_GENERATION_CONFIGURED),
            Arrays.asList(CountPlacementModifier.create(2), InSquarePlacementModifier.getInstance(), HeightRangePlacementModifier.createUniform(YOffset.getBottom(), YOffset.fixed(-8))));


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
