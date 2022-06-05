package io.siuolplex.soul_ice.registry.fabric;

import io.siuolplex.soul_ice.SoulIce;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.util.registry.Registry;

public class SoulIceEnchantmentsImpl {
    public static Enchantment register(String name, Enchantment enchantment) {
        return Registry.register(Registry.ENCHANTMENT, SoulIce.idFormatter(name), enchantment);
    }
}
