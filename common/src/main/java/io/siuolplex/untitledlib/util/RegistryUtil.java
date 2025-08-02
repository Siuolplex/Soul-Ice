package io.siuolplex.untitledlib.util;

import net.minecraft.core.Holder;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.enchantment.Enchantment;

public class RegistryUtil {
    public static Holder<Enchantment> grabEnchantFromRegistry(RegistryAccess access, ResourceKey<Enchantment> resourceKey) {
        return access.lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(resourceKey);
    }

}
