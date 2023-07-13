package io.siuolplex.soulice.mixin;

import net.minecraft.core.RegistryAccess;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageSources;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
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
            if (cactusArmorCheck()) {
                attackyThing.hurt(this.damageSources().thorns(this), 1F); //todo: Make this a non-magical thorns
            } else if (hydratedCactusArmorCheck()) {
                attackyThing.hurt(this.damageSources().thorns(this), 4F);
            }
        }
    }


    @Inject(method = "tick", at = @At("TAIL"))
    public void soulIce$tick(CallbackInfo ci) {
        if (isInWaterRainOrBubble() && cactusArmorCheck()) letsMoistenItUp();
        else if (isOnFire() && !fireImmune() && hydratedCactusArmorCheck()) demoistify();
    }

    // Optimizable
    public void letsMoistenItUp() {
        Inventory a = this.getInventory();
        CompoundTag item = new CompoundTag();
        if (a.getArmor(0).is(CACTUS_BOOTS)) {
            item = a.getArmor(0).save(item);
            item.putString("id", "soul_ice:hydrated_cactus_boots");
            CompoundTag tag = item.getCompound("tag");
            tag.putInt("Damage", (tag.getInt("Damage")) * 4);
            a.setItem(36, ItemStack.of(item));
            a.setChanged();
        }
        if (a.getArmor(1).is(CACTUS_LEGGINGS)) {
            item = a.getArmor(1).save(item);
            item.putString("id", "soul_ice:hydrated_cactus_leggings");
            CompoundTag tag = item.getCompound("tag");
            tag.putInt("Damage", (tag.getInt("Damage")) * 4);
            a.setItem(37, ItemStack.of(item));
            a.setChanged();
        }
        if (a.getArmor(2).is(CACTUS_CHESTPLATE)) {
            item = a.getArmor(2).save(item);
            item.putString("id", "soul_ice:hydrated_cactus_chestplate");
            CompoundTag tag = item.getCompound("tag");
            tag.putInt("Damage", (tag.getInt("Damage")) * 4);
            a.setItem(38, ItemStack.of(item));
            a.setChanged();
        }
        if (a.getArmor(3).is(CACTUS_HELMET)) {
            item = a.getArmor(3).save(item);
            item.putString("id", "soul_ice:hydrated_cactus_helmet");
            CompoundTag tag = item.getCompound("tag");
            tag.putInt("Damage", (tag.getInt("Damage")) * 4);
            a.setItem(39, ItemStack.of(item));
            a.setChanged();
        }
    }

    public void demoistify() {
        Inventory a = this.getInventory();
        CompoundTag item = new CompoundTag();
        if (a.getArmor(0).is(HYDRATED_CACTUS_BOOTS)) {
            item = a.getArmor(0).save(item);
            item.putString("id", "soul_ice:cactus_boots");
            CompoundTag tag = item.getCompound("tag");
            tag.putInt("Damage", (tag.getInt("Damage")) / 4);
            a.setItem(36, ItemStack.of(item));
            a.setChanged();
        }
        if (a.getArmor(1).is(HYDRATED_CACTUS_LEGGINGS)) {
            item = a.getArmor(1).save(item);
            item.putString("id", "soul_ice:cactus_leggings");
            CompoundTag tag = item.getCompound("tag");
            tag.putInt("Damage", (tag.getInt("Damage")) / 4);
            a.setItem(37, ItemStack.of(item));
            a.setChanged();
        }
        if (a.getArmor(2).is(HYDRATED_CACTUS_CHESTPLATE)) {
            item = a.getArmor(2).save(item);
            item.putString("id", "soul_ice:cactus_chestplate");
            CompoundTag tag = item.getCompound("tag");
            tag.putInt("Damage", (tag.getInt("Damage")) / 4);
            a.setItem(38, ItemStack.of(item));
            a.setChanged();
        }
        if (a.getArmor(3).is(HYDRATED_CACTUS_HELMET)) {
            item = a.getArmor(3).save(item);
            item.putString("id", "soul_ice:cactus_helmet");
            CompoundTag tag = item.getCompound("tag");
            tag.putInt("Damage", (tag.getInt("Damage")) / 4);
            a.setItem(39, ItemStack.of(item));
            a.setChanged();
        }
    }

    public boolean cactusArmorCheck() {
        Inventory a = this.getInventory();
        return (a.getArmor(3).is(CACTUS_HELMET) || a.getArmor(2).is(CACTUS_CHESTPLATE) || a.getArmor(1).is(CACTUS_LEGGINGS) || a.getArmor(0).is(CACTUS_BOOTS));
    }

    public boolean hydratedCactusArmorCheck() {
        Inventory a = this.getInventory();
        return (a.getArmor(3).is(HYDRATED_CACTUS_HELMET) || a.getArmor(2).is(HYDRATED_CACTUS_CHESTPLATE) || a.getArmor(1).is(HYDRATED_CACTUS_LEGGINGS) || a.getArmor(0).is(HYDRATED_CACTUS_BOOTS));
    }
}
