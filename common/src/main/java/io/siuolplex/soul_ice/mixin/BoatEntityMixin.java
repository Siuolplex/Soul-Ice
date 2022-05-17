package io.siuolplex.soul_ice.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.vehicle.BoatEntity;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(BoatEntity.class)
public class BoatEntityMixin {
    //Lnet/minecraft/entity/vehicle/BoatEntity;yawVelocity:F
    @Redirect(method = "updateVelocity", at = @At(value = "FIELD", target = "Lnet/minecraft/entity/vehicle/BoatEntity;yawVelocity:F", opcode = Opcodes.PUTFIELD))
    private void soulIce$noMoreCrashes(BoatEntity instance, float value) {
        if (value > 5F && ((BoatEntityAccessor) ((BoatEntity)((Object) this))).getLocation().equals(BoatEntity.Location.ON_LAND)) {
            ((BoatEntityAccessor) ((BoatEntity)((Object) this))).setYawVelocity(5);
        } else if (value < -5F && ((BoatEntityAccessor) ((BoatEntity)((Object) this))).getLocation().equals(BoatEntity.Location.ON_LAND)) {
            ((BoatEntityAccessor) ((BoatEntity)((Object) this))).setYawVelocity(-5);
        } else {
            float a = ((BoatEntityAccessor) ((BoatEntity)((Object) this))).getYawVelocity() *  ((BoatEntityAccessor) ((BoatEntity)((Object) this))).getVelocityDecay();
        }
    }
}
