package io.siuolplex.soul_ice.registry;

import dev.architectury.injectables.annotations.ExpectPlatform;
import io.siuolplex.soul_ice.entries.WoodRegistryEntrySet;
import io.siuolplex.soul_ice.items.SoulIceArmorMaterial;
import io.siuolplex.soul_ice.util.SoulIceIDHandler;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.*;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.DyeColor;
import net.minecraft.util.registry.Registry;
import io.siuolplex.soul_ice.items.CactusArmorMaterial;

public class SoulIceItems {
    public static final ArmorMaterial CactusArmorMaterial = new CactusArmorMaterial();
    public static final ArmorMaterial SoulIceCoveredArmorMaterial = new SoulIceArmorMaterial();

    public static final Item SOUL_ICE = register("soul_ice", new BlockItem(SoulIceBlocks.SOUL_ICE, sharedItemSettings()));
    public static final Item SOUL_ICE_SLAB = register("soul_ice_slab", new BlockItem(SoulIceBlocks.SOUL_ICE_SLAB, sharedItemSettings()));
    public static final Item SOUL_ICE_STAIRS = register("soul_ice_stairs", new BlockItem(SoulIceBlocks.SOUL_ICE_STAIRS, sharedItemSettings()));
    public static final Item SOUL_ICE_WALL = register("soul_ice_wall", new BlockItem(SoulIceBlocks.SOUL_ICE_WALL, sharedItemSettings()));
    public static final Item SOUL_ICE_GATE = register("soul_ice_gate", new BlockItem(SoulIceBlocks.SOUL_ICE_GATE, sharedItemSettings()));

    public static final Item POLISHED_SOUL_ICE = register("polished_soul_ice", new BlockItem(SoulIceBlocks.POLISHED_SOUL_ICE, sharedItemSettings()));
    public static final Item POLISHED_SOUL_ICE_SLAB = register("polished_soul_ice_slab", new BlockItem(SoulIceBlocks.POLISHED_SOUL_ICE_SLAB, sharedItemSettings()));
    public static final Item POLISHED_SOUL_ICE_STAIRS = register("polished_soul_ice_stairs", new BlockItem(SoulIceBlocks.POLISHED_SOUL_ICE_STAIRS, sharedItemSettings()));
    public static final Item POLISHED_SOUL_ICE_WALL = register("polished_soul_ice_wall", new BlockItem(SoulIceBlocks.POLISHED_SOUL_ICE_WALL, sharedItemSettings()));
    public static final Item POLISHED_SOUL_ICE_GATE = register("polished_soul_ice_gate", new BlockItem(SoulIceBlocks.POLISHED_SOUL_ICE_GATE, sharedItemSettings()));

    public static final Item SOUL_ICE_BRICKS = register("soul_ice_bricks", new BlockItem(SoulIceBlocks.SOUL_ICE_BRICKS, sharedItemSettings()));
    public static final Item SOUL_ICE_BRICK_SLAB = register("soul_ice_brick_slab", new BlockItem(SoulIceBlocks.SOUL_ICE_BRICK_SLAB, sharedItemSettings()));
    public static final Item SOUL_ICE_BRICK_STAIRS = register("soul_ice_brick_stairs", new BlockItem(SoulIceBlocks.SOUL_ICE_BRICK_STAIRS, sharedItemSettings()));
    public static final Item SOUL_ICE_BRICK_WALL = register("soul_ice_brick_wall", new BlockItem(SoulIceBlocks.SOUL_ICE_BRICK_WALL, sharedItemSettings()));
    public static final Item SOUL_ICE_BRICK_GATE = register("soul_ice_brick_gate", new BlockItem(SoulIceBlocks.SOUL_ICE_BRICK_GATE, sharedItemSettings()));

