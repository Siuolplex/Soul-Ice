package io.siuolplex.soul_ice.fabric.registry;

import io.siuolplex.soul_ice.fabric.enchantments.FreezingEnchantment;
import io.siuolplex.soul_ice.fabric.enchantments.UnfalteringEnchantment;
import io.siuolplex.soul_ice.util.SoulIceIDHandler;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.util.registry.Registry;

public class SoulIceEnchantments {
    public static Enchantment UNFALTERING = register("unfaltering", new UnfalteringEnchantment());
    public static Enchantment FREEZING = register("freezing", new FreezingEnchantment());

    private static Enchantment register(String name, Enchantment enchantment) {
        return Registry.register(Registry.ENCHANTMENT, SoulIceIDHandler.idFormatter(name), enchantment);
    }

    public static void init() {}
}
