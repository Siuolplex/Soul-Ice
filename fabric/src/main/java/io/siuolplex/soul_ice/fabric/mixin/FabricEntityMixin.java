package io.siuolplex.soul_ice.fabric.mixin;

import io.siuolplex.soul_ice.fabric.registry.SoulIceEnchantments;
import io.siuolplex.soul_ice.registry.SoulIceTags;
import io.siuolplex.soul_ice.util.SoulIceVelocityFixer;
import net.minecraft.block.BlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static io.siuolplex.soul_ice.fabric.registry.SoulIceItems.*;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

@Mixin(value = Entity.class, priority = 999)
public abstract class FabricEntityMixin {
    @Inject(method = "slowMovement(Lnet/minecraft/block/BlockState;Lnet/minecraft/util/math/Vec3d;)V", at = @At("HEAD"), cancellable = true)
    private void soulIceFabric$forUnfaltering(BlockState state, Vec3d multiplier, CallbackInfo ci) {
        if (unfalteringCheck()) ci.cancel();
    }

    @Inject(method = "getVelocityMultiplier", at = @At("RETURN"), cancellable = true)
    private void soulIceFabric$velocityMultiplierFix(CallbackInfoReturnable<Float> cir) {
        if (unfalteringCheck()) cir.setReturnValue(1.0f);
    }

    @Inject(method = "getVelocityAffectingPos()Lnet/minecraft/util/math/BlockPos;", at = @At("RETURN"), cancellable = true)
    private void soulIceFabric$nullifiedVelocity(CallbackInfoReturnable<BlockPos> cir) {
        cir.setReturnValue(SoulIceVelocityFixer.velocityFix(((Entity)((Object) this))));
    }

    private boolean unfalteringCheck() {
        return ((Entity)((Object) this) instanceof LivingEntity player && EnchantmentHelper.getEquipmentLevel(SoulIceEnchantments.UNFALTERING, player) > 0);
    }
}
