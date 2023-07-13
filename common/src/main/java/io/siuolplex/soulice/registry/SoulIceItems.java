package io.siuolplex.soulice.registry;

import io.siuolplex.soulice.items.BakedGlutenBallItem;
import io.siuolplex.soulice.items.GlutenBallItem;
import io.siuolplex.soulice.items.HydratedCactusArmorMaterial;
import io.siuolplex.soulice.items.UnhydratedCactusArmorMaterial;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;

import java.util.ArrayList;
import java.util.List;

public class SoulIceItems {
    public static List<Item> SOUL_ICE_ITEM_GROUP = new ArrayList<>();
    public static final ArmorMaterial UnhydratedCactusArmorMaterial = new UnhydratedCactusArmorMaterial();
    public static final ArmorMaterial HydratedCactusArmorMaterial = new HydratedCactusArmorMaterial();


    public static final Item SOUL_ICE = register("soul_ice", new BlockItem(SoulIceBlocks.SOUL_ICE, new Item.Properties()));
    public static final Item SOUL_ICE_SLAB = register("soul_ice_slab", new BlockItem(SoulIceBlocks.SOUL_ICE_SLAB, new Item.Properties()));
    public static final Item SOUL_ICE_STAIRS = register("soul_ice_stairs", new BlockItem(SoulIceBlocks.SOUL_ICE_STAIRS, new Item.Properties()));
    public static final Item SOUL_ICE_WALL = register("soul_ice_wall", new BlockItem(SoulIceBlocks.SOUL_ICE_WALL, new Item.Properties()));
    public static final Item SOUL_ICE_GATE = register("soul_ice_gate", new BlockItem(SoulIceBlocks.SOUL_ICE_GATE, new Item.Properties()));

    public static final Item POLISHED_SOUL_ICE = register("polished_soul_ice", new BlockItem(SoulIceBlocks.POLISHED_SOUL_ICE, new Item.Properties()));
    public static final Item POLISHED_SOUL_ICE_SLAB = register("polished_soul_ice_slab", new BlockItem(SoulIceBlocks.POLISHED_SOUL_ICE_SLAB, new Item.Properties()));
    public static final Item POLISHED_SOUL_ICE_STAIRS = register("polished_soul_ice_stairs", new BlockItem(SoulIceBlocks.POLISHED_SOUL_ICE_STAIRS, new Item.Properties()));
    public static final Item POLISHED_SOUL_ICE_WALL = register("polished_soul_ice_wall", new BlockItem(SoulIceBlocks.POLISHED_SOUL_ICE_WALL, new Item.Properties()));
    public static final Item POLISHED_SOUL_ICE_GATE = register("polished_soul_ice_gate", new BlockItem(SoulIceBlocks.POLISHED_SOUL_ICE_GATE, new Item.Properties()));

    public static final Item SOUL_ICE_BRICKS = register("soul_ice_bricks", new BlockItem(SoulIceBlocks.SOUL_ICE_BRICKS, new Item.Properties()));
    public static final Item SOUL_ICE_BRICK_SLAB = register("soul_ice_brick_slab", new BlockItem(SoulIceBlocks.SOUL_ICE_BRICK_SLAB, new Item.Properties()));
    public static final Item SOUL_ICE_BRICK_STAIRS = register("soul_ice_brick_stairs", new BlockItem(SoulIceBlocks.SOUL_ICE_BRICK_STAIRS, new Item.Properties()));
    public static final Item SOUL_ICE_BRICK_WALL = register("soul_ice_brick_wall", new BlockItem(SoulIceBlocks.SOUL_ICE_BRICK_WALL, new Item.Properties()));
    public static final Item SOUL_ICE_BRICK_GATE = register("soul_ice_brick_gate", new BlockItem(SoulIceBlocks.SOUL_ICE_BRICK_GATE, new Item.Properties()));

    public static final Item LIGHTSTONE = register("lightstone", new BlockItem(SoulIceBlocks.LIGHTSTONE, new Item.Properties()));
    public static final Item LIGHTSTONE_SLAB = register("lightstone_slab", new BlockItem(SoulIceBlocks.LIGHTSTONE_SLAB, new Item.Properties()));
    public static final Item LIGHTSTONE_STAIRS = register("lightstone_stairs", new BlockItem(SoulIceBlocks.LIGHTSTONE_STAIRS, new Item.Properties()));
    public static final Item LIGHTSTONE_WALL = register("lightstone_wall", new BlockItem(SoulIceBlocks.LIGHTSTONE_WALL, new Item.Properties()));

