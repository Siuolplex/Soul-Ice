package io.siuolplex.soulice.items;

import io.siuolplex.soulice.entity.GlutenBallEntity;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SnowballItem;
import net.minecraft.world.level.Level;

public class NineVoltBatteryItem extends SnowballItem {
    public NineVoltBatteryItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
        if (player.isCrouching()) {
            player.hurt(player.damageSources().lightningBolt(), 2);
            player.hurtDuration /= 2;
            player.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 200, 1));
            player.addEffect(new MobEffectInstance(MobEffects.POISON, 100, 1));
            player.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 400));
            player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 200, 1));
            player.addEffect(new MobEffectInstance(MobEffects.WITHER, 200, 1));
            player.addEffect(new MobEffectInstance(MobEffects.GLOWING, 400));
            player.stopUsingItem();
            return InteractionResultHolder.success(player.getItemInHand(usedHand));
        } else {
            ItemStack itemStack = player.getItemInHand(usedHand);
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
                GlutenBallEntity glutenBall = new GlutenBallEntity(player, level);
                glutenBall.setItem(itemStack);
                glutenBall.shootFromRotation(player, player.getXRot(), player.getYRot(), level.getRandom().nextFloat() * 0.1F * (level.getRandom().nextBoolean() ? 1 : -1), 1.5F, 0.75F);
                level.addFreshEntity(glutenBall);
            }

            player.awardStat(Stats.ITEM_USED.get(this));
            if (!player.getAbilities().instabuild) {
                itemStack.shrink(1);
            }

            return InteractionResultHolder.sidedSuccess(itemStack, level.isClientSide());
        }
    }
}
