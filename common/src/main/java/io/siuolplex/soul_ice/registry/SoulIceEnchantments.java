package io.siuolplex.soul_ice.registry;

import dev.architectury.injectables.annotations.ExpectPlatform;
import io.siuolplex.soul_ice.enchantments.FreezingEnchantment;
import io.siuolplex.soul_ice.enchantments.UnfalteringEnchantment;
import io.siuolplex.soul_ice.util.SoulIceIDHandler;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.util.registry.Registry;

public class SoulIceEnchantments {
    public static Enchantment UNFALTERING = register("unfaltering", new UnfalteringEnchantment());
    public static Enchantment FREEZING = register("freezing", new FreezingEnchantment());

    @ExpectPlatform
    private static Enchantment register(String name, Enchantment enchantment) {
        throw new RuntimeException("Architectury failed!");
    }

    public static void init() {}
}