    public static final Item POLISHED_LIGHTSTONE = register("polished_lightstone", new BlockItem(SoulIceBlocks.POLISHED_LIGHTSTONE, new Item.Properties()));
    public static final Item POLISHED_LIGHTSTONE_SLAB = register("polished_lightstone_slab", new BlockItem(SoulIceBlocks.POLISHED_LIGHTSTONE_SLAB, new Item.Properties()));
    public static final Item POLISHED_LIGHTSTONE_STAIRS = register("polished_lightstone_stairs", new BlockItem(SoulIceBlocks.POLISHED_LIGHTSTONE_STAIRS, new Item.Properties()));
    public static final Item POLISHED_LIGHTSTONE_WALL = register("polished_lightstone_wall", new BlockItem(SoulIceBlocks.POLISHED_LIGHTSTONE_WALL, new Item.Properties()));

    public static final Item LIGHTSTONE_BRICKS = register("lightstone_bricks", new BlockItem(SoulIceBlocks.LIGHTSTONE_BRICKS, new Item.Properties()));
    public static final Item LIGHTSTONE_BRICK_SLAB = register("lightstone_brick_slab", new BlockItem(SoulIceBlocks.LIGHTSTONE_BRICK_SLAB, new Item.Properties()));
    public static final Item LIGHTSTONE_BRICK_STAIRS = register("lightstone_brick_stairs", new BlockItem(SoulIceBlocks.LIGHTSTONE_BRICK_STAIRS, new Item.Properties()));
    public static final Item LIGHTSTONE_BRICK_WALL = register("lightstone_brick_wall", new BlockItem(SoulIceBlocks.LIGHTSTONE_BRICK_WALL, new Item.Properties()));

    public static final Item HARDENED_LIGHTSTONE = register("hardened_lightstone", new BlockItem(SoulIceBlocks.HARDENED_LIGHTSTONE, new Item.Properties()));
    public static final Item HARDENED_LIGHTSTONE_SLAB = register("hardened_lightstone_slab", new BlockItem(SoulIceBlocks.HARDENED_LIGHTSTONE_SLAB, new Item.Properties()));
    public static final Item HARDENED_LIGHTSTONE_STAIRS = register("hardened_lightstone_stairs", new BlockItem(SoulIceBlocks.HARDENED_LIGHTSTONE_STAIRS, new Item.Properties()));
    public static final Item HARDENED_LIGHTSTONE_WALL = register("hardened_lightstone_wall", new BlockItem(SoulIceBlocks.HARDENED_LIGHTSTONE_WALL, new Item.Properties()));

    public static final Item CACTUS_HELMET = register("cactus_helmet", new ArmorItem(UnhydratedCactusArmorMaterial, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final Item CACTUS_CHESTPLATE = register("cactus_chestplate", new ArmorItem(UnhydratedCactusArmorMaterial, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final Item CACTUS_LEGGINGS = register("cactus_leggings", new ArmorItem(UnhydratedCactusArmorMaterial, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final Item CACTUS_BOOTS = register("cactus_boots", new ArmorItem(UnhydratedCactusArmorMaterial, ArmorItem.Type.BOOTS, new Item.Properties()));
    public static final Item HYDRATED_CACTUS_HELMET = register("hydrated_cactus_helmet", new ArmorItem(HydratedCactusArmorMaterial, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final Item HYDRATED_CACTUS_CHESTPLATE = register("hydrated_cactus_chestplate", new ArmorItem(HydratedCactusArmorMaterial, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final Item HYDRATED_CACTUS_LEGGINGS = register("hydrated_cactus_leggings", new ArmorItem(HydratedCactusArmorMaterial, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final Item HYDRATED_CACTUS_BOOTS = register("hydrated_cactus_boots", new ArmorItem(HydratedCactusArmorMaterial, ArmorItem.Type.BOOTS, new Item.Properties()));

    public static final Item ORANGE_ROSE = register("orange_rose", new BlockItem(SoulIceBlocks.ORANGE_ROSE, new Item.Properties()));
    public static final Item RUJONE_BERRIES = register("rujone_berries", new BlockItem(SoulIceBlocks.RUJONE_BERRY_BUSH, new Item.Properties().food(new FoodProperties.Builder().nutrition(1).saturationMod(0.8f).build())));


    public static final Item GLUTEN_BALL = register("gluten_ball", new GlutenBallItem(new Item.Properties().stacksTo(16)));
    public static final Item BAKED_GLUTEN_BALL = register("baked_gluten_ball", new BakedGlutenBallItem(new Item.Properties().stacksTo(16)));



    // RECALL ITEM
    //public static final Item FISH_SPAWNING_STAFF = register("amphibian_blessing", new Item(new Item.Properties())); // Joke item
    // SCULK HELMET
    // Cape item
    // Plains berries?
    // Evoker Items
    
    private static Item register(String name, Item item) {
        Item registeredItem = Registry.register(BuiltInRegistries.ITEM, new ResourceLocation("soul_ice", name), item);
        SOUL_ICE_ITEM_GROUP.add(registeredItem);

        return registeredItem;
    }

    public static void init() {};
}
