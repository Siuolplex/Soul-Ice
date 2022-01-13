package io.siuolplex.soul_ice.mixin;

import net.minecraft.block.FenceGateBlock;
import net.minecraft.block.WallBlock;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = Entity.class, priority = 999)
public class EntityMixin {
    @Inject(method = "getVelocityAffectingPos()Lnet/minecraft/util/math/BlockPos;", at = @At("RETURN"), cancellable = true)
    private void injected(CallbackInfoReturnable<BlockPos> cir) {
        BlockPos velocityAffectingPos = new BlockPos(cir.getReturnValue().getX(), ((Entity)((Object) this)).getBoundingBox().minY - 0.2500001, cir.getReturnValue().getZ());
        if (((Entity)((Object) this)).getWorld().getBlockState(((Entity)((Object) this)).getBlockPos().down()).getBlock() instanceof WallBlock || ((Entity)((Object) this)).getWorld().getBlockState(((Entity)((Object) this)).getBlockPos().down()).getBlock() instanceof FenceGateBlock) velocityAffectingPos = cir.getReturnValue();
        cir.setReturnValue(velocityAffectingPos);
    }
}
