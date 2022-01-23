package io.siuolplex.soul_ice;

import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class SoulIceItems {
    public static final Item SOUL_ICE = register("soul_ice", new BlockItem(SoulIceBlocks.SOUL_ICE, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
    public static final Item SOUL_ICE_SLAB = register("soul_ice_slab", new BlockItem(SoulIceBlocks.SOUL_ICE_SLAB, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
    public static final Item SOUL_ICE_STAIRS = register("soul_ice_stairs", new BlockItem(SoulIceBlocks.SOUL_ICE_STAIRS, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
    public static final Item SOUL_ICE_WALL = register("soul_ice_wall", new BlockItem(SoulIceBlocks.SOUL_ICE_WALL, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
    public static final Item SOUL_ICE_GATE = register("soul_ice_gate", new BlockItem(SoulIceBlocks.SOUL_ICE_GATE, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));

    public static final Item POLISHED_SOUL_ICE = register("polished_soul_ice", new BlockItem(SoulIceBlocks.POLISHED_SOUL_ICE, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
    public static final Item POLISHED_SOUL_ICE_SLAB = register("polished_soul_ice_slab", new BlockItem(SoulIceBlocks.POLISHED_SOUL_ICE_SLAB, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
    public static final Item POLISHED_SOUL_ICE_STAIRS = register("polished_soul_ice_stairs", new BlockItem(SoulIceBlocks.POLISHED_SOUL_ICE_STAIRS, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
    public static final Item POLISHED_SOUL_ICE_WALL = register("polished_soul_ice_wall", new BlockItem(SoulIceBlocks.POLISHED_SOUL_ICE_WALL, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
    public static final Item POLISHED_SOUL_ICE_GATE = register("polished_soul_ice_gate", new BlockItem(SoulIceBlocks.POLISHED_SOUL_ICE_GATE, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));

    public static final Item SOUL_ICE_BRICKS = register("soul_ice_bricks", new BlockItem(SoulIceBlocks.SOUL_ICE_BRICKS, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
    public static final Item SOUL_ICE_BRICK_SLAB = register("soul_ice_brick_slab", new BlockItem(SoulIceBlocks.SOUL_ICE_BRICK_SLAB, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
    public static final Item SOUL_ICE_BRICK_STAIRS = register("soul_ice_brick_stairs", new BlockItem(SoulIceBlocks.SOUL_ICE_BRICK_STAIRS, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
    public static final Item SOUL_ICE_BRICK_WALL = register("soul_ice_brick_wall", new BlockItem(SoulIceBlocks.SOUL_ICE_BRICK_WALL, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
    public static final Item SOUL_ICE_BRICK_GATE = register("soul_ice_brick_gate", new BlockItem(SoulIceBlocks.SOUL_ICE_BRICK_GATE, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));

    private static Item register(String name, Item item) {
        return Registry.register(Registry.ITEM, new Identifier("soul_ice", name), item);
    }

    public static void init() {}
}
