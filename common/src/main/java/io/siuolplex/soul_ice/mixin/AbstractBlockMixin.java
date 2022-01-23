package io.siuolplex.soul_ice.mixin;

import net.minecraft.block.AbstractBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(AbstractBlock.class)
public interface AbstractBlockMixin {
    @Mutable
    @Accessor("slipperiness")
    public void setSlipperiness(float slipperiness);
}
