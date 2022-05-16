package io.siuolplex.soul_ice;

import io.siuolplex.soul_ice.forge.registry.SoulIceBlocks;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.RenderLayers;
import net.minecraft.util.DyeColor;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

import static io.siuolplex.soul_ice.SoulIce.idFormatter;

@Mod.EventBusSubscriber(modid = "soul_ice", bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class SoulIceClient {
    @SubscribeEvent
    public static void initClient(final FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            RenderLayers.setRenderLayer(SoulIceBlocks.ORANGE_ROSE.get(), RenderLayer.getCutout());
            RenderLayers.setRenderLayer(SoulIceBlocks.RUJONE_BERRY_BUSH.get(), RenderLayer.getCutout());
            for (DyeColor color : DyeColor.values()) {
                RenderLayers.setRenderLayer(Registry.BLOCK.get(idFormatter(color.toString() + "_plank_door")), RenderLayer.getCutout());
                RenderLayers.setRenderLayer(Registry.BLOCK.get(idFormatter(color.toString() + "_plank_trapdoor")), RenderLayer.getCutout());
            }
        });
    }
}
