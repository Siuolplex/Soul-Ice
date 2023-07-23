package io.siuolplex.soulice.mixin;

import io.siuolplex.soulice.SoulIce;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeManager;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.levelgen.*;
import net.minecraft.world.level.levelgen.placement.CaveSurface;
import net.minecraft.world.level.levelgen.synth.NormalNoise;
import org.apache.logging.log4j.message.StringFormattedMessage;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Formatter;

import static io.siuolplex.soulice.registry.SoulIceBlocks.*;

@Mixin(SurfaceSystem.class)
public class SurfaceSystemMixin {
    // I hate this genuinely, I do not recommend doing this.
    // If on Quilt, use its SurfaceRule system
    @ModifyVariable(method= "buildSurface", at = @At(value = "HEAD"), ordinal = 0, argsOnly = true)
    private SurfaceRules.RuleSource soulIce$real(SurfaceRules.RuleSource value) {
        if (value instanceof SurfaceRules.SequenceRuleSource sequenceRuleSource) {
            List<SurfaceRules.RuleSource> ruleSources = new ArrayList<>();
            ruleSources.addAll(sequenceRuleSource.sequence());
            ruleSources.add(ruleSources.size() - 1, SurfaceRules.ifTrue(
                    SurfaceRules.isBiome(ResourceKey.create(Registries.BIOME, new ResourceLocation("soul_ice", "soul_husk"))),
                    SurfaceRules.sequence(
                            SurfaceRules.ifTrue(
                                    SurfaceRules.stoneDepthCheck(0, true, CaveSurface.CEILING),
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

                                    )))));
            value = SurfaceRules.sequence(ruleSources.toArray(new SurfaceRules.RuleSource[0]));
        }
        return value;
    }
}
