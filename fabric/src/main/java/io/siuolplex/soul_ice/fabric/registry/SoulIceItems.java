package io.siuolplex.soul_ice.fabric.registry;

import io.siuolplex.soul_ice.util.SoulIceIDHandler;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.*;
import net.minecraft.util.registry.Registry;
import io.siuolplex.soul_ice.items.CactusArmorMaterial;

public class SoulIceItems {
    public static final ArmorMaterial CactusArmorMaterial = new CactusArmorMaterial();

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

    public static final Item LIGHTSTONE = register("lightstone", new BlockItem(SoulIceBlocks.LIGHTSTONE, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
    public static final Item LIGHTSTONE_SLAB = register("lightstone_slab", new BlockItem(SoulIceBlocks.LIGHTSTONE_SLAB, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
    public static final Item LIGHTSTONE_STAIRS = register("lightstone_stairs", new BlockItem(SoulIceBlocks.LIGHTSTONE_STAIRS, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
    public static final Item LIGHTSTONE_WALL = register("lightstone_wall", new BlockItem(SoulIceBlocks.LIGHTSTONE_WALL, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
    public static final Item POLISHED_LIGHTSTONE = register("polished_lightstone", new BlockItem(SoulIceBlocks.POLISHED_LIGHTSTONE, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
    public static final Item POLISHED_LIGHTSTONE_SLAB = register("polished_lightstone_slab", new BlockItem(SoulIceBlocks.POLISHED_LIGHTSTONE_SLAB, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
    public static final Item POLISHED_LIGHTSTONE_STAIRS = register("polished_lightstone_stairs", new BlockItem(SoulIceBlocks.POLISHED_LIGHTSTONE_STAIRS, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
    public static final Item POLISHED_LIGHTSTONE_WALL = register("polished_lightstone_wall", new BlockItem(SoulIceBlocks.POLISHED_LIGHTSTONE_WALL, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
    public static final Item LIGHTSTONE_BRICKS = register("lightstone_bricks", new BlockItem(SoulIceBlocks.LIGHTSTONE_BRICKS, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
    public static final Item LIGHTSTONE_BRICK_SLAB = register("lightstone_brick_slab", new BlockItem(SoulIceBlocks.LIGHTSTONE_BRICK_SLAB, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
    public static final Item LIGHTSTONE_BRICK_STAIRS = register("lightstone_brick_stairs", new BlockItem(SoulIceBlocks.LIGHTSTONE_BRICK_STAIRS, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
    public static final Item LIGHTSTONE_BRICK_WALL = register("lightstone_brick_wall", new BlockItem(SoulIceBlocks.LIGHTSTONE_BRICK_WALL, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
    public static final Item HARDENED_LIGHTSTONE = register("hardened_lightstone", new BlockItem(SoulIceBlocks.HARDENED_LIGHTSTONE, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
    public static final Item HARDENED_LIGHTSTONE_SLAB = register("hardened_lightstone_slab", new BlockItem(SoulIceBlocks.HARDENED_LIGHTSTONE_SLAB, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
    public static final Item HARDENED_LIGHTSTONE_STAIRS = register("hardened_lightstone_stairs", new BlockItem(SoulIceBlocks.HARDENED_LIGHTSTONE_STAIRS, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
    public static final Item HARDENED_LIGHTSTONE_WALL = register("hardened_lightstone_wall", new BlockItem(SoulIceBlocks.HARDENED_LIGHTSTONE_WALL, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));

    public static final Item CACTUS_HELMET = register("cactus_helmet", new ArmorItem(CactusArmorMaterial, EquipmentSlot.HEAD, new FabricItemSettings().group(ItemGroup.COMBAT)));
    public static final Item CACTUS_CHESTPLATE = register("cactus_chestplate", new ArmorItem(CactusArmorMaterial, EquipmentSlot.CHEST, new FabricItemSettings().group(ItemGroup.COMBAT)));
    public static final Item CACTUS_LEGGINGS = register("cactus_leggings", new ArmorItem(CactusArmorMaterial, EquipmentSlot.LEGS, new FabricItemSettings().group(ItemGroup.COMBAT)));
    public static final Item CACTUS_BOOTS = register("cactus_boots", new ArmorItem(CactusArmorMaterial, EquipmentSlot.FEET, new FabricItemSettings().group(ItemGroup.COMBAT)));


    private static Item register(String name, Item item) {
        return Registry.register(Registry.ITEM, SoulIceIDHandler.idFormatter(name), item);
    }

    public static void init() {}
}
