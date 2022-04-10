package io.siuolplex.soul_ice.forge.registry;

import io.siuolplex.soul_ice.util.SoulIceIDHandler;
import net.minecraft.block.Block;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placementmodifier.CountPlacementModifier;
import net.minecraft.world.gen.placementmodifier.HeightRangePlacementModifier;
import net.minecraft.world.gen.placementmodifier.PlacementModifier;
import net.minecraft.world.gen.placementmodifier.SquarePlacementModifier;
import net.minecraftforge.registries.DeferredRegister;

import java.util.Arrays;

public class SoulIceWorldGen {
    private static final RegistryEntry<ConfiguredFeature<?,?>> LIGHTSTONE_GENERATION_CONFIGURED = RegistryEntry.of(new ConfiguredFeature<>(Feature.ORE, new OreFeatureConfig(OreConfiguredFeatures.DEEPSLATE_ORE_REPLACEABLES, SoulIceBlocks.LIGHTSTONE.get().getDefaultState(), 32)));

    public static RegistryEntry<PlacedFeature> LIGHTSTONE_GEN = register(LIGHTSTONE_GENERATION_CONFIGURED, "lightstone",
            CountPlacementModifier.of(2), SquarePlacementModifier.of(), HeightRangePlacementModifier.uniform(YOffset.getBottom(), YOffset.fixed(-8)));

    public static RegistryEntry<PlacedFeature> register(RegistryEntry<ConfiguredFeature<?,?>> configured, String id, PlacementModifier... modifiers) {
        return PlacedFeatures.register(id, configured, modifiers);
    }

    public static void init() {}
}
