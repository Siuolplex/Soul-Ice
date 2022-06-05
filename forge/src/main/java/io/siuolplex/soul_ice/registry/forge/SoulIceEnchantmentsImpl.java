package io.siuolplex.soul_ice.registry.forge;

import io.siuolplex.soul_ice.SoulIce;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.util.registry.Registry;

import java.util.LinkedHashMap;
import java.util.Map;

public class SoulIceEnchantmentsImpl {
    public static Map<String, Enchantment> enchantmentMap = new LinkedHashMap<>();

    public static Enchantment register(String name, Enchantment enchantment) {
        enchantmentMap.put(name, enchantment);
        return enchantment;
    }
}
