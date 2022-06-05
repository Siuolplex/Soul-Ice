package io.siuolplex.soul_ice.util;

import io.siuolplex.soul_ice.enchantments.FreezingEnchantment;
import io.siuolplex.soul_ice.enchantments.UnfalteringEnchantment;
import io.siuolplex.soul_ice.mixin.AbstractBlockAccessor;
import io.siuolplex.soul_ice.registry.SoulIceEnchantments;

import static io.siuolplex.soul_ice.registry.SoulIceBlocks.*;

public class ConfigSyncer {
    public static boolean isUnfalteringEnabled = SoulIceConfig.enableUnfaltering;
    public static boolean isFreezingEnabled = SoulIceConfig.enableFreezing;

    public static void setIsUnfalteringEnabled(boolean bool) {
        isUnfalteringEnabled = bool;
        ((UnfalteringEnchantment) SoulIceEnchantments.UNFALTERING).setEnabled(bool);
    }

    public static void setIsFreezingEnabled(boolean bool) {
        isFreezingEnabled = bool;
        ((FreezingEnchantment)SoulIceEnchantments.FREEZING).setEnabled(bool);
    }

    public static void soulIceSlip(float slipperiness) {
        ((AbstractBlockAccessor)SOUL_ICE).setSlipperiness(slipperiness);
        ((AbstractBlockAccessor)SOUL_ICE_SLAB).setSlipperiness(slipperiness);
        ((AbstractBlockAccessor)SOUL_ICE_STAIRS).setSlipperiness(slipperiness);
        ((AbstractBlockAccessor)SOUL_ICE_WALL).setSlipperiness(slipperiness);
        ((AbstractBlockAccessor)SOUL_ICE_GATE).setSlipperiness(slipperiness);
        ((AbstractBlockAccessor)POLISHED_SOUL_ICE).setSlipperiness(slipperiness);
        ((AbstractBlockAccessor)POLISHED_SOUL_ICE_SLAB).setSlipperiness(slipperiness);
        ((AbstractBlockAccessor)POLISHED_SOUL_ICE_STAIRS).setSlipperiness(slipperiness);
        ((AbstractBlockAccessor)POLISHED_SOUL_ICE_WALL).setSlipperiness(slipperiness);
        ((AbstractBlockAccessor)POLISHED_SOUL_ICE_GATE).setSlipperiness(slipperiness);
        ((AbstractBlockAccessor)SOUL_ICE_BRICKS).setSlipperiness(slipperiness);
        ((AbstractBlockAccessor)SOUL_ICE_BRICK_SLAB).setSlipperiness(slipperiness);
        ((AbstractBlockAccessor)SOUL_ICE_BRICK_STAIRS).setSlipperiness(slipperiness);
        ((AbstractBlockAccessor)SOUL_ICE_BRICK_WALL).setSlipperiness(slipperiness);
        ((AbstractBlockAccessor)SOUL_ICE_BRICK_GATE).setSlipperiness(slipperiness);
    }
}
