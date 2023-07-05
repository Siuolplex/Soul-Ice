package io.siuolplex.soulice.enchantments;

import io.siuolplex.soulice.registry.SoulIceEnchantments;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class UnfalteringEnchantment extends Enchantment {
    public UnfalteringEnchantment() {
        super(Enchantment.Rarity.UNCOMMON, EnchantmentCategory.ARMOR_FEET, new EquipmentSlot[]{EquipmentSlot.FEET});
    }

    @Override
    protected boolean checkCompatibility(Enchantment enchantment) {
        return super.checkCompatibility(enchantment) && enchantment != SoulIceEnchantments.SLIPPERINESS;
    }

    @Override
    public int getMaxLevel() {
        return 1;
    }
}
