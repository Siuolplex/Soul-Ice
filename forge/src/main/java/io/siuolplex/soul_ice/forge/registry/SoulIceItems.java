package io.siuolplex.soul_ice.forge.registry;

import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class SoulIceItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, "soul_ice");

    public static RegistryObject<Item> SOUL_ICE = ITEMS.register("soul_ice", () -> new BlockItem(SoulIceBlocks.SOUL_ICE.get(), new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
    public static RegistryObject<Item> SOUL_ICE_SLAB = ITEMS.register("soul_ice_slab", () -> new BlockItem(SoulIceBlocks.SOUL_ICE_SLAB.get(), new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
    public static RegistryObject<Item> SOUL_ICE_STAIRS = ITEMS.register("soul_ice_stairs", () -> new BlockItem(SoulIceBlocks.SOUL_ICE_STAIRS.get(), new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
    public static RegistryObject<Item> SOUL_ICE_WALL = ITEMS.register("soul_ice_wall", () -> new BlockItem(SoulIceBlocks.SOUL_ICE_WALL.get(), new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
    public static RegistryObject<Item> SOUL_ICE_GATE = ITEMS.register("soul_ice_gate", () -> new BlockItem(SoulIceBlocks.SOUL_ICE_GATE.get(), new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));

    public static RegistryObject<Item> POLISHED_SOUL_ICE = ITEMS.register("polished_soul_ice", () -> new BlockItem(SoulIceBlocks.POLISHED_SOUL_ICE.get(), new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
    public static RegistryObject<Item> POLISHED_SOUL_ICE_SLAB = ITEMS.register("polished_soul_ice_slab", () -> new BlockItem(SoulIceBlocks.POLISHED_SOUL_ICE_SLAB.get(), new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
    public static RegistryObject<Item> POLISHED_SOUL_ICE_STAIRS = ITEMS.register("polished_soul_ice_stairs", () -> new BlockItem(SoulIceBlocks.POLISHED_SOUL_ICE_STAIRS.get(), new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
    public static RegistryObject<Item> POLISHED_SOUL_ICE_WALL = ITEMS.register("polished_soul_ice_wall", () -> new BlockItem(SoulIceBlocks.POLISHED_SOUL_ICE_WALL.get(), new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
    public static RegistryObject<Item> POLISHED_SOUL_ICE_GATE = ITEMS.register("polished_soul_ice_gate", () -> new BlockItem(SoulIceBlocks.POLISHED_SOUL_ICE_GATE.get(), new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));

    public static RegistryObject<Item> SOUL_ICE_BRICKS = ITEMS.register("soul_ice_bricks", () -> new BlockItem(SoulIceBlocks.SOUL_ICE_BRICKS.get(), new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
    public static RegistryObject<Item> SOUL_ICE_BRICK_SLAB = ITEMS.register("soul_ice_brick_slab", () -> new BlockItem(SoulIceBlocks.SOUL_ICE_BRICK_SLAB.get(), new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
    public static RegistryObject<Item> SOUL_ICE_BRICK_STAIRS = ITEMS.register("soul_ice_brick_stairs", () -> new BlockItem(SoulIceBlocks.SOUL_ICE_BRICK_STAIRS.get(), new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
    public static RegistryObject<Item> SOUL_ICE_BRICK_WALL = ITEMS.register("soul_ice_brick_wall", () -> new BlockItem(SoulIceBlocks.SOUL_ICE_BRICK_WALL.get(), new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
    public static RegistryObject<Item> SOUL_ICE_BRICK_GATE = ITEMS.register("soul_ice_brick_gate", () -> new BlockItem(SoulIceBlocks.SOUL_ICE_BRICK_GATE.get(), new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
}
