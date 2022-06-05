package io.siuolplex.soul_ice.registry.fabric;

import io.siuolplex.soul_ice.registry.SoulIceItems;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.crash.CrashException;
import org.quiltmc.qsl.item.group.api.QuiltItemGroup;

import static io.siuolplex.soul_ice.SoulIce.LOGGER;
import static io.siuolplex.soul_ice.SoulIce.idFormatter;

public class MTJSIItemGroupImpl {
    public static ItemGroup setItemGroup(String id, Item item) {
        return QuiltItemGroup.builder(idFormatter(id)).icon(() -> new ItemStack(SoulIceItems.SOUL_ICE)).build();
    }
}
