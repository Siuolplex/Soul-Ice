package io.siuolplex.soul_ice.registry.fabric;

import io.siuolplex.soul_ice.registry.SoulIceItems;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;


import static io.siuolplex.soul_ice.SoulIce.idFormatter;

public class MTJSIItemGroupImpl {
    public static ItemGroup setItemGroup(String id, Item item) {
        return FabricItemGroupBuilder.build(idFormatter(id), () -> new ItemStack(SoulIceItems.SOUL_ICE));
    }
}
