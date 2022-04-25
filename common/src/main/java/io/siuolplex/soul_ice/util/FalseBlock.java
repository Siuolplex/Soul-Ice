package io.siuolplex.soul_ice.util;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.Items;

// From https://github.com/ConsistencyPlus/ConsistencyPlus/blob/master/common/src/main/java/io/github/consistencyplus/consistency_plus/blocks/FalseBlock.java, licensed under LGPLv3.
// This class file serves as a way to allow for storage of BlockSettings in loop based registration solutions.
public class FalseBlock extends AbstractBlock {
    public FalseBlock(Settings settings) {
        super(settings);
    }

    @Override
    public Item asItem() {
        return Items.AIR;
    }

    @Override
    protected Block asBlock() {
        return Blocks.AIR;
    }
}
