package io.siuolplex.soul_ice;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod("soul_ice")

public class SoulIce {
    public static String id = "soul_ice";

    final ModLoadingContext modLoadingContext = ModLoadingContext.get();
    final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

    public SoulIce() {
        SoulIceBlocks.BLOCKS.register(modEventBus);
        SoulIceItems.ITEMS.register(modEventBus);
    }
}
