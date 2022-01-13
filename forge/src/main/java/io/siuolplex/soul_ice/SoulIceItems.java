package io.siuolplex.soul_ice;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class SoulIceItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, "soul_ice");

    public static final Item SOUL_ICE = register("soul_ice", new BlockItem(SoulIceBlocks.SOUL_ICE, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
    public static final Item SOUL_ICE_SLAB = register("soul_ice_slab", new BlockItem(SoulIceBlocks.SOUL_ICE_SLAB, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
    public static final Item SOUL_ICE_STAIRS = register("soul_ice_stairs", new BlockItem(SoulIceBlocks.SOUL_ICE_STAIRS, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
    public static final Item SOUL_ICE_WALL = register("soul_ice_wall", new BlockItem(SoulIceBlocks.SOUL_ICE_WALL, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
    public static final Item SOUL_ICE_GATE = register("soul_ice_gate", new BlockItem(SoulIceBlocks.SOUL_ICE_GATE, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));


    private static Item register(String name, Item item) {
        RegistryObject<Item> blockSupplied = ITEMS.register(name, () -> item);
        return item;
    }
}
