package io.siuolplex.soul_ice.quilt.registry;

import io.siuolplex.soul_ice.SoulIceConfig;
import io.siuolplex.soul_ice.block.SoulIceStairBlock;
import io.siuolplex.soul_ice.quilt.blocks.RujoneBerryBushBlock;
import io.siuolplex.soul_ice.util.SoulIceIDHandler;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.registry.Registry;
import org.quiltmc.qsl.block.extensions.api.QuiltBlockSettings;

public class SoulIceBlocks {
    public static final BlockSoundGroup LIGHTSTONE_SOUNDS = new BlockSoundGroup(1.0F, 1.5F, SoundEvents.BLOCK_POLISHED_DEEPSLATE_BREAK, SoundEvents.BLOCK_POLISHED_DEEPSLATE_STEP, SoundEvents.BLOCK_POLISHED_DEEPSLATE_PLACE, SoundEvents.BLOCK_POLISHED_DEEPSLATE_HIT, SoundEvents.BLOCK_POLISHED_DEEPSLATE_FALL);

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

    public static final Block LIGHTSTONE = register("lightstone", new Block(sharedLightstoneSettings()));
    public static final Block LIGHTSTONE_SLAB = register("lightstone_slab", new SlabBlock(sharedLightstoneSettings()));
    public static final Block LIGHTSTONE_STAIRS = register("lightstone_stairs", new SoulIceStairBlock(LIGHTSTONE.getDefaultState(), sharedLightstoneSettings()));
    public static final Block LIGHTSTONE_WALL = register("lightstone_wall", new WallBlock(sharedLightstoneSettings()));

    public static final Block POLISHED_LIGHTSTONE = register("polished_lightstone", new Block(sharedLightstoneSettings()));
    public static final Block POLISHED_LIGHTSTONE_SLAB = register("polished_lightstone_slab", new SlabBlock(sharedLightstoneSettings()));
    public static final Block POLISHED_LIGHTSTONE_STAIRS = register("polished_lightstone_stairs", new SoulIceStairBlock(POLISHED_LIGHTSTONE.getDefaultState(), sharedLightstoneSettings()));
    public static final Block POLISHED_LIGHTSTONE_WALL = register("polished_lightstone_wall", new WallBlock(sharedLightstoneSettings()));

    public static final Block LIGHTSTONE_BRICKS = register("lightstone_bricks", new Block(sharedLightstoneSettings()));
    public static final Block LIGHTSTONE_BRICK_SLAB = register("lightstone_brick_slab", new SlabBlock(sharedLightstoneSettings()));
    public static final Block LIGHTSTONE_BRICK_STAIRS = register("lightstone_brick_stairs", new SoulIceStairBlock(LIGHTSTONE_BRICKS.getDefaultState(), sharedLightstoneSettings()));
    public static final Block LIGHTSTONE_BRICK_WALL = register("lightstone_brick_wall", new WallBlock(sharedLightstoneSettings()));

    public static final Block HARDENED_LIGHTSTONE = register("hardened_lightstone", new Block(sharedLightstoneSettings()));
    public static final Block HARDENED_LIGHTSTONE_SLAB = register("hardened_lightstone_slab", new SlabBlock(sharedLightstoneSettings()));
    public static final Block HARDENED_LIGHTSTONE_STAIRS = register("hardened_lightstone_stairs", new SoulIceStairBlock(HARDENED_LIGHTSTONE.getDefaultState(), sharedLightstoneSettings()));
    public static final Block HARDENED_LIGHTSTONE_WALL = register("hardened_lightstone_wall", new WallBlock(sharedLightstoneSettings()));

    public static final Block RUJONE_BERRY_BUSH = register("rujone_berry_bush", new RujoneBerryBushBlock(AbstractBlock.Settings.copy(Blocks.SWEET_BERRY_BUSH)));
    public static final Block ORANGE_ROSE = register("orange_rose", new FlowerBlock(StatusEffects.FIRE_RESISTANCE, 120, AbstractBlock.Settings.of(Material.PLANT, MapColor.ORANGE).breakInstantly().dynamicBounds().sounds(BlockSoundGroup.GRASS)));


    public static AbstractBlock.Settings sharedPlankSettings() {
        return QuiltBlockSettings.of(Material.WOOD).strength(2.0F, 3.0F).sounds(BlockSoundGroup.WOOD);
    }

    public static AbstractBlock.Settings sharedSettings() {
        return Block.Settings.of(Material.DENSE_ICE, MapColor.LIGHT_BLUE).strength(3.0F).slipperiness(SoulIceConfig.instance().slipperiness).sounds(BlockSoundGroup.GLASS);
    }

    public static AbstractBlock.Settings sharedLightstoneSettings() {
        return Block.Settings.of(Material.STONE, MapColor.WHITE).hardness(4f).sounds(LIGHTSTONE_SOUNDS);
    }
    
    private static Block register(String name, Block block) {
        return Registry.register(Registry.BLOCK, SoulIceIDHandler.idFormatter(name), block);
    }

    public static void init() {}
}
