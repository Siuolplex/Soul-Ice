package io.siuolplex.soul_ice.fabric.util;

import io.siuolplex.soul_ice.SoulIceConfig;
import io.siuolplex.soul_ice.fabric.enchantments.UnfalteringEnchantment;
import io.siuolplex.soul_ice.fabric.registry.SoulIceEnchantments;

public class SoulIceEnchantSyncer {
    public static boolean isUnfalteringEnabled = SoulIceConfig.instance().enableUnfaltering;

    public static void setIsUnfalteringEnabled(boolean bool) {
        isUnfalteringEnabled = bool;
        ((UnfalteringEnchantment)SoulIceEnchantments.UNFALTERING).setEnabled(bool);
    }
}
