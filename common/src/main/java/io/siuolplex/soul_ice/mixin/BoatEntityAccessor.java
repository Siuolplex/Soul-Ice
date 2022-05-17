package io.siuolplex.soul_ice.mixin;

import net.minecraft.entity.vehicle.BoatEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(BoatEntity.class)
public interface BoatEntityAccessor {
    @Accessor public float getVelocityDecay();

    @Accessor public BoatEntity.Location getLocation();

    @Accessor public void setYawVelocity(float yawVelocity);

    @Accessor float getYawVelocity();
}
