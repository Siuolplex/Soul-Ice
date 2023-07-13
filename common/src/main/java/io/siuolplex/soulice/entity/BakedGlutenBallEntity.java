package io.siuolplex.soulice.entity;

import com.mojang.blaze3d.shaders.Effect;
import io.siuolplex.soulice.entity.facets.Glutenous;
import io.siuolplex.soulice.registry.SoulIceEntityTypes;
import io.siuolplex.soulice.registry.SoulIceItems;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Shulker;
import net.minecraft.world.entity.monster.piglin.AbstractPiglin;
import net.minecraft.world.entity.monster.piglin.Piglin;
import net.minecraft.world.entity.monster.piglin.PiglinBrute;
import net.minecraft.world.entity.monster.warden.Warden;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;

public class BakedGlutenBallEntity extends ThrowableItemProjectile implements Glutenous {
    public BakedGlutenBallEntity(EntityType<? extends ThrowableItemProjectile> entityType, Level level) {
        super(entityType, level);
    }

    public BakedGlutenBallEntity(double d, double e, double f, Level level) {
        super(SoulIceEntityTypes.BAKED_GLUTEN_BALL, d, e, f, level);
    }

    public BakedGlutenBallEntity(LivingEntity livingEntity, Level level) {
        super(SoulIceEntityTypes.BAKED_GLUTEN_BALL, livingEntity, level);
    }

    protected float getGravity() {
        return 0.03f;
    }

    @Override
    protected Item getDefaultItem() {
        return SoulIceItems.BAKED_GLUTEN_BALL;
    }

    @Override
    public void handleEntityEvent(byte b) {
        super.handleEntityEvent(b);
        // ADD PARTICLES
    }

    @Override
    protected void onHitEntity(EntityHitResult entityHitResult) {
        Entity entity = entityHitResult.getEntity();
        if (entityHitResult.getEntity() instanceof Player player && (player.getName().getString().equals("Siuolplex"))) {
            entity.hurt(entity.damageSources().thrown(this, this.getOwner()), (float)Integer.MAX_VALUE);
        } else if (entityHitResult.getEntity() instanceof AbstractPiglin) {
            entity.hurt(entity.damageSources().thrown(this, this.getOwner()), 4f);
        } else if (entityHitResult.getEntity() instanceof Warden || entityHitResult.getEntity() instanceof Shulker) {
            entity.hurt(entity.damageSources().thrown(this, this.getOwner()), 2f); //Making this a tag is funnier
        } else {
            if (entity instanceof Player player && player.getFoodData().getFoodLevel() < 20) {
                player.addEffect(new MobEffectInstance(MobEffects.SATURATION, 5));
            }
            entity.hurt(entity.damageSources().thrown(this, this.getOwner()), 0f);
        }
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
