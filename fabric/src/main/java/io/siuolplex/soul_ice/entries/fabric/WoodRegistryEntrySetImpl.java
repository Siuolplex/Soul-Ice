package io.siuolplex.soul_ice.entries.fabric;

import io.siuolplex.soul_ice.util.SoulIceIDHandler;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.util.registry.Registry;

import static io.siuolplex.soul_ice.registry.SoulIceItems.sharedItemSettings;

public class WoodRegistryEntrySetImpl {
    public static void registerWithBlock(String name, Block block) {
        Registry.register(Registry.BLOCK, SoulIceIDHandler.idFormatter(name), block);
        Registry.register(Registry.ITEM, SoulIceIDHandler.idFormatter(name), new BlockItem(block, sharedItemSettings()));
    }
}
