package io.siuolplex.soulice.worldgen;

import com.mojang.serialization.Codec;
import io.siuolplex.soulice.registry.SoulIceBiomes;
import io.siuolplex.soulice.registry.SoulIceBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.SnowAndFreezeFeature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class SoulIceFreezeTopFeature extends SnowAndFreezeFeature {
    public SoulIceFreezeTopFeature(Codec<NoneFeatureConfiguration> codec) {
        super(codec);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> featurePlaceContext) {
        WorldGenLevel worldGenLevel = featurePlaceContext.level();
        BlockPos blockPos = featurePlaceContext.origin();
        BlockPos.MutableBlockPos surfacePos = new BlockPos.MutableBlockPos();
        BlockPos.MutableBlockPos underPos = new BlockPos.MutableBlockPos();
        BlockPos.MutableBlockPos abovePos = new BlockPos.MutableBlockPos();

        for(int xSeq = 0; xSeq < 16; ++xSeq) {
            for(int zSeq = 0; zSeq < 16; ++zSeq) {
                int xPos = blockPos.getX() + xSeq;
                int zPos = blockPos.getZ() + zSeq;

                surfacePos.set(xPos, featurePlaceContext.chunkGenerator().getSeaLevel(), zPos);
                underPos.set(surfacePos).move(Direction.DOWN, 1);
                abovePos.set(underPos);


                if (worldGenLevel.getBlockState(underPos).is(Blocks.LAVA) &&
                        worldGenLevel.getBiome(surfacePos).is(SoulIceBiomes.SOUL_HUSK)) {
                    worldGenLevel.setBlock(underPos, SoulIceBlocks.IGNIDIA_ICE.defaultBlockState(), 2);
                    boolean makeMoreFloor = featurePlaceContext.random().nextInt(0, 256) > 64;
                    while (true) {
                        underPos = underPos.move(Direction.DOWN);
                        makeMoreFloor = worldGenLevel.getBlockState(underPos).is(Blocks.LAVA) &&
                                worldGenLevel.getBiome(underPos).is(SoulIceBiomes.SOUL_HUSK);
                        if (!makeMoreFloor) break;
                        worldGenLevel.setBlock(underPos, SoulIceBlocks.IGNIDIA_ICE.defaultBlockState(), 2);
                    }
                    makeMoreFloor = featurePlaceContext.random().nextInt(0, 256) > 192;
                    while (makeMoreFloor) {
                        abovePos = abovePos.move(Direction.UP);
                        makeMoreFloor = featurePlaceContext.random().nextInt(0, 256) > 192 && worldGenLevel.getBlockState(abovePos).is(Blocks.AIR) &&
                                worldGenLevel.getBiome(abovePos).is(SoulIceBiomes.SOUL_HUSK);
                        worldGenLevel.setBlock(abovePos, SoulIceBlocks.IGNIDIA_ICE.defaultBlockState(), 2);
                    }
                }
            }
        }

        return true;
    }
}
