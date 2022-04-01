package io.siuolplex.soul_ice.registry;

import io.siuolplex.soul_ice.util.SoulIceIDHandler;
import net.minecraft.block.Block;
import net.minecraft.tag.TagKey;
import net.minecraft.util.registry.Registry;

public class SoulIceTags {
    public static final TagKey<Block> ICES = TagKey.of(Registry.BLOCK_KEY, SoulIceIDHandler.idFormatter("ices"));
}
