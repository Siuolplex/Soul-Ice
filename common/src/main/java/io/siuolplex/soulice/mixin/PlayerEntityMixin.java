package io.siuolplex.soulice.mixin;

import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static io.siuolplex.soulice.registry.SoulIceItems.*;

@Mixin(Player.class)
public abstract class PlayerEntityMixin extends LivingEntity {
    protected PlayerEntityMixin(EntityType<? extends LivingEntity> entityType, Level level) {
        super(entityType, level);
    }

    @Shadow
    public abstract Inventory getInventory();

    @Inject(method = "hurt", at = @At("TAIL"))
    public void soulIce$damage(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        if (!source.is(DamageTypes.MAGIC) && !source.is(DamageTypes.EXPLOSION) && !source.is(DamageTypes.MOB_PROJECTILE) && source.getEntity() instanceof LivingEntity attackyThing) {
            if (soul_Ice$cactusArmorCheck()) {
                attackyThing.hurt(this.damageSources().thorns(this), 1F); //todo: Make this a non-magical thorns
            } else if (soul_Ice$hydratedCactusArmorCheck()) {
                attackyThing.hurt(this.damageSources().thorns(this), 4F);
            }
        }
    }


    @Inject(method = "tick", at = @At("TAIL"))
    public void soulIce$tick(CallbackInfo ci) {
        if (isInWaterRainOrBubble() && soul_Ice$cactusArmorCheck()) soul_Ice$letsMoistenItUp();
        else if (isOnFire() && !fireImmune() && soul_Ice$hydratedCactusArmorCheck()) soul_Ice$demoistify();
    }

    @Unique
    public void soul_Ice$letsMoistenItUp() {
        Inventory inv = this.getInventory();
        for (int slot = 0; slot < 4; slot++) {
            ItemStack armorPiece = inv.getArmor(slot);
            Holder<Item> heldItem = null;
            boolean isCactus = false;
            switch (slot) {
                case 0 -> {
                    heldItem = Holder.direct(HYDRATED_CACTUS_BOOTS);
                    isCactus = armorPiece.is(CACTUS_BOOTS);
                }
                case 1 -> {
                    heldItem = Holder.direct(HYDRATED_CACTUS_LEGGINGS);
                    isCactus = armorPiece.is(CACTUS_LEGGINGS);
                }
                case 2 -> {
                    heldItem = Holder.direct(HYDRATED_CACTUS_CHESTPLATE);
                    isCactus = armorPiece.is(CACTUS_CHESTPLATE);
                }
                case 3 -> {
                    heldItem = Holder.direct(HYDRATED_CACTUS_HELMET);
                    isCactus = armorPiece.is(CACTUS_HELMET);
                }
            }

            if (!isCactus) continue;

            armorPiece = new ItemStack(heldItem, 1, armorPiece.getComponentsPatch());
            armorPiece.set(DataComponents.DAMAGE, armorPiece.get(DataComponents.DAMAGE) * 4);
            inv.armor.set(slot, armorPiece);
            inv.setChanged();
        }

        if (level().isClientSide()) {
            playSound(SoundEvents.SPONGE_ABSORB, 5, 0.5f);
        }
    }

    @Unique
    public void soul_Ice$demoistify() {
        Inventory inv = this.getInventory();
        for (int slot = 0; slot < 4; slot++) {
            ItemStack armorPiece = inv.getArmor(slot);
            Holder<Item> heldItem = null;
            boolean isCactus = false;
            switch (slot) {
                case 0 -> {
                    heldItem = Holder.direct(CACTUS_BOOTS);
                    isCactus = armorPiece.is(HYDRATED_CACTUS_BOOTS);
                }
                case 1 -> {
                    heldItem = Holder.direct(CACTUS_LEGGINGS);
                    isCactus = armorPiece.is(HYDRATED_CACTUS_LEGGINGS);
                }
                case 2 -> {
                    heldItem = Holder.direct(CACTUS_CHESTPLATE);
                    isCactus = armorPiece.is(HYDRATED_CACTUS_CHESTPLATE);
                }
                case 3 -> {
                    heldItem = Holder.direct(CACTUS_HELMET);
                    isCactus = armorPiece.is(HYDRATED_CACTUS_HELMET);
                }
            }

            if (!isCactus) continue;

            armorPiece = new ItemStack(heldItem, 1, armorPiece.getComponentsPatch());
            armorPiece.set(DataComponents.DAMAGE, armorPiece.get(DataComponents.DAMAGE) / 4);
            inv.armor.set(slot, armorPiece);
            inv.setChanged();
        }

        if (level().isClientSide()) {
            playSound(SoundEvents.FIRE_EXTINGUISH, 5, 1.5f);
        }
    }

    @Unique
    public boolean soul_Ice$cactusArmorCheck() {
        Inventory a = this.getInventory();
        return (a.getArmor(3).is(CACTUS_HELMET) || a.getArmor(2).is(CACTUS_CHESTPLATE) || a.getArmor(1).is(CACTUS_LEGGINGS) || a.getArmor(0).is(CACTUS_BOOTS));
    }

    @Unique
    public boolean soul_Ice$hydratedCactusArmorCheck() {
        Inventory a = this.getInventory();
        return (a.getArmor(3).is(HYDRATED_CACTUS_HELMET) || a.getArmor(2).is(HYDRATED_CACTUS_CHESTPLATE) || a.getArmor(1).is(HYDRATED_CACTUS_LEGGINGS) || a.getArmor(0).is(HYDRATED_CACTUS_BOOTS));
    }
}
