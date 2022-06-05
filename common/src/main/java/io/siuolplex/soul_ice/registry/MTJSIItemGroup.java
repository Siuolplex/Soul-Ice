package io.siuolplex.soul_ice.registry;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

public class MTJSIItemGroup {
    public static ItemGroup MTJSI = setItemGroup("mtjsi", SoulIceItems.SOUL_ICE);

    @ExpectPlatform
    public static ItemGroup setItemGroup(String id, Item item) {
        throw new RuntimeException("Architectury failed!");
    }
}
