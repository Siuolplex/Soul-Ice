package io.siuolplex.soul_ice.registry.fabric;

import io.siuolplex.soul_ice.registry.SoulIceBlocks;
import io.siuolplex.soul_ice.util.SoulIceConfig;
import io.siuolplex.soul_ice.util.SoulIceIDHandler;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.block.Blocks;
import net.minecraft.block.SweetBerryBushBlock;
import net.minecraft.util.Holder;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.blockpredicate.BlockPredicate;
import net.minecraft.world.gen.decorator.*;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.util.PlacedFeatureUtil;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;

import java.util.Arrays;
import java.util.List;

public class SoulIceWorldGen {
    public static void register(ConfiguredFeature<?, ?> configured, PlacedFeature placed, String id) {
        Identifier featureID = SoulIceIDHandler.idFormatter(id);
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, featureID, configured);
        Registry.register(BuiltinRegistries.PLACED_FEATURE, featureID, placed);
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, RegistryKey.of(Registry.PLACED_FEATURE_KEY, featureID));
    }

    public static void registerPlains(ConfiguredFeature<?, ?> configured, PlacedFeature placed, String id) {
        Identifier featureID = SoulIceIDHandler.idFormatter(id);
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, featureID, configured);
        Registry.register(BuiltinRegistries.PLACED_FEATURE, featureID, placed);
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeKeys.PLAINS, BiomeKeys.FOREST, BiomeKeys.FLOWER_FOREST), GenerationStep.Feature.VEGETAL_DECORATION, RegistryKey.of(Registry.PLACED_FEATURE_KEY, featureID));
    }

    public static void registerDesert(ConfiguredFeature<?, ?> configured, PlacedFeature placed, String id) {
        Identifier featureID = SoulIceIDHandler.idFormatter(id);
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, featureID, configured);
        Registry.register(BuiltinRegistries.PLACED_FEATURE, featureID, placed);
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeKeys.DESERT, BiomeKeys.BADLANDS), GenerationStep.Feature.VEGETAL_DECORATION, RegistryKey.of(Registry.PLACED_FEATURE_KEY, featureID));
    }

    public static void init() {
        if (SoulIceConfig.enableLightstoneGeneration) register(MTJSIConfiguredFeatures.LIGHTSTONE, MTJSIPlacedFeatures.LIGHTSTONE, "lightstone");
        if (SoulIceConfig.enableOrangeRoseGeneration) registerPlains(MTJSIConfiguredFeatures.ORANGE_ROSE, MTJSIPlacedFeatures.ORANGE_ROSE, "orange_rose");
        if (SoulIceConfig.enableRujoneBerryGeneration) registerDesert(MTJSIConfiguredFeatures.RUJONE_BERRY, MTJSIPlacedFeatures.RUJONE_BERRY, "rujone_berry");
    }

    public class MTJSIPlacedFeatures {
        public static PlacedFeature LIGHTSTONE = new PlacedFeature(
                Holder.createDirect(MTJSIConfiguredFeatures.LIGHTSTONE),
                Arrays.asList(CountPlacementModifier.create(2), InSquarePlacementModifier.getInstance(), HeightRangePlacementModifier.createUniform(YOffset.getBottom(), YOffset.fixed(-8))));

        public static PlacedFeature ORANGE_ROSE = new PlacedFeature(
                Holder.createDirect(MTJSIConfiguredFeatures.ORANGE_ROSE),
                List.of(new PlacementModifier[]{
                        RarityFilterPlacementModifier.create(24),
                        InSquarePlacementModifier.getInstance(),
                        PlacedFeatureUtil.WORLD_SURFACE_WG_HEIGHTMAP,
                        BiomePlacementModifier.getInstance()
                }));

        public static PlacedFeature RUJONE_BERRY = new PlacedFeature(
                Holder.createDirect(MTJSIConfiguredFeatures.RUJONE_BERRY),
                List.of(new PlacementModifier[]{
                        RarityFilterPlacementModifier.create(8),
                        InSquarePlacementModifier.getInstance(),
                        PlacedFeatureUtil.WORLD_SURFACE_WG_HEIGHTMAP,
                        BiomePlacementModifier.getInstance()
                }));
    }

    public class MTJSIConfiguredFeatures {
        public static final ConfiguredFeature<?, ?> LIGHTSTONE = new ConfiguredFeature<>(Feature.ORE, new OreFeatureConfig(OreConfiguredFeatures.DEEPSLATE_ORE_REPLACEABLES, SoulIceBlocks.LIGHTSTONE.getDefaultState(), 48));
        public static final ConfiguredFeature<?, ?> RUJONE_BERRY = new ConfiguredFeature(Feature.RANDOM_PATCH, new RandomPatchFeatureConfig(4, 7, 3, PlacedFeatureUtil.filtered(
                Feature.SIMPLE_BLOCK,
                new SimpleBlockFeatureConfig(BlockStateProvider.of(SoulIceBlocks.RUJONE_BERRY_BUSH.getDefaultState().with(SweetBerryBushBlock.AGE, 3))),
                BlockPredicate.matchingBlocks(List.of(Blocks.SAND, Blocks.RED_SAND), new BlockPos(0, -1, 0)))
        ));
        public static final ConfiguredFeature<?, ?> ORANGE_ROSE = new ConfiguredFeature(Feature.RANDOM_PATCH, new RandomPatchFeatureConfig(64, 4, 3, PlacedFeatureUtil.filtered(
                Feature.SIMPLE_BLOCK,
                new SimpleBlockFeatureConfig(BlockStateProvider.of(SoulIceBlocks.ORANGE_ROSE.getDefaultState())),
                BlockPredicate.matchingBlock(Blocks.GRASS_BLOCK, new BlockPos(0, -1, 0)))
        ));

    }
}
