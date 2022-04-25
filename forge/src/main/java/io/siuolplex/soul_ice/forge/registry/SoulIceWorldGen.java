package io.siuolplex.soul_ice.forge.registry;

import io.siuolplex.soul_ice.util.SoulIceIDHandler;
import net.minecraft.block.Block;
import net.minecraft.util.Holder;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.decorator.CountPlacementModifier;
import net.minecraft.world.gen.decorator.HeightRangePlacementModifier;
import net.minecraft.world.gen.decorator.InSquarePlacementModifier;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.util.PlacedFeatureUtil;
import net.minecraftforge.registries.DeferredRegister;

import java.util.Arrays;

public class SoulIceWorldGen {
    private static final Holder<ConfiguredFeature<?,?>> LIGHTSTONE_GENERATION_CONFIGURED = Holder.createDirect(new ConfiguredFeature<>(Feature.ORE, new OreFeatureConfig(OreConfiguredFeatures.DEEPSLATE_ORE_REPLACEABLES, SoulIceBlocks.LIGHTSTONE.get().getDefaultState(), 48)));

    public static Holder<PlacedFeature> LIGHTSTONE_GEN = register(LIGHTSTONE_GENERATION_CONFIGURED, "lightstone",
            CountPlacementModifier.create(2), InSquarePlacementModifier.getInstance(), HeightRangePlacementModifier.createUniform(YOffset.getBottom(), YOffset.fixed(-8)));

    public static Holder<PlacedFeature> register(Holder<ConfiguredFeature<?,?>> configured, String id, PlacementModifier... modifiers) {
        return PlacedFeatureUtil.register(id, configured, modifiers);
    }

    public static void init() {}
}
