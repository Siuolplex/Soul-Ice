package io.siuolplex.soul_ice.forge.util;

import io.siuolplex.soul_ice.SoulIce;
import io.siuolplex.soul_ice.SoulIceConfig;
import io.siuolplex.soul_ice.forge.enchantments.UnfalteringEnchantment;
import io.siuolplex.soul_ice.forge.registry.SoulIceEnchantments;

public class SoulIceEnchantSyncer {
    public static boolean isUnfalteringEnabled = SoulIceConfig.instance().enableUnfaltering;

    public static void setIsUnfalteringEnabled(boolean bool) {
        isUnfalteringEnabled = bool;
        ((UnfalteringEnchantment) SoulIceEnchantments.UNFALTERING.get()).setEnabled(bool);
    }
}

