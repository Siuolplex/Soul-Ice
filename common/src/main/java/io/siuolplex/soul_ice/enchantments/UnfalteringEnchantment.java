package io.siuolplex.soul_ice.enchantments;

import io.siuolplex.soul_ice.util.ConfigSyncer;
import io.siuolplex.soul_ice.mixin.EnchantmentAccessor;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.enchantment.SoulSpeedEnchantment;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;

public class UnfalteringEnchantment extends Enchantment {
    public boolean enabled = ConfigSyncer.isUnfalteringEnabled;

    public UnfalteringEnchantment() {
        super(Rarity.UNCOMMON, (ConfigSyncer.isUnfalteringEnabled) ? EnchantmentTarget.ARMOR_FEET : null, new EquipmentSlot[] {EquipmentSlot.FEET});
    }

    public void setEnabled(boolean setTo) {
        ((EnchantmentAccessor) this).setType((setTo) ? EnchantmentTarget.ARMOR_FEET : null);
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
    protected boolean canAccept(Enchantment other) {
        if (enabled) return !(other instanceof SoulSpeedEnchantment);
        return false;
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
