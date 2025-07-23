package io.siuolplex.soulice.fabric.worldgen;

import com.terraformersmc.biolith.api.surface.SurfaceGeneration;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.biome.v1.NetherBiomes;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.Climate;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.SurfaceRules;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.placement.CaveSurface;

import static io.siuolplex.soulice.registry.SoulIceBlocks.MULVITE;

public class BiomeSetup {
    public static void setupBiomes() {
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Decoration.UNDERGROUND_ORES, ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath("soul_ice", "lightstone")));
        BiomeModifications.addFeature(BiomeSelectors.tag(BiomeTags.HAS_DESERT_PYRAMID), GenerationStep.Decoration.VEGETAL_DECORATION, ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath("soul_ice", "rujone_berries")));
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(Biomes.PLAINS, Biomes.WINDSWEPT_HILLS, Biomes.GROVE, Biomes.FOREST, Biomes.FLOWER_FOREST), GenerationStep.Decoration.VEGETAL_DECORATION, ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath("soul_ice", "orange_rose")));
        NetherBiomes.addNetherBiome(ResourceKey.create(Registries.BIOME, ResourceLocation.fromNamespaceAndPath("soul_ice", "soul_husk")), Climate.parameters(-0.4f, -0.6f, 0f, 0f, 0f, 0f, 0f));

        SurfaceRules.RuleSource evilRule = SurfaceRules.sequence(
                SurfaceRules.ifTrue(SurfaceRules.verticalGradient("bedrock_floor", VerticalAnchor.BOTTOM, VerticalAnchor.aboveBottom(5)), SurfaceRules.state(Blocks.BEDROCK.defaultBlockState())),
                SurfaceRules.ifTrue(SurfaceRules.not(SurfaceRules.verticalGradient("bedrock_roof", VerticalAnchor.belowTop(5), VerticalAnchor.TOP)), SurfaceRules.state(Blocks.BEDROCK.defaultBlockState())),
                SurfaceRules.ifTrue(
                        SurfaceRules.isBiome(ResourceKey.create(Registries.BIOME, ResourceLocation.fromNamespaceAndPath("soul_ice", "soul_husk"))),
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

        SurfaceGeneration.addNetherSurfaceRules(ResourceLocation.fromNamespaceAndPath("soul_ice", "soul_husk"), evilRule);
    }
}
