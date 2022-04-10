package io.siuolplex.soul_ice.forge.enchantments;

import io.siuolplex.soul_ice.forge.util.SoulIceEnchantSyncer;
import io.siuolplex.soul_ice.mixin.EnchantmentAccessor;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.enchantment.FireAspectEnchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;

public class FreezingEnchantment extends Enchantment {
    public boolean enabled = SoulIceEnchantSyncer.isFreezingEnabled;

    public FreezingEnchantment() {
        super(Rarity.UNCOMMON, (SoulIceEnchantSyncer.isFreezingEnabled) ? EnchantmentTarget.WEAPON : null, new EquipmentSlot[] {EquipmentSlot.MAINHAND});
    }

    public void setEnabled(boolean setTo) {
        ((EnchantmentAccessor) this).setType((setTo) ? EnchantmentTarget.WEAPON : null);
        this.enabled = setTo;
    }

    @Override
    public int getMinPower(int level) {
        return 5 + 10 * (level);
    }

    @Override
    public int getMaxPower(int level) {
        return super.getMinPower(level) + 20;
    }

    @Override
    public void onTargetDamaged(LivingEntity user, Entity target, int level) {
        if (target instanceof LivingEntity livingTarget) {
            if (target.isOnFire()) livingTarget.extinguish();
            else livingTarget.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, level * 100, 0));

        }
    }

    @Override
    protected boolean canAccept(Enchantment other) {
        if (enabled) return !(other instanceof FireAspectEnchantment);
        return false;
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }

    @Override
    public boolean isAcceptableItem(ItemStack stack) {
        return enabled && super.isAcceptableItem(stack);
    }

    @Override
    public boolean isAvailableForRandomSelection() {
        return enabled;
    }

    @Override
    public boolean isTreasure() {return false;}
}
