package io.siuolplex.soul_ice.forge.mixin;

import io.siuolplex.soul_ice.forge.registry.SoulIceEnchantments;
import io.siuolplex.soul_ice.registry.SoulIceTags;
import io.siuolplex.soul_ice.util.SoulIceVelocityFixer;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = Entity.class, priority = 999)
public abstract class ForgeEntityMixin {
    @Shadow
    public abstract Box getBoundingBox();
//
    @Inject(method = "getVelocityMultiplier", at = @At("RETURN"), cancellable = true)
    private void soulIceForge$velocityMultiplierFix(CallbackInfoReturnable<Float> cir) {
        if (((Entity)((Object) this) instanceof LivingEntity player && EnchantmentHelper.getEquipmentLevel(SoulIceEnchantments.UNFALTERING.get(), player) > 0)) {
            cir.setReturnValue(1.0f);
        }
    }

    @Inject(method = "getVelocityAffectingPos()Lnet/minecraft/util/math/BlockPos;", at = @At("RETURN"), cancellable = true)
    private void soulIceForge$nullifiedVelocity(CallbackInfoReturnable<BlockPos> cir) {
        BlockPos velocityAffectingPos = SoulIceVelocityFixer.velocityFix(((Entity)((Object) this)));
        if (((Entity)((Object) this) instanceof LivingEntity player && EnchantmentHelper.getEquipmentLevel(SoulIceEnchantments.UNFALTERING.get(), player) > 0 && blockPosCheck(player))) {
            velocityAffectingPos = new BlockPos(cir.getReturnValue().getX(), this.getBoundingBox().minY + 0.5000001, cir.getReturnValue().getZ());
        }
        cir.setReturnValue(velocityAffectingPos);
    }

    private static boolean blockPosCheck(LivingEntity player) {
        World world = player.getWorld();
        BlockPos blockPos = player.getBlockPos();
        return (world.getBlockState(blockPos.down()).getBlock().getSlipperiness() != 0.6f || world.getBlockState(blockPos).getBlock().getSlipperiness() != 0.6f);
    }
}
