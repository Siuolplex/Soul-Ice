package io.siuolplex.soul_ice.entries.forge;

import io.siuolplex.soul_ice.registry.forge.SoulIceBlocksImpl;
import io.siuolplex.soul_ice.util.SoulIceIDHandler;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.registry.Registry;

import java.util.LinkedHashMap;
import java.util.Map;

import static io.siuolplex.soul_ice.registry.SoulIceItems.sharedItemSettings;

public class WoodRegistryEntrySetImpl {
    public static Map<String, Item> entrySetItemMap = new LinkedHashMap<>();

    public static void registerWithBlock(String name, Block block) {
        SoulIceBlocksImpl.blockMap.put(name, block);
        entrySetItemMap.put(name, new BlockItem(block, sharedItemSettings()));
    }
}
