package io.siuolplex.soul_ice.registry.forge;

import io.siuolplex.soul_ice.SoulIce;
import net.minecraft.block.Block;
import net.minecraft.util.registry.Registry;

import java.util.LinkedHashMap;
import java.util.Map;

public class SoulIceBlocksImpl {
    public static Map<String, Block> blockMap = new LinkedHashMap<>();

    public static Block register(String name, Block block) {
        blockMap.put(name, block);
        return block;
    }
}
