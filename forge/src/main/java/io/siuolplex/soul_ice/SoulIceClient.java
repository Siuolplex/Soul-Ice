package io.siuolplex.soul_ice;

import io.siuolplex.soul_ice.entries.forge.WoodRegistryEntrySetImpl;
import io.siuolplex.soul_ice.registry.SoulIceBlocks;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.RenderLayers;
import net.minecraft.client.render.entity.feature.ArmorFeatureRenderer;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.DyeColor;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

import java.util.Map;

import static io.siuolplex.soul_ice.SoulIce.idFormatter;

@Mod.EventBusSubscriber(modid = "soul_ice", bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class SoulIceClient {
    @SubscribeEvent
    public static void initClient(final FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            RenderLayers.setRenderLayer(SoulIceBlocks.ORANGE_ROSE, RenderLayer.getCutout());
            RenderLayers.setRenderLayer(SoulIceBlocks.RUJONE_BERRY_BUSH, RenderLayer.getCutout());
            for (Map.Entry<String, Item> entry : WoodRegistryEntrySetImpl.entrySetItemMap.entrySet()) {
                if (entry.getKey().contains("door") && entry.getValue() instanceof BlockItem blockItem) {
                    RenderLayers.setRenderLayer(blockItem.getBlock(), RenderLayer.getCutout());
                }
            }
        });
    }
}
