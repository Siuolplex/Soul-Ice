package io.siuolplex.soulice.entity;

import com.mojang.util.UndashedUuid;
import io.siuolplex.soulice.registry.SoulIceEntityTypes;
import io.siuolplex.soulice.registry.SoulIceItems;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Shulker;
import net.minecraft.world.entity.monster.piglin.AbstractPiglin;
import net.minecraft.world.entity.monster.warden.Warden;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;

import java.util.Random;

public class BatteryEntity extends ThrowableItemProjectile {
    public BatteryEntity(EntityType<? extends BatteryEntity> entityType, Level level) {
        super(entityType, level);
    }

    public BatteryEntity(double d, double e, double f, Level level) {
        super(SoulIceEntityTypes.GLUTEN_BALL, d, e, f, level);
    }

    public BatteryEntity(LivingEntity livingEntity, Level level) {
        super(SoulIceEntityTypes.GLUTEN_BALL, livingEntity, level);
    }

    @Override
    protected Item getDefaultItem() {
        return SoulIceItems.NINE_VOLT_BATTERY;
    }

    @Override
    public void handleEntityEvent(byte b) {
        super.handleEntityEvent(b);
        // ADD PARTICLES
    }

    @Override
    protected void onHitEntity(EntityHitResult entityHitResult) {
        Entity entity = entityHitResult.getEntity();
        if (entity instanceof LivingEntity livingEntity) {
            livingEntity.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 200));
            livingEntity.addEffect(new MobEffectInstance(MobEffects.POISON, 100));
            livingEntity.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 200));
            livingEntity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 200));
            livingEntity.addEffect(new MobEffectInstance(MobEffects.WITHER, 200));
            livingEntity.addEffect(new MobEffectInstance(MobEffects.GLOWING, 200));
        }
        entity.hurt(entity.damageSources().thrown(this, this.getOwner()), 0f);
        super.onHitEntity(entityHitResult);
    }

    @Override
    protected void onHit(HitResult hitResult) {
        super.onHit(hitResult);
        if (!level().isClientSide) {
            level().broadcastEntityEvent(this, (byte)3);
            this.discard();
        }
    }
}
