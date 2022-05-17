package io.siuolplex.soul_ice.quilt.mixin;

import io.siuolplex.soul_ice.util.SoulIceIDHandler;
import net.minecraft.client.render.entity.feature.ArmorFeatureRenderer;
import net.minecraft.item.ArmorItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

// Thanks, Tropheous Jay for letting me use the C+ Armor mixin for this, it's very useful!
// Found here https://github.com/ConsistencyPlus/ConsistencyPlus/blob/master/src/main/java/io/github/consistencyplus/consistency_plus/mixin/ArmorFeatureRendererMixin.java

@Mixin(ArmorFeatureRenderer.class)
public abstract class QuiltArmorFeatureRendererMixin {
    @Inject(at = @At("HEAD"), method = "getArmorTexture(Lnet/minecraft/item/ArmorItem;ZLjava/lang/String;)Lnet/minecraft/util/Identifier;", cancellable = true)
    private void soulIceQuilt$getTexture(ArmorItem item, boolean legs, @Nullable String overlay, CallbackInfoReturnable<Identifier> cir) {
        if (Registry.ITEM.getId(item).getNamespace().equals("soul_ice")) {
            String string = "textures/models/armor/" + item.getMaterial().getName() + "_layer_" + (legs ? 2 : 1) + (overlay == null ? "" : "_" + overlay) + ".png";
            cir.setReturnValue(SoulIceIDHandler.idFormatter(string));
        }
    }
}
