package io.siuolplex.soul_ice.fabric.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static io.siuolplex.soul_ice.fabric.registry.SoulIceItems.*;
import static io.siuolplex.soul_ice.fabric.registry.SoulIceItems.CACTUS_BOOTS;

@Mixin(value = PlayerEntity.class)
public abstract class FabricPlayerEntityMixin {
    @Shadow public abstract PlayerInventory getInventory();

    @Inject(method = "damage(Lnet/minecraft/entity/damage/DamageSource;F)Z", at = @At("TAIL"))
    public void soulIceFabric$damage(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        if (cactusArmorCheck() && !source.isMagic() && !source.isExplosive() && !source.isProjectile() && source.getSource() instanceof LivingEntity b) {
            b.damage(DamageSource.thorns(((PlayerEntity)((Object) this))), 1F); //todo: Make this a non-magical thorns
        }
    }

    public boolean cactusArmorCheck() {
        PlayerInventory a = this.getInventory();
        return (a.getArmorStack(3).isOf(CACTUS_HELMET) || a.getArmorStack(2).isOf(CACTUS_CHESTPLATE) || a.getArmorStack(1).isOf(CACTUS_LEGGINGS) || a.getArmorStack(0).isOf(CACTUS_BOOTS));
    }
}
