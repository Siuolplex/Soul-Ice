package io.siuolplex.soulice.registry;

import io.siuolplex.soulice.worldgen.SoulIceFreezeTopFeature;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.BlockStateConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class SoulIceFeatures {
    public static Feature<NoneFeatureConfiguration> SOUL_ICESHEET = register(new ResourceLocation("soul_ice", "soul_icesheet"), new SoulIceFreezeTopFeature(NoneFeatureConfiguration.CODEC));


    private static <C extends FeatureConfiguration, F extends Feature<C>> F register(ResourceLocation identifier, F feature) {
        return Registry.register(BuiltInRegistries.FEATURE, identifier, feature);
    }

    public static void init() {}
}
