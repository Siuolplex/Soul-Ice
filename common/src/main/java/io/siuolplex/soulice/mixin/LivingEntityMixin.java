package io.siuolplex.soulice.mixin;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {
    @Shadow public abstract Iterable<ItemStack> getArmorSlots();

    // Based on Pollinators Paradise mixin, which is based on the old Soul Ice redirect
    // Source found here under LGPLv3 https://github.com/HestiMae/pollinators-paradise/blob/1.20/src/main/java/garden/hestia/pollinators_paradise/mixin/LivingEntityMixin.java#L78
    @SuppressWarnings("ConstantConditions")
    @ModifyVariable(method = "travel", at = @At(value = "INVOKE_ASSIGN", target = "Lnet/minecraft/world/level/block/Block;getFriction()F"))
    public float soulIce$slipperinessModifier(float lament) {
        /*if (EnchantmentHelper.getEnchantmentLevel(SoulIceEnchantments.UNFALTERING, (LivingEntity)((Object) this)) > 0) lament = 0.6f;
        else if (EnchantmentHelper.getEnchantmentLevel(SoulIceEnchantments.SLIPPERINESS, (LivingEntity)((Object) this)) > 0) {
            switch (EnchantmentHelper.getEnchantmentLevel(SoulIceEnchantments.SLIPPERINESS, (LivingEntity) ((Object) this))) {
                case 1 -> lament = Math.max(lament, 0.98F);
                case 2 -> lament = Math.max(lament, 0.989F);
                case 3 -> lament = Math.max(lament, 0.995f);
            }
        }*/


        return lament;
    }
}
