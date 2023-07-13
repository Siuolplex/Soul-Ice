package io.siuolplex.soulice.mixin;

import io.siuolplex.soulice.entity.facets.Glutenous;
import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(DamageSource.class)
public class DamageSourceMixin {
    @Shadow @Final @Nullable private Entity causingEntity;

    @Shadow @Final @Nullable private Entity directEntity;

    @Inject(method = "getLocalizedDeathMessage(Lnet/minecraft/world/entity/LivingEntity;)Lnet/minecraft/network/chat/Component;", at = @At(value = "FIELD", target = "Lnet/minecraft/world/damagesource/DamageSource;causingEntity:Lnet/minecraft/world/entity/Entity;", ordinal = 3), cancellable = true)
    private void soulIce$doTheCoolDeathMessageItsAnEasterEgg(LivingEntity livingEntity, CallbackInfoReturnable<Component> cir) {
        if (this.directEntity instanceof Glutenous) {
            Component component = this.causingEntity == null ? this.directEntity.getDisplayName() : this.causingEntity.getDisplayName();
            //cir.setReturnValue(Component.translatable("death.attack.anvil.player"));
            cir.setReturnValue(Component.translatable("death.soul_ice.attack.gluten_ball", livingEntity.getDisplayName(), component));
        }
    }
}
