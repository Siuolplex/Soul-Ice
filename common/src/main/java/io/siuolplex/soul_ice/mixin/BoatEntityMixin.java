package io.siuolplex.soul_ice.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.util.math.MathHelper;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

// Mixin from Peculiar Pieces by AmyMialee, with explicit permission from her. TY!
@Mixin(BoatEntity.class)
public class BoatEntityMixin {
    @Shadow private float yawVelocity;

    @Inject(method = "updateVelocity", at = @At("TAIL"))
    private void soulIce$spinVelocityCap(CallbackInfo ci) {
        yawVelocity = MathHelper.clamp(yawVelocity, -90, 90);
    }

    @Inject(method = "method_7548", at = @At("TAIL"), cancellable = true)
    public void soulIce$boatSlipperinessCap(CallbackInfoReturnable<Float> cir) {
        cir.setReturnValue(Math.min(cir.getReturnValue(), 1));
    }
}
