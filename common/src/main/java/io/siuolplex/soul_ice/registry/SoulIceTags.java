package io.siuolplex.soul_ice.registry;

import io.siuolplex.soul_ice.util.SoulIceIDHandler;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tag.TagKey;
import net.minecraft.util.registry.Registry;

public class SoulIceTags {
    public static final TagKey<Block> ICES = TagKey.of(Registry.BLOCK_KEY, SoulIceIDHandler.idFormatter("ices"));
    public static final TagKey<Item> SOUL_ICE = TagKey.of(Registry.ITEM_KEY, SoulIceIDHandler.idFormatter("soul_ice"));
}
