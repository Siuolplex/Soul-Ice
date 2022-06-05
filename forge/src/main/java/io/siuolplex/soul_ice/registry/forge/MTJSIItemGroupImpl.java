package io.siuolplex.soul_ice.registry.forge;

import io.siuolplex.soul_ice.registry.SoulIceItems;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class MTJSIItemGroupImpl {
    public static ItemGroup setItemGroup(String id, Item item) {
        return new ItemGroup(id) {
            @Override
            public ItemStack createIcon() {
                return new ItemStack(SoulIceItems.SOUL_ICE);
            }
        };
    }
}
