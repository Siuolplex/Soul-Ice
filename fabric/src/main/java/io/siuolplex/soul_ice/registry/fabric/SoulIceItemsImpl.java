package io.siuolplex.soul_ice.registry.fabric;

import io.siuolplex.soul_ice.SoulIce;
import net.minecraft.item.Item;
import net.minecraft.util.registry.Registry;

public class SoulIceItemsImpl {
    public static Item register(String name, Item item) {
       return Registry.register(Registry.ITEM, SoulIce.idFormatter(name), item);
    }
}
