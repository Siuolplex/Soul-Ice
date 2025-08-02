package io.siuolplex.soulice.fabric;

import io.siuolplex.soulice.registry.SoulIceBlocks;
import io.siuolplex.soulice.registry.SoulIceEntityTypes;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;

public class SoulIceFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(SoulIceEntityTypes.GLUTEN_BALL, ThrownItemRenderer::new);
        EntityRendererRegistry.register(SoulIceEntityTypes.BAKED_GLUTEN_BALL, ThrownItemRenderer::new);
        EntityRendererRegistry.register(SoulIceEntityTypes.NINE_VOLT_BATTERY, ThrownItemRenderer::new);
        BlockRenderLayerMap.INSTANCE.putBlocks(RenderType.cutout(), SoulIceBlocks.ORANGE_ROSE);
        BlockRenderLayerMap.INSTANCE.putBlocks(RenderType.cutout(), SoulIceBlocks.RUJONE_BERRY_BUSH);
        //BlockRenderLayerMap.INSTANCE.putBlock(SoulIceBlocks.IGNIDIA_ICE, RenderType.translucent());



    }
}
