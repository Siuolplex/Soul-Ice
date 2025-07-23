package io.siuolplex.soulice.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.enchantment.Enchantment;

public class SoulIceEnchantments {
    public static final ResourceKey<Enchantment> UNFALTERING = ResourceKey.create(Registries.ENCHANTMENT, ResourceLocation.fromNamespaceAndPath("soul_ice", "unfaltering"));
    public static final ResourceKey<Enchantment> SLIPPERINESS = ResourceKey.create(Registries.ENCHANTMENT, ResourceLocation.fromNamespaceAndPath("soul_ice", "slipperiness"));
}
