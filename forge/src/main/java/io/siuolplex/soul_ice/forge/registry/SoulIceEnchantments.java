package io.siuolplex.soul_ice.forge.registry;

import io.siuolplex.soul_ice.forge.enchantments.UnfalteringEnchantment;
import net.minecraft.enchantment.Enchantment;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class SoulIceEnchantments {
    public static final DeferredRegister<Enchantment> ENCHANTMENTS = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, "soul_ice");

    public static RegistryObject<Enchantment> UNFALTERING = ENCHANTMENTS.register("unfaltering", ()-> new UnfalteringEnchantment());

    private static Enchantment register(String name, Enchantment enchantment) {
        RegistryObject<Enchantment> enchantmentSupplied = ENCHANTMENTS.register(name, () -> enchantment);
        return enchantment;
    }
}
