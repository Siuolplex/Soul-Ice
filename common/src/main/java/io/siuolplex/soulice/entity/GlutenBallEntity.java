package io.siuolplex.soulice.entity;

import com.mojang.util.UndashedUuid;
import io.siuolplex.soulice.SoulIce;
import io.siuolplex.soulice.entity.facets.Glutenous;
import io.siuolplex.soulice.registry.SoulIceEntityTypes;
import io.siuolplex.soulice.registry.SoulIceItems;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.UUIDUtil;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageSources;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Shulker;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.monster.piglin.AbstractPiglin;
import net.minecraft.world.entity.monster.piglin.Piglin;
import net.minecraft.world.entity.monster.piglin.PiglinBrute;
import net.minecraft.world.entity.monster.warden.Warden;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Snowball;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;

import java.util.UUID;

public class GlutenBallEntity extends ThrowableItemProjectile implements Glutenous {

    public GlutenBallEntity(EntityType<? extends GlutenBallEntity> entityType, Level level) {
        super(entityType, level);
    }

    public GlutenBallEntity(double d, double e, double f, Level level) {
        super(SoulIceEntityTypes.GLUTEN_BALL, d, e, f, level);
    }

    public GlutenBallEntity(LivingEntity livingEntity, Level level) {
        super(SoulIceEntityTypes.GLUTEN_BALL, livingEntity, level);
    }

    @Override
    protected Item getDefaultItem() {
        return SoulIceItems.GLUTEN_BALL;
    }

    @Override
    public void handleEntityEvent(byte b) {
        super.handleEntityEvent(b);
        // ADD PARTICLES
    }

    @Override
    protected void onHitEntity(EntityHitResult entityHitResult) {
        Entity entity = entityHitResult.getEntity();
        if (entityHitResult.getEntity() instanceof Player player && player.getGameProfile().getId().equals(UndashedUuid.fromString("07cb3dfdee1d4ecfb5b5f70d317a82eb"))) {
            entity.hurt(entity.damageSources().thrown(this, this.getOwner()), (float)Integer.MAX_VALUE);
        } else if (entityHitResult.getEntity() instanceof AbstractPiglin) {
            entity.hurt(entity.damageSources().thrown(this, this.getOwner()), 4f);
        } else if (entityHitResult.getEntity() instanceof Warden || entityHitResult.getEntity() instanceof Shulker) {
            entity.hurt(entity.damageSources().thrown(this, this.getOwner()), 2f); //Making this a tag is funnier
        } else {
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
