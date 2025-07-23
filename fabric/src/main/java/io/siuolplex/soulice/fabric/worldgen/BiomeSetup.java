package io.siuolplex.soulice.fabric.worldgen;

import com.terraformersmc.biolith.api.surface.SurfaceGeneration;
import io.siuolplex.soulice.registry.SoulIceBiomes;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.biome.v1.NetherBiomes;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.SurfaceRules;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.CaveSurface;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import java.util.List;

import static io.siuolplex.soulice.registry.SoulIceBlocks.MULVITE;

public class BiomeSetup {
    public static void setupBiomes() {
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Decoration.UNDERGROUND_ORES, ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath("soul_ice", "lightstone")));
        BiomeModifications.addFeature(BiomeSelectors.tag(BiomeTags.HAS_DESERT_PYRAMID), GenerationStep.Decoration.VEGETAL_DECORATION, ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath("soul_ice", "rujone_berries")));
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(Biomes.PLAINS, Biomes.WINDSWEPT_HILLS, Biomes.GROVE, Biomes.FOREST, Biomes.FLOWER_FOREST), GenerationStep.Decoration.VEGETAL_DECORATION, ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath("soul_ice", "orange_rose")));
        NetherBiomes.addNetherBiome(SoulIceBiomes.SOUL_HUSK, Climate.parameters(-0.4f, -0.6f, 0f, 0f, 0f, 0f, 0f));

        SurfaceRules.RuleSource evilRule = SurfaceRules.sequence(
                SurfaceRules.ifTrue(SurfaceRules.verticalGradient("bedrock_floor", VerticalAnchor.BOTTOM, VerticalAnchor.aboveBottom(5)), SurfaceRules.state(Blocks.BEDROCK.defaultBlockState())),
                SurfaceRules.ifTrue(SurfaceRules.not(SurfaceRules.verticalGradient("bedrock_roof", VerticalAnchor.belowTop(5), VerticalAnchor.TOP)), SurfaceRules.state(Blocks.BEDROCK.defaultBlockState())),
                SurfaceRules.ifTrue(
                        SurfaceRules.isBiome(SoulIceBiomes.SOUL_HUSK),
                        SurfaceRules.sequence(
                                SurfaceRules.ifTrue(
                                        SurfaceRules.stoneDepthCheck(5, true, CaveSurface.CEILING),
                                        SurfaceRules.sequence(
                                                SurfaceRules.ifTrue(
                                                        SurfaceRules.UNDER_CEILING,
                                                        SurfaceRules.state(MULVITE.defaultBlockState())
                                                ),
                                                SurfaceRules.ifTrue(
                                                        SurfaceRules.UNDER_FLOOR,
                                                        SurfaceRules.state(MULVITE.defaultBlockState())
                                                ))),
                                SurfaceRules.ifTrue(
                                        SurfaceRules.stoneDepthCheck(0, true, CaveSurface.FLOOR),
                                        SurfaceRules.sequence(
                                                SurfaceRules.ifTrue(
                                                        SurfaceRules.UNDER_CEILING,
                                                        SurfaceRules.state(MULVITE.defaultBlockState())
                                                ),
                                                SurfaceRules.ifTrue(
                                                        SurfaceRules.UNDER_FLOOR,
                                                        SurfaceRules.state(MULVITE.defaultBlockState())
                                                )

                                        )
                                )
                        )
                )
        );

        SurfaceGeneration.addNetherSurfaceRules(SoulIceBiomes.SOUL_HUSK.location(), evilRule);
    }

    public static void biomeBootstrap(BootstrapContext<Biome> bootstrap) {
        bootstrap.register(
                SoulIceBiomes.SOUL_HUSK,
                new Biome.BiomeBuilder().downfall(0).hasPrecipitation(false).temperature(2).specialEffects(new BiomeSpecialEffects.Builder().fogColor(0).waterColor(0).waterFogColor(0).skyColor(0).build())
                        .mobSpawnSettings(MobSpawnSettings.EMPTY).generationSettings(BiomeGenerationSettings.EMPTY).build()
        );
    }
}
