package io.siuolplex.soul_ice.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

// Mixin from PeculiarPieces. Thank you Amy for this mixin.
@Mixin(BoatEntity.class)
public abstract class BoatEntityMixin extends Entity {
    @Shadow private float yawVelocity;

    public BoatEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Inject(method = "updateVelocity", at = @At("TAIL"))
    private void soulIce$SpinVelocityCap(CallbackInfo ci) {
        yawVelocity = MathHelper.clamp(yawVelocity, -90, 90);
    }

    @Inject(method = "method_7548", at = @At("TAIL"), cancellable = true)
    public void soulIce$BoatSlipperinessCap(CallbackInfoReturnable<Float> cir) {
        cir.setReturnValue(Math.min(cir.getReturnValue(), 1));
    }
}
