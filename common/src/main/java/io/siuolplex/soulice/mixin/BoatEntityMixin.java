package io.siuolplex.soulice.mixin;

import net.minecraft.util.Mth;
import net.minecraft.world.entity.vehicle.Boat;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

// Mixin from Peculiar Pieces by AmyMialee, with explicit permission from her. TY!
@Mixin(Boat.class)
public class BoatEntityMixin {
    @Shadow
    private float deltaRotation;

    @Inject(method = "floatBoat", at = @At("TAIL"))
    private void soulIce$spinVelocityCap(CallbackInfo ci) {
        deltaRotation = Mth.clamp(deltaRotation, -90, 90);
    }

    @Inject(method = "getGroundFriction", at = @At("TAIL"), cancellable = true)
    public void soulIce$boatSlipperinessCap(CallbackInfoReturnable<Float> cir) {
        cir.setReturnValue(Math.min(cir.getReturnValue(), 1));
    }
}