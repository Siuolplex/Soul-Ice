package io.siuolplex.soul_ice.forge.util;

import io.siuolplex.soul_ice.SoulIce;
import io.siuolplex.soul_ice.SoulIceConfig;
import io.siuolplex.soul_ice.forge.enchantments.FreezingEnchantment;
import io.siuolplex.soul_ice.forge.enchantments.UnfalteringEnchantment;
import io.siuolplex.soul_ice.forge.registry.SoulIceEnchantments;

public class SoulIceEnchantSyncer {
    public static boolean isUnfalteringEnabled = SoulIceConfig.instance().enableUnfaltering;
    public static boolean isFreezingEnabled = SoulIceConfig.instance().enableFreezing;

    public static void setIsUnfalteringEnabled(boolean bool) {
        isUnfalteringEnabled = bool;
        SoulIce.LOGGER.info("Unfaltering set to " + bool);
        ((UnfalteringEnchantment)SoulIceEnchantments.UNFALTERING.get()).setEnabled(bool);
    }

    public static void setIsFreezingEnabled(boolean bool) {
        isFreezingEnabled = bool;
        SoulIce.LOGGER.info("Freezing set to " + bool);
        ((FreezingEnchantment)SoulIceEnchantments.FREEZING.get()).setEnabled(bool);
    }
}

