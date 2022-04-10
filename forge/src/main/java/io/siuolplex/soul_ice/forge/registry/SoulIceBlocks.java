package io.siuolplex.soul_ice.forge.registry;

import io.siuolplex.soul_ice.SoulIceConfig;
import io.siuolplex.soul_ice.block.SoulIceStairBlock;
import net.minecraft.block.*;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundEvents;
import net.minecraftforge.common.util.ForgeSoundType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class SoulIceBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, "soul_ice");

    public static final BlockSoundGroup LIGHTSTONE_SOUNDS = new BlockSoundGroup(1.0F, 1.5F, SoundEvents.BLOCK_POLISHED_DEEPSLATE_BREAK, SoundEvents.BLOCK_POLISHED_DEEPSLATE_STEP, SoundEvents.BLOCK_POLISHED_DEEPSLATE_PLACE, SoundEvents.BLOCK_POLISHED_DEEPSLATE_HIT, SoundEvents.BLOCK_POLISHED_DEEPSLATE_FALL);

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

    public static RegistryObject<Block> LIGHTSTONE = BLOCKS.register("lightstone", () -> new Block(sharedLightstoneSettings()));
    public static RegistryObject<Block> LIGHTSTONE_SLAB = BLOCKS.register("lightstone_slab", () -> new SlabBlock(sharedLightstoneSettings()));
    public static RegistryObject<Block> LIGHTSTONE_STAIRS = BLOCKS.register("lightstone_stairs", () -> new SoulIceStairBlock(LIGHTSTONE.get().getDefaultState(), sharedLightstoneSettings()));
    public static RegistryObject<Block> LIGHTSTONE_WALL = BLOCKS.register("lightstone_wall", () -> new WallBlock(sharedLightstoneSettings()));

    public static RegistryObject<Block> POLISHED_LIGHTSTONE = BLOCKS.register("polished_lightstone", () -> new Block(sharedLightstoneSettings()));
    public static RegistryObject<Block> POLISHED_LIGHTSTONE_SLAB = BLOCKS.register("polished_lightstone_slab", () -> new SlabBlock(sharedLightstoneSettings()));
    public static RegistryObject<Block> POLISHED_LIGHTSTONE_STAIRS = BLOCKS.register("polished_lightstone_stairs", () -> new SoulIceStairBlock(POLISHED_LIGHTSTONE.get().getDefaultState(), sharedLightstoneSettings()));
    public static RegistryObject<Block> POLISHED_LIGHTSTONE_WALL = BLOCKS.register("polished_lightstone_wall", () -> new WallBlock(sharedLightstoneSettings()));

    public static RegistryObject<Block> LIGHTSTONE_BRICKS = BLOCKS.register("lightstone_bricks", () -> new Block(sharedLightstoneSettings()));
    public static RegistryObject<Block> LIGHTSTONE_BRICK_SLAB = BLOCKS.register("lightstone_brick_slab", () -> new SlabBlock(sharedLightstoneSettings()));
    public static RegistryObject<Block> LIGHTSTONE_BRICK_STAIRS = BLOCKS.register("lightstone_brick_stairs", () -> new SoulIceStairBlock(LIGHTSTONE_BRICKS.get().getDefaultState(), sharedLightstoneSettings()));
    public static RegistryObject<Block> LIGHTSTONE_BRICK_WALL = BLOCKS.register("lightstone_brick_wall", () -> new WallBlock(sharedLightstoneSettings()));

    public static RegistryObject<Block> HARDENED_LIGHTSTONE = BLOCKS.register("hardened_lightstone", () -> new Block(sharedLightstoneSettings()));
    public static RegistryObject<Block> HARDENED_LIGHTSTONE_SLAB = BLOCKS.register("hardened_lightstone_slab", () -> new SlabBlock(sharedLightstoneSettings()));
    public static RegistryObject<Block> HARDENED_LIGHTSTONE_STAIRS = BLOCKS.register("hardened_lightstone_stairs", () -> new SoulIceStairBlock(HARDENED_LIGHTSTONE.get().getDefaultState(), sharedLightstoneSettings()));
    public static RegistryObject<Block> HARDENED_LIGHTSTONE_WALL = BLOCKS.register("hardened_lightstone_wall", () -> new WallBlock(sharedLightstoneSettings()));

    public static AbstractBlock.Settings sharedSettings() {
        return Block.Settings.of(Material.DENSE_ICE, MapColor.LIGHT_BLUE).strength(3.0F).slipperiness(SoulIceConfig.instance().slipperiness).sounds(BlockSoundGroup.GLASS);
    }
    
    public static AbstractBlock.Settings sharedLightstoneSettings() {
        return Block.Settings.of(Material.STONE, MapColor.WHITE).hardness(4f).sounds(LIGHTSTONE_SOUNDS);
    }
}
