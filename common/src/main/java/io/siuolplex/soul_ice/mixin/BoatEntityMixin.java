package io.siuolplex.soul_ice.mixin;

import net.minecraft.entity.vehicle.BoatEntity;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(BoatEntity.class)
public class BoatEntityMixin {
    @Shadow private float velocityDecay;

    @Shadow private float yawVelocity;

    @Shadow private BoatEntity.Location location;

    //Lnet/minecraft/entity/vehicle/BoatEntity;yawVelocity:F
    @Redirect(method = "updateVelocity", at = @At(value = "FIELD", target = "Lnet/minecraft/entity/vehicle/BoatEntity;yawVelocity:F", opcode = Opcodes.PUTFIELD))
    private void soulIce$noMoreCrashes(BoatEntity instance, float value) {
        if (value > 5F && this.location.equals(BoatEntity.Location.ON_LAND)) {
            yawVelocity = 5F;
        } else if (value < -5F && this.location.equals(BoatEntity.Location.ON_LAND)) {
            yawVelocity = -5F;
        } else {
            yawVelocity *= velocityDecay;
        }
    }
}
