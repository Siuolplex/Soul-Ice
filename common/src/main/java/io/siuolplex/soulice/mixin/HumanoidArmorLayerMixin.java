package io.siuolplex.soulice.mixin;

import net.minecraft.client.renderer.entity.ArmorStandRenderer;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ArmorItem;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(HumanoidArmorLayer.class)
public class HumanoidArmorLayerMixin {
    @Inject(at = @At("HEAD"), method = "getArmorLocation", cancellable = true)
    private void soulIce$getTexture(ArmorItem item, boolean legs, @Nullable String overlay, CallbackInfoReturnable<ResourceLocation> cir) {
        if (BuiltInRegistries.ITEM.getKey(item).getNamespace().equals("soul_ice")) {
            String string = "textures/models/armor/" + item.getMaterial().getName() + "_layer_" + (legs ? 2 : 1) + (overlay == null ? "" : "_" + overlay) + ".png";
            cir.setReturnValue(new ResourceLocation("soul_ice", string));
        }
    }
}
