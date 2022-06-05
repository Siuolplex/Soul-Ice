package io.siuolplex.soul_ice.mixin;

import io.siuolplex.soul_ice.registry.SoulIceBlocks;
import io.siuolplex.soul_ice.registry.SoulIceEnchantments;
import io.siuolplex.soul_ice.registry.SoulIceItems;
import net.minecraft.block.Block;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = LivingEntity.class)
public abstract class LivingEntityMixin {
    @Shadow
    public abstract Iterable<ItemStack> getArmorItems();

    @Redirect(method = "travel(Lnet/minecraft/util/math/Vec3d;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/Block;getSlipperiness()F"))
    public float soulIce$slipperinessModifier(Block instance) {
        if (EnchantmentHelper.getEquipmentLevel(SoulIceEnchantments.UNFALTERING, (LivingEntity)((Object) this)) > 0) return 0.6f;

        for (ItemStack itemStack : getArmorItems()) {
            if (itemStack.getItem().equals(SoulIceItems.SOUL_ICE_COVERED_LEATHER_BOOTS)) {
                if (instance.getSlipperiness() < SoulIceBlocks.SOUL_ICE.getSlipperiness()) {
                    return SoulIceBlocks.SOUL_ICE.getSlipperiness();
                }
            }
        }

        return instance.getSlipperiness();
    }
}
