package io.siuolplex.soul_ice;

import io.siuolplex.soul_ice.network.quilt.ClientEventHelper;
import io.siuolplex.soul_ice.registry.SoulIceBlocks;
import io.siuolplex.soul_ice.util.Loader;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.util.DyeColor;
import net.minecraft.util.registry.Registry;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.client.ClientModInitializer;
import org.quiltmc.qsl.block.extensions.api.client.BlockRenderLayerMap;

public class SoulIceClient implements ClientModInitializer {
    @Override
    public void onInitializeClient(ModContainer mod) {
        ClientEventHelper.registerClientPackets();
        BlockRenderLayerMap.put(RenderLayer.getCutout(), SoulIceBlocks.ORANGE_ROSE);
        BlockRenderLayerMap.put(RenderLayer.getCutout(), SoulIceBlocks.RUJONE_BERRY_BUSH);
        for (DyeColor color : DyeColor.values()) {
            BlockRenderLayerMap.put(RenderLayer.getCutout(), Registry.BLOCK.get(SoulIce.idFormatter(color.toString().toLowerCase() + "_plank_door")));
            BlockRenderLayerMap.put(RenderLayer.getCutout(), Registry.BLOCK.get(SoulIce.idFormatter(color.toString().toLowerCase() + "_plank_trapdoor")));
        }
    }
}
