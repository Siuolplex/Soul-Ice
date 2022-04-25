package io.siuolplex.soul_ice.mixin;

import net.minecraft.block.AbstractBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(AbstractBlock.class)
public interface AbstractBlockAccessor {
    @Mutable
    @Accessor("slipperiness")
    public void setSlipperiness(float slipperiness);
}
