package io.siuolplex.soulice.registry;

import io.siuolplex.soulice.enchantments.SlipperinessEnchantment;
import io.siuolplex.soulice.enchantments.UnfalteringEnchantment;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.block.Block;

public class SoulIceEnchantments {
    public static final Enchantment SLIPPERINESS = register("slipperiness", new SlipperinessEnchantment());
    public static final Enchantment UNFALTERING = register("unfaltering", new UnfalteringEnchantment());

    private static Enchantment register(String name, Enchantment enchantment) {
        return Registry.register(BuiltInRegistries.ENCHANTMENT, new ResourceLocation("soul_ice", name), enchantment);
    }

    public static void init() {}
}
