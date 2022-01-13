package io.siuolplex.soul_ice;

import net.fabricmc.api.ModInitializer;

public class SoulIce implements ModInitializer {
    public static String id = "soul_ice";

    @Override
    public void onInitialize() {
        SoulIceBlocks.init();
        SoulIceItems.init();
    }
}
