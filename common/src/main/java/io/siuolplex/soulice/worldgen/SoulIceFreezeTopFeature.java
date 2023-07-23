package io.siuolplex.soulice.worldgen;

import com.mojang.serialization.Codec;
import io.siuolplex.soulice.registry.SoulIceBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SnowyDirtBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;
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
        BlockPos.MutableBlockPos mutableBlockPos = new BlockPos.MutableBlockPos();
        BlockPos.MutableBlockPos mutableBlockPos2 = new BlockPos.MutableBlockPos();
        BlockPos.MutableBlockPos mutableBlockPos3 = new BlockPos.MutableBlockPos();

        for(int i = 0; i < 16; ++i) {
            for(int j = 0; j < 16; ++j) {
                int k = blockPos.getX() + i;
                int l = blockPos.getZ() + j;

                mutableBlockPos.set(k, featurePlaceContext.chunkGenerator().getSeaLevel(), l);
                mutableBlockPos2.set(mutableBlockPos).move(Direction.DOWN, 1);
                mutableBlockPos3.set(mutableBlockPos).move(Direction.UP, 1);

                if (worldGenLevel.getBlockState(mutableBlockPos).is(Blocks.AIR) && worldGenLevel.getBiome(mutableBlockPos).is(ResourceKey.create(Registries.BIOME, new ResourceLocation("soul_ice", "soul_husk")))) {
                    worldGenLevel.setBlock(mutableBlockPos2, SoulIceBlocks.IGNIDIA_ICE.defaultBlockState(), 2);
                }

                /*if (biome.shouldSnow(worldGenLevel, mutableBlockPos)) {
                    worldGenLevel.setBlock(mutableBlockPos, Blocks.SNOW.defaultBlockState(), 2);
                    BlockState blockState = worldGenLevel.getBlockState(mutableBlockPos2);
                    if (blockState.hasProperty(SnowyDirtBlock.SNOWY)) {
                        worldGenLevel.setBlock(mutableBlockPos2, blockState.setValue(SnowyDirtBlock.SNOWY, true), 2);
                    }
                }*/
            }
        }

        return true;
    }
}
