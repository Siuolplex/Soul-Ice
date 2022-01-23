package io.siuolplex.soul_ice.blocks;

import io.siuolplex.soul_ice.SoulIce;
import io.siuolplex.soul_ice.mixin.AbstractBlockMixin;

import static io.siuolplex.soul_ice.SoulIceBlocks.*;

public class SoulIceSlipSetter {
    public SoulIceSlipSetter(float slipperiness) {
        ((AbstractBlockMixin)SOUL_ICE).setSlipperiness(slipperiness);
        ((AbstractBlockMixin)SOUL_ICE_SLAB).setSlipperiness(slipperiness);
        ((AbstractBlockMixin)SOUL_ICE_STAIRS).setSlipperiness(slipperiness);
        ((AbstractBlockMixin)SOUL_ICE_WALL).setSlipperiness(slipperiness);
        ((AbstractBlockMixin)SOUL_ICE_GATE).setSlipperiness(slipperiness);
        ((AbstractBlockMixin)POLISHED_SOUL_ICE).setSlipperiness(slipperiness);
        ((AbstractBlockMixin)POLISHED_SOUL_ICE_SLAB).setSlipperiness(slipperiness);
        ((AbstractBlockMixin)POLISHED_SOUL_ICE_STAIRS).setSlipperiness(slipperiness);
        ((AbstractBlockMixin)POLISHED_SOUL_ICE_WALL).setSlipperiness(slipperiness);
        ((AbstractBlockMixin)POLISHED_SOUL_ICE_GATE).setSlipperiness(slipperiness);
        ((AbstractBlockMixin)SOUL_ICE_BRICKS).setSlipperiness(slipperiness);
        ((AbstractBlockMixin)SOUL_ICE_BRICK_SLAB).setSlipperiness(slipperiness);
        ((AbstractBlockMixin)SOUL_ICE_BRICK_STAIRS).setSlipperiness(slipperiness);
        ((AbstractBlockMixin)SOUL_ICE_BRICK_WALL).setSlipperiness(slipperiness);
        ((AbstractBlockMixin)SOUL_ICE_BRICK_GATE).setSlipperiness(slipperiness);
        SoulIce.LOGGER.info("Soul Ice Slipperiness set to " + slipperiness);
    }
}
