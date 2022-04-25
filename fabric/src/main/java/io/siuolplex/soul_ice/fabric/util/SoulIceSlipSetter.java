package io.siuolplex.soul_ice.fabric.util;

import io.siuolplex.soul_ice.SoulIce;
import io.siuolplex.soul_ice.mixin.AbstractBlockAccessor;

import static io.siuolplex.soul_ice.fabric.registry.SoulIceBlocks.*;

public class SoulIceSlipSetter {
    public static void soulIceSlip(float slipperiness) {
        ((AbstractBlockAccessor)SOUL_ICE).setSlipperiness(slipperiness);
        ((AbstractBlockAccessor)SOUL_ICE_SLAB).setSlipperiness(slipperiness);
        ((AbstractBlockAccessor)SOUL_ICE_STAIRS).setSlipperiness(slipperiness);
        ((AbstractBlockAccessor)SOUL_ICE_WALL).setSlipperiness(slipperiness);
        ((AbstractBlockAccessor)SOUL_ICE_GATE).setSlipperiness(slipperiness);
        ((AbstractBlockAccessor)POLISHED_SOUL_ICE).setSlipperiness(slipperiness);
        ((AbstractBlockAccessor)POLISHED_SOUL_ICE_SLAB).setSlipperiness(slipperiness);
        ((AbstractBlockAccessor)POLISHED_SOUL_ICE_STAIRS).setSlipperiness(slipperiness);
        ((AbstractBlockAccessor)POLISHED_SOUL_ICE_WALL).setSlipperiness(slipperiness);
        ((AbstractBlockAccessor)POLISHED_SOUL_ICE_GATE).setSlipperiness(slipperiness);
        ((AbstractBlockAccessor)SOUL_ICE_BRICKS).setSlipperiness(slipperiness);
        ((AbstractBlockAccessor)SOUL_ICE_BRICK_SLAB).setSlipperiness(slipperiness);
        ((AbstractBlockAccessor)SOUL_ICE_BRICK_STAIRS).setSlipperiness(slipperiness);
        ((AbstractBlockAccessor)SOUL_ICE_BRICK_WALL).setSlipperiness(slipperiness);
        ((AbstractBlockAccessor)SOUL_ICE_BRICK_GATE).setSlipperiness(slipperiness);
        SoulIce.LOGGER.info("Soul Ice Slipperiness set to " + slipperiness);
    }
}
