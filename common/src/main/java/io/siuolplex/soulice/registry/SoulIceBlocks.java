package io.siuolplex.soulice.registry;

import io.siuolplex.soulice.blocks.IgnidiaIceBlock;
import io.siuolplex.soulice.blocks.RujoneBerryBushBlock;
import io.siuolplex.soulice.blocks.SoulIceStairBlock;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.WoodType;



public class SoulIceBlocks {

    public static final SoundType LIGHTSTONE_SOUNDS = new SoundType(1.0F, 1.5F, SoundEvents.POLISHED_DEEPSLATE_BREAK, SoundEvents.POLISHED_DEEPSLATE_STEP, SoundEvents.POLISHED_DEEPSLATE_PLACE, SoundEvents.POLISHED_DEEPSLATE_HIT, SoundEvents.POLISHED_DEEPSLATE_FALL);

    public static final Block SOUL_ICE = register("soul_ice", new Block(sharedSettings()));
    public static final Block SOUL_ICE_SLAB = register("soul_ice_slab", new SlabBlock(sharedSettings()));
    public static final Block SOUL_ICE_STAIRS = register("soul_ice_stairs", new SoulIceStairBlock(SOUL_ICE.defaultBlockState(), sharedSettings()));
    public static final Block SOUL_ICE_WALL = register("soul_ice_wall", new WallBlock(sharedSettings()));
    public static final Block SOUL_ICE_GATE = register("soul_ice_gate", new FenceGateBlock(WoodType.OAK, sharedSettings()));

    public static final Block POLISHED_SOUL_ICE = register("polished_soul_ice", new Block(sharedSettings()));
    public static final Block POLISHED_SOUL_ICE_SLAB = register("polished_soul_ice_slab", new SlabBlock(sharedSettings()));
    public static final Block POLISHED_SOUL_ICE_STAIRS = register("polished_soul_ice_stairs", new SoulIceStairBlock(POLISHED_SOUL_ICE.defaultBlockState(), sharedSettings()));
    public static final Block POLISHED_SOUL_ICE_WALL = register("polished_soul_ice_wall", new WallBlock(sharedSettings()));
    public static final Block POLISHED_SOUL_ICE_GATE = register("polished_soul_ice_gate", new FenceGateBlock(WoodType.OAK, sharedSettings()));

    public static final Block SOUL_ICE_BRICKS = register("soul_ice_bricks", new Block(sharedSettings()));
    public static final Block SOUL_ICE_BRICK_SLAB = register("soul_ice_brick_slab", new SlabBlock(sharedSettings()));
    public static final Block SOUL_ICE_BRICK_STAIRS = register("soul_ice_brick_stairs", new SoulIceStairBlock(SOUL_ICE_BRICKS.defaultBlockState(), sharedSettings()));
    public static final Block SOUL_ICE_BRICK_WALL = register("soul_ice_brick_wall", new WallBlock(sharedSettings()));
    public static final Block SOUL_ICE_BRICK_GATE = register("soul_ice_brick_gate", new FenceGateBlock(WoodType.OAK, sharedSettings()));

    public static final Block LIGHTSTONE = register("lightstone", new Block(sharedLightstoneSettings()));
    public static final Block LIGHTSTONE_SLAB = register("lightstone_slab", new SlabBlock(sharedLightstoneSettings()));
    public static final Block LIGHTSTONE_STAIRS = register("lightstone_stairs", new SoulIceStairBlock(LIGHTSTONE.defaultBlockState(), sharedLightstoneSettings()));
    public static final Block LIGHTSTONE_WALL = register("lightstone_wall", new WallBlock(sharedLightstoneSettings()));

    public static final Block POLISHED_LIGHTSTONE = register("polished_lightstone", new Block(sharedLightstoneSettings()));
    public static final Block POLISHED_LIGHTSTONE_SLAB = register("polished_lightstone_slab", new SlabBlock(sharedLightstoneSettings()));
    public static final Block POLISHED_LIGHTSTONE_STAIRS = register("polished_lightstone_stairs", new SoulIceStairBlock(POLISHED_LIGHTSTONE.defaultBlockState(), sharedLightstoneSettings()));
    public static final Block POLISHED_LIGHTSTONE_WALL = register("polished_lightstone_wall", new WallBlock(sharedLightstoneSettings()));

    public static final Block LIGHTSTONE_BRICKS = register("lightstone_bricks", new Block(sharedLightstoneSettings()));
    public static final Block LIGHTSTONE_BRICK_SLAB = register("lightstone_brick_slab", new SlabBlock(sharedLightstoneSettings()));
    public static final Block LIGHTSTONE_BRICK_STAIRS = register("lightstone_brick_stairs", new SoulIceStairBlock(LIGHTSTONE_BRICKS.defaultBlockState(), sharedLightstoneSettings()));
    public static final Block LIGHTSTONE_BRICK_WALL = register("lightstone_brick_wall", new WallBlock(sharedLightstoneSettings()));

    public static final Block HARDENED_LIGHTSTONE = register("hardened_lightstone", new Block(sharedLightstoneSettings()));
    public static final Block HARDENED_LIGHTSTONE_SLAB = register("hardened_lightstone_slab", new SlabBlock(sharedLightstoneSettings()));
    public static final Block HARDENED_LIGHTSTONE_STAIRS = register("hardened_lightstone_stairs", new SoulIceStairBlock(HARDENED_LIGHTSTONE.defaultBlockState(), sharedLightstoneSettings()));
    public static final Block HARDENED_LIGHTSTONE_WALL = register("hardened_lightstone_wall", new WallBlock(sharedLightstoneSettings()));

    public static final Block MULVITE = register("mulvite", new Block(sharedLightstoneSettings()));
    public static final Block IGNIDIA_ICE = register("ignidia_ice", new IgnidiaIceBlock(BlockBehaviour.Properties.of().strength(3.0F).friction(0.5f).sound(SoundType.GLASS).noOcclusion().isValidSpawn((blockStatex, blockGetter, blockPos, entityType) -> entityType == EntityType.CREEPER).isRedstoneConductor((blockState, blockGetter, blockPos) -> false)));


    public static final Block RUJONE_BERRY_BUSH = register("rujone_berry_bush", new RujoneBerryBushBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SWEET_BERRY_BUSH)));
    public static final Block ORANGE_ROSE = register("orange_rose", new FlowerBlock(MobEffects.FIRE_RESISTANCE, 120, BlockBehaviour.Properties.of().instabreak().dynamicShape().sound(SoundType.GRASS).noCollission()));


    public static BlockBehaviour.Properties sharedSettings() {
        return BlockBehaviour.Properties.of().strength(3.0F).friction(0.999f).sound(SoundType.GLASS);
    }

    public static BlockBehaviour.Properties sharedLightstoneSettings() {
        return BlockBehaviour.Properties.of().strength(4f).sound(LIGHTSTONE_SOUNDS);
    }

    private static Block register(String name, Block block) {
        return Registry.register(BuiltInRegistries.BLOCK, ResourceLocation.fromNamespaceAndPath("soul_ice", name), block);
    }

    public static void init() {}
}
