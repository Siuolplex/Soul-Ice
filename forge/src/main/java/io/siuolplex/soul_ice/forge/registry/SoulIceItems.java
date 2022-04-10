package io.siuolplex.soul_ice.forge.registry;

import io.siuolplex.soul_ice.items.CactusArmorMaterial;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.*;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class SoulIceItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, "soul_ice");
    public static final ArmorMaterial CactusArmorMaterial = new CactusArmorMaterial();

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

    public static RegistryObject<Item> LIGHTSTONE = ITEMS.register("lightstone", () -> new BlockItem(SoulIceBlocks.LIGHTSTONE.get(), new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
    public static RegistryObject<Item> LIGHTSTONE_SLAB = ITEMS.register("lightstone_slab", () -> new BlockItem(SoulIceBlocks.LIGHTSTONE_SLAB.get(), new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
    public static RegistryObject<Item> LIGHTSTONE_STAIRS = ITEMS.register("lightstone_stairs", () -> new BlockItem(SoulIceBlocks.LIGHTSTONE_STAIRS.get(), new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
    public static RegistryObject<Item> LIGHTSTONE_WALL = ITEMS.register("lightstone_wall", () -> new BlockItem(SoulIceBlocks.LIGHTSTONE_WALL.get(), new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));

    public static RegistryObject<Item> POLISHED_LIGHTSTONE = ITEMS.register("polished_lightstone", () -> new BlockItem(SoulIceBlocks.POLISHED_LIGHTSTONE.get(), new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
    public static RegistryObject<Item> POLISHED_LIGHTSTONE_SLAB = ITEMS.register("polished_lightstone_slab", () -> new BlockItem(SoulIceBlocks.POLISHED_LIGHTSTONE_SLAB.get(), new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
    public static RegistryObject<Item> POLISHED_LIGHTSTONE_STAIRS = ITEMS.register("polished_lightstone_stairs", () -> new BlockItem(SoulIceBlocks.POLISHED_LIGHTSTONE_STAIRS.get(), new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
    public static RegistryObject<Item> POLISHED_LIGHTSTONE_WALL = ITEMS.register("polished_lightstone_wall", () -> new BlockItem(SoulIceBlocks.POLISHED_LIGHTSTONE_WALL.get(), new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));

    public static RegistryObject<Item> LIGHTSTONE_BRICKS = ITEMS.register("lightstone_bricks", () -> new BlockItem(SoulIceBlocks.LIGHTSTONE_BRICKS.get(), new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
    public static RegistryObject<Item> LIGHTSTONE_BRICK_SLAB = ITEMS.register("lightstone_brick_slab", () -> new BlockItem(SoulIceBlocks.LIGHTSTONE_BRICK_SLAB.get(), new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
    public static RegistryObject<Item> LIGHTSTONE_BRICK_STAIRS = ITEMS.register("lightstone_brick_stairs", () -> new BlockItem(SoulIceBlocks.LIGHTSTONE_BRICK_STAIRS.get(), new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
    public static RegistryObject<Item> LIGHTSTONE_BRICK_WALL = ITEMS.register("lightstone_brick_wall", () -> new BlockItem(SoulIceBlocks.LIGHTSTONE_BRICK_WALL.get(), new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));

    public static RegistryObject<Item> HARDENED_LIGHTSTONE = ITEMS.register("hardened_lightstone", () -> new BlockItem(SoulIceBlocks.HARDENED_LIGHTSTONE.get(), new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
    public static RegistryObject<Item> HARDENED_LIGHTSTONE_SLAB = ITEMS.register("hardened_lightstone_slab", () -> new BlockItem(SoulIceBlocks.HARDENED_LIGHTSTONE_SLAB.get(), new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
    public static RegistryObject<Item> HARDENED_LIGHTSTONE_STAIRS = ITEMS.register("hardened_lightstone_stairs", () -> new BlockItem(SoulIceBlocks.HARDENED_LIGHTSTONE_STAIRS.get(), new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
    public static RegistryObject<Item> HARDENED_LIGHTSTONE_WALL = ITEMS.register("hardened_lightstone_wall", () -> new BlockItem(SoulIceBlocks.HARDENED_LIGHTSTONE_WALL.get(), new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));

    public static RegistryObject<Item> CACTUS_HELMET = ITEMS.register("cactus_helmet", () -> new ArmorItem(CactusArmorMaterial, EquipmentSlot.HEAD, new Item.Settings().group(ItemGroup.COMBAT)));
    public static RegistryObject<Item> CACTUS_CHESTPLATE = ITEMS.register("cactus_chestplate", () -> new ArmorItem(CactusArmorMaterial, EquipmentSlot.CHEST, new Item.Settings().group(ItemGroup.COMBAT)));
    public static RegistryObject<Item> CACTUS_LEGGINGS = ITEMS.register("cactus_leggings", () -> new ArmorItem(CactusArmorMaterial, EquipmentSlot.LEGS, new Item.Settings().group(ItemGroup.COMBAT)));
    public static RegistryObject<Item> CACTUS_BOOTS = ITEMS.register("cactus_boots", () -> new ArmorItem(CactusArmorMaterial, EquipmentSlot.FEET, new Item.Settings().group(ItemGroup.COMBAT)));
}
