package io.siuolplex.soul_ice.forge.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import static io.siuolplex.soul_ice.forge.registry.SoulIceItems.*;


@Mixin(value = PlayerEntity.class)
public abstract class ForgePlayerEntityMixin {
    @Shadow
    public abstract PlayerInventory getInventory();

    @Inject(method = "damage(Lnet/minecraft/entity/damage/DamageSource;F)Z", at = @At("TAIL"))
    public void soulIceForge$damage(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        if (cactusArmorCheck() && !source.isMagic() && !source.isExplosive() && source.getSource() instanceof LivingEntity b) {
            b.damage(DamageSource.thorns((Entity)((Object) this)), 1F);
        }
    }

    public boolean cactusArmorCheck() {
        PlayerInventory a = this.getInventory();
        return (a.getArmorStack(3).isOf(CACTUS_HELMET.get()) || a.getArmorStack(2).isOf(CACTUS_CHESTPLATE.get()) || a.getArmorStack(1).isOf(CACTUS_LEGGINGS.get()) || a.getArmorStack(0).isOf(CACTUS_BOOTS.get()));
    }
}

