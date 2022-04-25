package io.siuolplex.soul_ice;

import net.minecraft.client.render.RenderLayer;
import net.minecraft.util.DyeColor;
import net.minecraft.util.registry.Registry;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.client.ClientModInitializer;
import org.quiltmc.qsl.block.extensions.api.client.BlockRenderLayerMap;

public class SoulIceClient implements ClientModInitializer {
    @Override
    public void onInitializeClient(ModContainer mod) {
        for (DyeColor color : DyeColor.values()) {
            BlockRenderLayerMap.put(RenderLayer.getCutout(), Registry.BLOCK.get(SoulIce.idFormatter(color.toString().toLowerCase() + "_plank_door")));
            BlockRenderLayerMap.put(RenderLayer.getCutout(), Registry.BLOCK.get(SoulIce.idFormatter(color.toString().toLowerCase() + "_plank_trapdoor")));
        }
    }
}
