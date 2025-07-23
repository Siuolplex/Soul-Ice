package io.siuolplex.soulice.items;

import io.siuolplex.soulice.entity.BakedGlutenBallEntity;
import io.siuolplex.soulice.entity.GlutenBallEntity;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class BakedGlutenBallItem extends GlutenBallItem {
    public BakedGlutenBallItem(Properties properties) {
        super(properties);
    }

    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand interactionHand) {
        ItemStack itemStack = player.getItemInHand(interactionHand);
        player.getCooldowns().addCooldown(this, 15);
        level.playSound(
                null,
                player.getX(),
                player.getY(),
                player.getZ(),
                SoundEvents.SNOWBALL_THROW,
                SoundSource.NEUTRAL,
                0.5F,
                0.4F / (level.getRandom().nextFloat() * 0.4F + 0.5F)
        );
        if (!level.isClientSide) {
            BakedGlutenBallEntity bakedGlutenBall = new BakedGlutenBallEntity(player, level);
            bakedGlutenBall.setItem(itemStack);
            bakedGlutenBall.shootFromRotation(player, player.getXRot(), player.getYRot(), level.getRandom().nextFloat() * 0.1F * (level.getRandom().nextBoolean() ? 1 : -1), 1F, 0.625F);
            level.addFreshEntity(bakedGlutenBall);
        }

        player.awardStat(Stats.ITEM_USED.get(this));
        if (!player.getAbilities().instabuild) {
            itemStack.shrink(1);
        }

        return InteractionResultHolder.sidedSuccess(itemStack, level.isClientSide());
    }
}
