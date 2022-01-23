package io.siuolplex.soul_ice.mixin;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.FenceGateBlock;
import net.minecraft.block.WallBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.tag.BlockTags;
import net.minecraft.tag.Tag;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = Entity.class, priority = 999)
public abstract class EntityMixin {
    @Shadow public abstract Box getBoundingBox();

    @Inject(method = "getVelocityAffectingPos()Lnet/minecraft/util/math/BlockPos;", at = @At("RETURN"), cancellable = true)
    private void soulIce$velocityPositionFix(CallbackInfoReturnable<BlockPos> cir) {
        BlockPos velocityAffectingPos = new BlockPos(cir.getReturnValue().getX(), ((Entity)((Object) this)).getBoundingBox().minY - 0.2500001, cir.getReturnValue().getZ());
        if (((Entity)((Object) this)).getWorld().getBlockState(((Entity)((Object) this)).getBlockPos().down()).getBlock() instanceof WallBlock || ((Entity)((Object) this)).getWorld().getBlockState(((Entity)((Object) this)).getBlockPos().down()).getBlock() instanceof FenceGateBlock) velocityAffectingPos = cir.getReturnValue();
        //Todo: This.
        /*if (((Entity)((Object) this) instanceof LivingEntity player && player.getEquippedStack(EquipmentSlot.FEET).isOf(Items.LEATHER_BOOTS) && player.getWorld().getBlockState(player.getBlockPos().down()).isIn(BlockTags.ICE))) {
            Block downblock = player.getWorld().getBlockState(player.getBlockPos().down()).getBlock();
            if (downblock.equals(Blocks.ICE) || downblock.equals(Blocks.FROSTED_ICE) || downblock.equals(Blocks.PACKED_ICE) || downblock.equals(Blocks.BLUE_ICE)) velocityAffectingPos = new BlockPos(cir.getReturnValue().getX(), this.getBoundingBox().minY + 0.0001, cir.getReturnValue().getZ());
        }*/
        cir.setReturnValue(velocityAffectingPos);
    }
}
