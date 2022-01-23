package io.siuolplex.soul_ice;

import net.minecraft.block.*;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class SoulIceBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, "soul_ice");

    public static final Block SOUL_ICE = register("soul_ice", new Block(sharedSettings()));
    public static final Block SOUL_ICE_SLAB = register("soul_ice_slab", new SlabBlock(sharedSettings()));
    public static final Block SOUL_ICE_STAIRS = register("soul_ice_stairs", new SoulIceStairBlock(SOUL_ICE.getDefaultState(), sharedSettings()));
    public static final Block SOUL_ICE_WALL = register("soul_ice_wall", new WallBlock(sharedSettings()));
    public static final Block SOUL_ICE_GATE = register("soul_ice_gate", new FenceGateBlock(sharedSettings()));

    public static final Block POLISHED_SOUL_ICE = register("polished_soul_ice", new Block(sharedSettings()));
    public static final Block POLISHED_SOUL_ICE_SLAB = register("polished_soul_ice_slab", new SlabBlock(sharedSettings()));
    public static final Block POLISHED_SOUL_ICE_STAIRS = register("polished_soul_ice_stairs", new SoulIceStairBlock(POLISHED_SOUL_ICE.getDefaultState(), sharedSettings()));
    public static final Block POLISHED_SOUL_ICE_WALL = register("polished_soul_ice_wall", new WallBlock(sharedSettings()));
    public static final Block POLISHED_SOUL_ICE_GATE = register("polished_soul_ice_gate", new FenceGateBlock(sharedSettings()));

    public static final Block SOUL_ICE_BRICKS = register("soul_ice_bricks", new Block(sharedSettings()));
    public static final Block SOUL_ICE_BRICK_SLAB = register("soul_ice_brick_slab", new SlabBlock(sharedSettings()));
    public static final Block SOUL_ICE_BRICK_STAIRS = register("soul_ice_brick_stairs", new SoulIceStairBlock(SOUL_ICE_BRICKS.getDefaultState(), sharedSettings()));
    public static final Block SOUL_ICE_BRICK_WALL = register("soul_ice_brick_wall", new WallBlock(sharedSettings()));
    public static final Block SOUL_ICE_BRICK_GATE = register("soul_ice_brick_gate", new FenceGateBlock(sharedSettings()));

    public static AbstractBlock.Settings sharedSettings() {
        return Block.Settings.of(Material.DENSE_ICE, MapColor.LIGHT_BLUE).strength(3.0F).slipperiness(SoulIceConfig.instance().slipperiness).sounds(BlockSoundGroup.GLASS);
    }

    private static Block register(String name, Block block) {
        RegistryObject<Block> blockSupplied = BLOCKS.register(name, () -> block);
        return block;
    }
}
