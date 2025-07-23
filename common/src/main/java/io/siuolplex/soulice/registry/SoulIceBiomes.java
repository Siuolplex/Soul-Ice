package io.siuolplex.soulice.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.biome.Biome;

public class SoulIceBiomes {
    public static final ResourceKey<Biome> SOUL_HUSK = ResourceKey.create(Registries.BIOME, ResourceLocation.fromNamespaceAndPath("soul_ice", "soul_husk"));
}
