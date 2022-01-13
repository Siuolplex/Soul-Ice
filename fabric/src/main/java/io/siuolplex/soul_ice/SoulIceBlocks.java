package io.siuolplex.soul_ice;

import net.minecraft.block.*;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class SoulIceBlocks {
    public static final Block SOUL_ICE = register("soul_ice", new Block(Block.Settings.of(Material.DENSE_ICE, MapColor.LIGHT_BLUE).strength(3.0F).slipperiness(1.1f).sounds(BlockSoundGroup.GLASS)));
    public static final Block SOUL_ICE_SLAB = register("soul_ice_slab", new SlabBlock(Block.Settings.of(Material.DENSE_ICE, MapColor.LIGHT_BLUE).strength(3.0F).slipperiness(1.1f).sounds(BlockSoundGroup.GLASS)));
    public static final Block SOUL_ICE_STAIRS = register("soul_ice_stairs", new SoulIceStairBlock(SOUL_ICE.getDefaultState(), Block.Settings.of(Material.DENSE_ICE, MapColor.LIGHT_BLUE).strength(3.0F).slipperiness(1.1f).sounds(BlockSoundGroup.GLASS)));
    public static final Block SOUL_ICE_WALL = register("soul_ice_wall", new WallBlock(Block.Settings.of(Material.DENSE_ICE, MapColor.LIGHT_BLUE).strength(3.0F).slipperiness(1.1f).sounds(BlockSoundGroup.GLASS)));
    public static final Block SOUL_ICE_GATE = register("soul_ice_gate", new FenceGateBlock(Block.Settings.of(Material.DENSE_ICE, MapColor.LIGHT_BLUE).strength(3.0F).slipperiness(1.1f).sounds(BlockSoundGroup.GLASS)));


    private static Block register(String name, Block block) {
        return Registry.register(Registry.BLOCK, new Identifier("soul_ice", name), block);
    }

    public static void init() {}
}
