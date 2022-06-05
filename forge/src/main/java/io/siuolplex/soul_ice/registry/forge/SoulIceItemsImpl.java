package io.siuolplex.soul_ice.registry.forge;

import io.siuolplex.soul_ice.SoulIce;
import net.minecraft.item.Item;
import net.minecraft.item.Item;
import net.minecraft.util.registry.Registry;

import java.util.LinkedHashMap;
import java.util.Map;

public class SoulIceItemsImpl {
    public static Map<String, Item> itemMap = new LinkedHashMap<>();

    public static Item register(String name, Item item) {
        itemMap.put(name, item);
        return item;
    }
}
