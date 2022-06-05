package io.siuolplex.soul_ice.registry.fabric;

import io.siuolplex.soul_ice.SoulIce;
import net.minecraft.block.Block;
import net.minecraft.util.registry.Registry;

public class SoulIceBlocksImpl {
    public static Block register(String name, Block block) {
       return Registry.register(Registry.BLOCK, SoulIce.idFormatter(name), block);
    }
}
