package io.siuolplex.soul_ice;

import io.siuolplex.soul_ice.network.fabric.ClientEventHelper;
import io.siuolplex.soul_ice.registry.SoulIceBlocks;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.util.DyeColor;
import net.minecraft.util.registry.Registry;

public class SoulIceClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ClientEventHelper.registerClientPackets();
        BlockRenderLayerMap.INSTANCE.putBlock(SoulIceBlocks.ORANGE_ROSE, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(SoulIceBlocks.RUJONE_BERRY_BUSH, RenderLayer.getCutout());
        for (DyeColor color : DyeColor.values()) {
            BlockRenderLayerMap.INSTANCE.putBlock(Registry.BLOCK.get(SoulIce.idFormatter(color.toString().toLowerCase() + "_plank_door")), RenderLayer.getCutout());
            BlockRenderLayerMap.INSTANCE.putBlock(Registry.BLOCK.get(SoulIce.idFormatter(color.toString().toLowerCase() + "_plank_trapdoor")), RenderLayer.getCutout());
        }
    }
}
