package io.siuolplex.soulice.mixin;

import io.siuolplex.soulice.registry.SoulIceBlocks;
import io.siuolplex.soulice.registry.SoulIceEnchantments;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {
    @Shadow public abstract Iterable<ItemStack> getArmorSlots();

    @Redirect(method = "travel", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/Block;getFriction()F"))
    public float soulIceQuilt$slipperinessModifier(Block instance) {
        float retort = instance.getFriction();
        if (EnchantmentHelper.getEnchantmentLevel(SoulIceEnchantments.UNFALTERING, (LivingEntity)((Object) this)) > 0) retort = 0.6f;
        else if (EnchantmentHelper.getEnchantmentLevel(SoulIceEnchantments.SLIPPERINESS, (LivingEntity)((Object) this)) > 0) {
            switch (EnchantmentHelper.getEnchantmentLevel(SoulIceEnchantments.SLIPPERINESS, (LivingEntity) ((Object) this))) {
                case 1 -> retort = Math.max(retort, 0.98F);
                case 2 -> retort = Math.max(retort, 0.989F);
                case 3 -> retort = Math.max(retort, 0.995f);
            }
        }

        return retort;
    }
}
