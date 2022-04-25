package io.siuolplex.soul_ice.forge.util;

import io.siuolplex.soul_ice.SoulIce;
import io.siuolplex.soul_ice.mixin.AbstractBlockAccessor;

import static io.siuolplex.soul_ice.forge.registry.SoulIceBlocks.*;

public class SoulIceSlipSetter {
    public static void soulIceSlip(float slipperiness) {
        ((AbstractBlockAccessor)SOUL_ICE.get()).setSlipperiness(slipperiness);
        ((AbstractBlockAccessor)SOUL_ICE_SLAB.get()).setSlipperiness(slipperiness);
        ((AbstractBlockAccessor)SOUL_ICE_STAIRS.get()).setSlipperiness(slipperiness);
        ((AbstractBlockAccessor)SOUL_ICE_WALL.get()).setSlipperiness(slipperiness);
        ((AbstractBlockAccessor)SOUL_ICE_GATE.get()).setSlipperiness(slipperiness);
        ((AbstractBlockAccessor)POLISHED_SOUL_ICE.get()).setSlipperiness(slipperiness);
        ((AbstractBlockAccessor)POLISHED_SOUL_ICE_SLAB.get()).setSlipperiness(slipperiness);
        ((AbstractBlockAccessor)POLISHED_SOUL_ICE_STAIRS.get()).setSlipperiness(slipperiness);
        ((AbstractBlockAccessor)POLISHED_SOUL_ICE_WALL.get()).setSlipperiness(slipperiness);
        ((AbstractBlockAccessor)POLISHED_SOUL_ICE_GATE.get()).setSlipperiness(slipperiness);
        ((AbstractBlockAccessor)SOUL_ICE_BRICKS.get()).setSlipperiness(slipperiness);
        ((AbstractBlockAccessor)SOUL_ICE_BRICK_SLAB.get()).setSlipperiness(slipperiness);
        ((AbstractBlockAccessor)SOUL_ICE_BRICK_STAIRS.get()).setSlipperiness(slipperiness);
        ((AbstractBlockAccessor)SOUL_ICE_BRICK_WALL.get()).setSlipperiness(slipperiness);
        ((AbstractBlockAccessor)SOUL_ICE_BRICK_GATE.get()).setSlipperiness(slipperiness);
        SoulIce.LOGGER.info("Soul Ice Slipperiness set to " + slipperiness);
    }
}