    public static final Item LIGHTSTONE = register("lightstone", new BlockItem(SoulIceBlocks.LIGHTSTONE, sharedItemSettings()));
    public static final Item LIGHTSTONE_SLAB = register("lightstone_slab", new BlockItem(SoulIceBlocks.LIGHTSTONE_SLAB, sharedItemSettings()));
    public static final Item LIGHTSTONE_STAIRS = register("lightstone_stairs", new BlockItem(SoulIceBlocks.LIGHTSTONE_STAIRS, sharedItemSettings()));
    public static final Item LIGHTSTONE_WALL = register("lightstone_wall", new BlockItem(SoulIceBlocks.LIGHTSTONE_WALL, sharedItemSettings()));
    public static final Item POLISHED_LIGHTSTONE = register("polished_lightstone", new BlockItem(SoulIceBlocks.POLISHED_LIGHTSTONE, sharedItemSettings()));
    public static final Item POLISHED_LIGHTSTONE_SLAB = register("polished_lightstone_slab", new BlockItem(SoulIceBlocks.POLISHED_LIGHTSTONE_SLAB, sharedItemSettings()));
    public static final Item POLISHED_LIGHTSTONE_STAIRS = register("polished_lightstone_stairs", new BlockItem(SoulIceBlocks.POLISHED_LIGHTSTONE_STAIRS, sharedItemSettings()));
    public static final Item POLISHED_LIGHTSTONE_WALL = register("polished_lightstone_wall", new BlockItem(SoulIceBlocks.POLISHED_LIGHTSTONE_WALL, sharedItemSettings()));
    public static final Item LIGHTSTONE_BRICKS = register("lightstone_bricks", new BlockItem(SoulIceBlocks.LIGHTSTONE_BRICKS, sharedItemSettings()));
    public static final Item LIGHTSTONE_BRICK_SLAB = register("lightstone_brick_slab", new BlockItem(SoulIceBlocks.LIGHTSTONE_BRICK_SLAB, sharedItemSettings()));
    public static final Item LIGHTSTONE_BRICK_STAIRS = register("lightstone_brick_stairs", new BlockItem(SoulIceBlocks.LIGHTSTONE_BRICK_STAIRS, sharedItemSettings()));
    public static final Item LIGHTSTONE_BRICK_WALL = register("lightstone_brick_wall", new BlockItem(SoulIceBlocks.LIGHTSTONE_BRICK_WALL, sharedItemSettings()));
    public static final Item HARDENED_LIGHTSTONE = register("hardened_lightstone", new BlockItem(SoulIceBlocks.HARDENED_LIGHTSTONE, sharedItemSettings()));
    public static final Item HARDENED_LIGHTSTONE_SLAB = register("hardened_lightstone_slab", new BlockItem(SoulIceBlocks.HARDENED_LIGHTSTONE_SLAB, sharedItemSettings()));
    public static final Item HARDENED_LIGHTSTONE_STAIRS = register("hardened_lightstone_stairs", new BlockItem(SoulIceBlocks.HARDENED_LIGHTSTONE_STAIRS, sharedItemSettings()));
    public static final Item HARDENED_LIGHTSTONE_WALL = register("hardened_lightstone_wall", new BlockItem(SoulIceBlocks.HARDENED_LIGHTSTONE_WALL, sharedItemSettings()));

    public static final Item ORANGE_ROSE = register("orange_rose", new BlockItem(SoulIceBlocks.ORANGE_ROSE, sharedItemSettings()));
    public static final Item RUJONE_BERRIES = register("rujone_berries", new AliasedBlockItem(SoulIceBlocks.RUJONE_BERRY_BUSH, sharedItemSettings().food(new FoodComponent.Builder().hunger(1).saturationModifier(0.8f).build())));

    public static final Item CACTUS_HELMET = register("cactus_helmet", new ArmorItem(CactusArmorMaterial, EquipmentSlot.HEAD, sharedItemSettings()));
    public static final Item CACTUS_CHESTPLATE = register("cactus_chestplate", new ArmorItem(CactusArmorMaterial, EquipmentSlot.CHEST, sharedItemSettings()));
    public static final Item CACTUS_LEGGINGS = register("cactus_leggings", new ArmorItem(CactusArmorMaterial, EquipmentSlot.LEGS, sharedItemSettings()));
    public static final Item CACTUS_BOOTS = register("cactus_boots", new ArmorItem(CactusArmorMaterial, EquipmentSlot.FEET, sharedItemSettings()));

    public static final Item SOUL_ICE_COVERED_LEATHER_BOOTS = register("soul_ice_covered_leather_boots", new ArmorItem(SoulIceCoveredArmorMaterial, EquipmentSlot.FEET, sharedItemSettings()));
    
    public static Item.Settings sharedItemSettings() {
        return new Item.Settings().group(MTJSIItemGroup.MTJSI);
    }

    @ExpectPlatform
    private static Item register(String name, Item item) {
        throw new RuntimeException("Architectury failed!");
    }

    public static void init() {}
}
