package io.siuolplex.soul_ice;

import io.siuolplex.soul_ice.quilt.network.ClientEventHelper;
import io.siuolplex.soul_ice.quilt.network.ServerNetHelper;
import io.siuolplex.soul_ice.quilt.registry.SoulIceWorldGen;
import io.siuolplex.soul_ice.quilt.registry.SoulIceBlocks;
import io.siuolplex.soul_ice.quilt.registry.SoulIceEnchantments;
import io.siuolplex.soul_ice.quilt.registry.SoulIceItems;
import net.fabricmc.api.EnvType;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.loader.api.QuiltLoader;
import org.quiltmc.loader.api.minecraft.MinecraftQuiltLoader;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;

public class SoulIce implements ModInitializer {
    public static String id = "soul_ice";

    public static Identifier unfalteringSyncID = idFormatter("unfaltering_sync");
    public static Identifier freezingSyncID = idFormatter("freezing_sync");
    public static Identifier soulIceSyncID = idFormatter("soul_ice_sync");

    public static Identifier idFormatter(String string){
        return new Identifier(id, string);
    }

    public static Logger LOGGER = LogManager.getLogger("Soul Ice");

    @Override
    public void onInitialize(ModContainer mod) {
        SoulIceBlocks.init();
        SoulIceItems.init();
        SoulIceEnchantments.init();
        SoulIceWorldGen.init();

        if (MinecraftQuiltLoader.getEnvironmentType().equals(EnvType.CLIENT)) {
            ClientEventHelper.registerClientPackets();
        }

        ServerNetHelper.readyThePackets();
    }
}
