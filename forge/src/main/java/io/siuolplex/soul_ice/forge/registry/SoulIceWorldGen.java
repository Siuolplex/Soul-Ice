package io.siuolplex.soul_ice.forge.registry;

import io.siuolplex.soul_ice.SoulIceConfig;
import io.siuolplex.soul_ice.util.SoulIceIDHandler;
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
    public class MTJSIConfiguredFeatures {
        public static final Holder<ConfiguredFeature<?, ?>> LIGHTSTONE = Holder.createDirect(new ConfiguredFeature<>(Feature.ORE, new OreFeatureConfig(OreConfiguredFeatures.DEEPSLATE_ORE_REPLACEABLES, SoulIceBlocks.LIGHTSTONE.get().getDefaultState(), 48)));
        public static final Holder<ConfiguredFeature<?, ?>> RUJONE_BERRY = Holder.createDirect(new ConfiguredFeature<>(Feature.RANDOM_PATCH, new RandomPatchFeatureConfig(4, 7, 3, PlacedFeatureUtil.filtered(
                Feature.SIMPLE_BLOCK,
                new SimpleBlockFeatureConfig(BlockStateProvider.of(SoulIceBlocks.RUJONE_BERRY_BUSH.get().getDefaultState().with(SweetBerryBushBlock.AGE, 3))),
                BlockPredicate.matchingBlocks(List.of(Blocks.SAND, Blocks.RED_SAND), new BlockPos(0, -1, 0)))
        )));
        public static final Holder<ConfiguredFeature<?, ?>> ORANGE_ROSE = Holder.createDirect(new ConfiguredFeature<>(Feature.RANDOM_PATCH, new RandomPatchFeatureConfig(64, 4, 3, PlacedFeatureUtil.filtered(
                Feature.SIMPLE_BLOCK,
                new SimpleBlockFeatureConfig(BlockStateProvider.of(SoulIceBlocks.ORANGE_ROSE.get().getDefaultState())),
                BlockPredicate.matchingBlock(Blocks.GRASS_BLOCK, new BlockPos(0, -1, 0)))
        )));

    }

    public class MTJSIPlacedFeatures {
        public static Holder<PlacedFeature> LIGHTSTONE = SoulIceWorldGen.registerPlacedFeature(MTJSIConfiguredFeatures.LIGHTSTONE,"lightstone",
                CountPlacementModifier.create(2), InSquarePlacementModifier.getInstance(), HeightRangePlacementModifier.createUniform(YOffset.getBottom(), YOffset.fixed(-8)));

        public static Holder<PlacedFeature> ORANGE_ROSE = SoulIceWorldGen.registerPlacedFeature(MTJSIConfiguredFeatures.ORANGE_ROSE, "orange_rose", RarityFilterPlacementModifier.create(24),
                InSquarePlacementModifier.getInstance(), PlacedFeatureUtil.WORLD_SURFACE_WG_HEIGHTMAP, BiomePlacementModifier.getInstance());

        public static Holder<PlacedFeature> RUJONE_BERRY = SoulIceWorldGen.registerPlacedFeature(MTJSIConfiguredFeatures.RUJONE_BERRY, "rujone_berry",
                RarityFilterPlacementModifier.create(8), InSquarePlacementModifier.getInstance(), PlacedFeatureUtil.WORLD_SURFACE_WG_HEIGHTMAP, BiomePlacementModifier.getInstance());
    }

    public static Holder<PlacedFeature> registerPlacedFeature(Holder<ConfiguredFeature<?,?>> configured, String id, PlacementModifier... modifiers) {
        return PlacedFeatureUtil.register(id, configured, modifiers);
    }

    public static void init() {}
}
