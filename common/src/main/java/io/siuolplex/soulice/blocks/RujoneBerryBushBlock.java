package io.siuolplex.soulice.blocks;

import io.siuolplex.soulice.registry.SoulIceEnchantments;
import io.siuolplex.soulice.registry.SoulIceItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.SweetBerryBushBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;

public class RujoneBerryBushBlock extends SweetBerryBushBlock {
    public RujoneBerryBushBlock(Properties properties) {
        super(properties);
    }

    public ItemStack getCloneItemStack(LevelReader level, BlockPos pos, BlockState state) {

        return new ItemStack(SoulIceItems.RUJONE_BERRIES);
    }

    protected boolean mayPlaceOn(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos) {
        return blockState.is(BlockTags.SAND);
    }

    public void entityInside(BlockState blockState, Level level, BlockPos blockPos, Entity entity) {
        Holder<Enchantment> unfaltering = level.registryAccess().registry(Registries.ENCHANTMENT).get().getHolder(SoulIceEnchantments.UNFALTERING).get();

        if (entity instanceof LivingEntity livingEntity && entity.getType() != EntityType.HUSK && entity.getType() != EntityType.BEE && !(EnchantmentHelper.getEnchantmentLevel(unfaltering, livingEntity) > 0)) {
            entity.makeStuckInBlock(blockState, new Vec3(0.9375, 0.75, 0.9375));
            if (!level.isClientSide && blockState.getValue(AGE) > 0 && (entity.xOld != entity.getX() || entity.zOld != entity.getZ())) {
                double d = Math.abs(entity.getX() - entity.xOld);
                double e = Math.abs(entity.getZ() - entity.zOld);
                if (d >= 0.003 || e >= 0.003) {
                    entity.hurt(level.damageSources().sweetBerryBush(), 3.0F);
                }
            }
        }
    }

    public void randomTick(BlockState blockState, ServerLevel serverLevel, BlockPos blockPos, RandomSource randomSource) {
        if (blockState.getValue(AGE) < 3 && randomSource.nextInt(9) == 0 && serverLevel.getRawBrightness(blockPos.above(), 0) >= 9) {
            BlockState blockState2 = blockState.setValue(AGE, blockState.getValue(AGE) + 1);
            serverLevel.setBlock(blockPos, blockState2, 2);
            serverLevel.gameEvent(GameEvent.BLOCK_CHANGE, blockPos, GameEvent.Context.of(blockState2));
        }
    }

    public InteractionResult useWithoutItem(BlockState blockState, Level level, BlockPos blockPos, Player player, BlockHitResult blockHitResult) {
        int i = blockState.getValue(AGE);
        boolean bl = i == 3;
        if (!bl && player.getItemInHand(player.getUsedItemHand()).is(Items.BONE_MEAL)) {
            return InteractionResult.PASS;
        } else if (i > 1) {
            int j = 1 + level.random.nextInt(2);
            popResource(level, blockPos, new ItemStack(SoulIceItems.RUJONE_BERRIES, j + (bl ? 1 : 0)));
            level.playSound(null, blockPos, SoundEvents.SWEET_BERRY_BUSH_PICK_BERRIES, SoundSource.BLOCKS, 1.0F, 0.6F + level.random.nextFloat() * 0.4F);
            BlockState blockState2 = blockState.setValue(AGE, 1);
            level.setBlock(blockPos, blockState2, 2);
            level.gameEvent(GameEvent.BLOCK_CHANGE, blockPos, GameEvent.Context.of(player, blockState2));
            return InteractionResult.sidedSuccess(level.isClientSide);
        } else {
            return super.useWithoutItem(blockState, level, blockPos, player, blockHitResult);
        }
    }
}
