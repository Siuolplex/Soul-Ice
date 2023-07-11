package io.siuolplex.soulice.fabric;

import io.siuolplex.soulice.registry.SoulIceBlocks;
import io.siuolplex.soulice.registry.SoulIceEntityTypes;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.world.entity.Entity;

public class SoulIceFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(SoulIceEntityTypes.GLUTEN_BALL, ThrownItemRenderer::new);
        BlockRenderLayerMap.INSTANCE.putBlocks(RenderType.cutout(), SoulIceBlocks.ORANGE_ROSE);
        BlockRenderLayerMap.INSTANCE.putBlocks(RenderType.cutout(), SoulIceBlocks.RUJONE_BERRY_BUSH);

    }
}
