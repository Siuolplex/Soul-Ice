package io.siuolplex.soul_ice.forge.registry;

import io.siuolplex.soul_ice.SoulIceConfig;
import io.siuolplex.soul_ice.block.SoulIceStairBlock;
import net.minecraft.block.*;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class SoulIceBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, "soul_ice");


    public static RegistryObject<Block> SOUL_ICE = BLOCKS.register("soul_ice", () -> new Block(sharedSettings()));
    public static RegistryObject<Block> SOUL_ICE_SLAB = BLOCKS.register("soul_ice_slab", () -> new SlabBlock(sharedSettings()));
    public static RegistryObject<Block> SOUL_ICE_STAIRS = BLOCKS.register("soul_ice_stairs", () -> new SoulIceStairBlock(SOUL_ICE.get().getDefaultState(), sharedSettings()));
    public static RegistryObject<Block> SOUL_ICE_WALL = BLOCKS.register("soul_ice_wall", () -> new WallBlock(sharedSettings()));
    public static RegistryObject<Block> SOUL_ICE_GATE = BLOCKS.register("soul_ice_gate", () -> new FenceGateBlock(sharedSettings()));

    public static RegistryObject<Block> POLISHED_SOUL_ICE = BLOCKS.register("polished_soul_ice", () -> new Block(sharedSettings()));
    public static RegistryObject<Block> POLISHED_SOUL_ICE_SLAB = BLOCKS.register("polished_soul_ice_slab", () -> new SlabBlock(sharedSettings()));
    public static RegistryObject<Block> POLISHED_SOUL_ICE_STAIRS = BLOCKS.register("polished_soul_ice_stairs", () -> new SoulIceStairBlock(POLISHED_SOUL_ICE.get().getDefaultState(), sharedSettings()));
    public static RegistryObject<Block> POLISHED_SOUL_ICE_WALL = BLOCKS.register("polished_soul_ice_wall", () -> new WallBlock(sharedSettings()));
    public static RegistryObject<Block> POLISHED_SOUL_ICE_GATE = BLOCKS.register("polished_soul_ice_gate", () -> new FenceGateBlock(sharedSettings()));

    public static RegistryObject<Block> SOUL_ICE_BRICKS = BLOCKS.register("soul_ice_bricks", () -> new Block(sharedSettings()));
    public static RegistryObject<Block> SOUL_ICE_BRICK_SLAB = BLOCKS.register("soul_ice_brick_slab", () -> new SlabBlock(sharedSettings()));
    public static RegistryObject<Block> SOUL_ICE_BRICK_STAIRS = BLOCKS.register("soul_ice_brick_stairs", () -> new SoulIceStairBlock(SOUL_ICE_BRICKS.get().getDefaultState(), sharedSettings()));
    public static RegistryObject<Block> SOUL_ICE_BRICK_WALL = BLOCKS.register("soul_ice_brick_wall", () -> new WallBlock(sharedSettings()));
    public static RegistryObject<Block> SOUL_ICE_BRICK_GATE = BLOCKS.register("soul_ice_brick_gate", () -> new FenceGateBlock(sharedSettings()));

    public static AbstractBlock.Settings sharedSettings() {
        return Block.Settings.of(Material.DENSE_ICE, MapColor.LIGHT_BLUE).strength(3.0F).slipperiness(SoulIceConfig.instance().slipperiness).sounds(BlockSoundGroup.GLASS);
    }
}
