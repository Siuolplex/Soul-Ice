package io.siuolplex.soul_ice.util;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.math.BlockPos;

public class SoulIceVelocityFixer {
    public static BlockPos velocityFix(Entity entity) {
        double yvalue = entity.getBoundingBox().minY - 0.2500001;
        BlockState underBlock = entity.getWorld().getBlockState(entity.getBlockPos().down());
        BlockPos velocityAffectingPos;
        if (underBlock.isIn(BlockTags.WALLS) || underBlock.isIn(BlockTags.FENCE_GATES) || underBlock.isIn(BlockTags.FENCES)) yvalue = entity.getBoundingBox().minY - 0.5000001;
        velocityAffectingPos = new BlockPos(entity.getBlockPos().getX(), yvalue, entity.getBlockPos().getZ());
        return velocityAffectingPos;
    }
}
