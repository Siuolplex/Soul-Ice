package io.siuolplex.soul_ice.quilt.util;

import io.siuolplex.soul_ice.SoulIceConfig;
import io.siuolplex.soul_ice.quilt.enchantments.FreezingEnchantment;
import io.siuolplex.soul_ice.quilt.enchantments.UnfalteringEnchantment;
import io.siuolplex.soul_ice.quilt.registry.SoulIceEnchantments;

public class SoulIceEnchantSyncer {
    public static boolean isUnfalteringEnabled = SoulIceConfig.instance().enableUnfaltering;
    public static boolean isFreezingEnabled = SoulIceConfig.instance().enableFreezing;

    public static void setIsUnfalteringEnabled(boolean bool) {
        isUnfalteringEnabled = bool;
        ((UnfalteringEnchantment)SoulIceEnchantments.UNFALTERING).setEnabled(bool);
    }

    public static void setIsFreezingEnabled(boolean bool) {
        isFreezingEnabled = bool;
        ((FreezingEnchantment)SoulIceEnchantments.FREEZING).setEnabled(bool);
    }
}
