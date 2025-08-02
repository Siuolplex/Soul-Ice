package io.siuolplex.soulice.mixin;

import io.siuolplex.soulice.registry.SoulIceEnchantments;
import io.siuolplex.soulice.registry.SoulIceItems;
import io.siuolplex.untitledlib.util.RegistryUtil;
import net.minecraft.core.Holder;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {
    public LivingEntityMixin(EntityType<?> entityType, Level level) {
        super(entityType, level);
    }

    @Shadow public abstract Iterable<ItemStack> getArmorSlots();

    @Shadow public abstract ItemStack getItemBySlot(EquipmentSlot equipmentSlot);

    // Based on Pollinators Paradise mixin, which is based on the old Soul Ice redirect
    // Source found here under LGPLv3 https://github.com/HestiMae/pollinators-paradise/blob/1.20/src/main/java/garden/hestia/pollinators_paradise/mixin/LivingEntityMixin.java#L78
    @SuppressWarnings("ConstantConditions")
    @ModifyVariable(method = "travel", at = @At(value = "INVOKE_ASSIGN", target = "Lnet/minecraft/world/level/block/Block;getFriction()F"))
    //todo: I could make this defined in the enchantment probs.
    public float soulIce$slipperinessModifier(float lament) {
        RegistryAccess registryAccess = this.registryAccess();
        Holder<Enchantment> unfaltering = RegistryUtil.grabEnchantFromRegistry(registryAccess, SoulIceEnchantments.UNFALTERING);
        Holder<Enchantment> slipperiness = RegistryUtil.grabEnchantFromRegistry(registryAccess, SoulIceEnchantments.SLIPPERINESS);
        ItemStack boots = this.getItemBySlot(EquipmentSlot.FEET);

        if (boots.has(DataComponents.ENCHANTMENTS)) {
            if (boots.get(DataComponents.ENCHANTMENTS).getLevel(unfaltering) > 0) lament = 0.6f;
            else switch (boots.get(DataComponents.ENCHANTMENTS).getLevel(slipperiness)) {
                case 1 -> lament = Math.max(lament, 0.98F);
                case 2 -> lament = Math.max(lament, 0.989F);
                case 3 -> lament = Math.max(lament, 0.995f);
            }
        }

        return lament;
    }

    @Inject(method = "eat(Lnet/minecraft/world/level/Level;Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/food/FoodProperties;)Lnet/minecraft/world/item/ItemStack;", at = @At(value = "TAIL"))
    public void eatTheBattery(Level level, ItemStack food, FoodProperties foodProperties, CallbackInfoReturnable<ItemStack> cir) {
        if (food.is(SoulIceItems.NINE_VOLT_BATTERY)) {

        }
    }
}
