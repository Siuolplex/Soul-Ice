package io.siuolplex.soulice.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.HalfTransparentBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class IgnidiaIceBlock extends HalfTransparentBlock {
    public IgnidiaIceBlock(Properties properties) {
        super(properties);
    }

    public static BlockState meltsInto() {
        return Blocks.LAVA.defaultBlockState();
    }


    public void playerDestroy(Level level, Player player, BlockPos blockPos, BlockState blockState, @Nullable BlockEntity blockEntity, ItemStack itemStack) {
        super.playerDestroy(level, player, blockPos, blockState, blockEntity, itemStack);
        if (EnchantmentHelper.getItemEnchantmentLevel(Enchantments.SILK_TOUCH, itemStack) == 0) {
            BlockState blockState2 = level.getBlockState(blockPos.below());
            if (blockState2.blocksMotion() || blockState2.liquid()) {
                level.setBlockAndUpdate(blockPos, meltsInto());
            }
        }
    }
}
