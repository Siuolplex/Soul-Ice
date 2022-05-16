package io.siuolplex.soul_ice.forge.mixin;

import io.siuolplex.soul_ice.forge.registry.SoulIceBlocks;
import io.siuolplex.soul_ice.forge.registry.SoulIceEnchantments;
import io.siuolplex.soul_ice.forge.registry.SoulIceItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = LivingEntity.class)
public abstract class ForgeLivingEntityMixin {
    @Shadow public abstract Iterable<ItemStack> getArmorItems();

    @Redirect(method = "travel(Lnet/minecraft/util/math/Vec3d;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/BlockState;getFriction(Lnet/minecraft/world/WorldView;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/entity/Entity;)F"))
    public float soulIceForge$slipperinessModifier(BlockState instance, WorldView view, BlockPos pos, Entity entity) {
        if (EnchantmentHelper.getEquipmentLevel(SoulIceEnchantments.UNFALTERING.get(), (LivingEntity)((Object) this)) > 0) return 0.6f;

        for (ItemStack itemStack : getArmorItems()) {
            if (itemStack.getItem().equals(SoulIceItems.SOUL_ICE_COVERED_LEATHER_BOOTS.get())) {
                if (instance.getBlock().getSlipperiness() < SoulIceBlocks.SOUL_ICE.get().getSlipperiness()) {
                    return SoulIceBlocks.SOUL_ICE.get().getSlipperiness();
                }
            }
        }

        return instance.getBlock().getSlipperiness();
    }
}
