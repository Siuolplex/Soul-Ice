package io.siuolplex.soul_ice.mixin;

import io.siuolplex.soul_ice.registry.SoulIceEnchantments;
import io.siuolplex.soul_ice.util.SoulIceVelocityFixer;
import net.minecraft.block.BlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = Entity.class, priority = 999)
public abstract class EntityMixin {
    @Inject(method = "slowMovement(Lnet/minecraft/block/BlockState;Lnet/minecraft/util/math/Vec3d;)V", at = @At("HEAD"), cancellable = true)
    private void soulIce$forUnfaltering(BlockState state, Vec3d multiplier, CallbackInfo ci) {
        if (unfalteringCheck()) ci.cancel();
    }

    @Inject(method = "getVelocityMultiplier", at = @At("RETURN"), cancellable = true)
    private void soulIce$velocityMultiplierFix(CallbackInfoReturnable<Float> cir) {
        if (unfalteringCheck()) cir.setReturnValue(1.0f);
    }

    @Inject(method = "getVelocityAffectingPos()Lnet/minecraft/util/math/BlockPos;", at = @At("RETURN"), cancellable = true)
    private void soulIce$nullifiedVelocity(CallbackInfoReturnable<BlockPos> cir) {
        cir.setReturnValue(SoulIceVelocityFixer.velocityFix(((Entity)((Object) this))));
    }

    private boolean unfalteringCheck() {
        return ((Entity)((Object) this) instanceof LivingEntity player && EnchantmentHelper.getEquipmentLevel(SoulIceEnchantments.UNFALTERING, player) > 0);
    }
}
