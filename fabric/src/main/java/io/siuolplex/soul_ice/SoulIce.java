package io.siuolplex.soul_ice;

import io.siuolplex.soul_ice.network.fabric.ServerNetHelper;
import io.siuolplex.soul_ice.registry.*;
import io.siuolplex.soul_ice.registry.fabric.SoulIceItemGroups;
import io.siuolplex.soul_ice.registry.fabric.SoulIceWorldGen;
import io.siuolplex.soul_ice.util.Loader;
import io.siuolplex.soul_ice.util.MidnightConfig;
import io.siuolplex.soul_ice.util.SoulIceConfig;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
    public void onInitialize() {
        Loader.setConstants("fabric", true, FabricLoader.getInstance().getConfigDir());
        if (FabricLoader.getInstance().getEnvironmentType().equals(EnvType.CLIENT)) Loader.isClient = true;
        MidnightConfig.init("more_than_just_soul_ice", SoulIceConfig.class);
        SoulIceBlocks.init();
        SoulIceItems.init();
        MTJSIEntries.init();
        SoulIceEnchantments.init();
        SoulIceWorldGen.init();
        ServerNetHelper.readyThePackets();

        if (FabricLoader.getInstance().isModLoaded("quilt_loader")){
            LOGGER.warn("Quilt Loader detected, Soul Ice has native Quilt versions, you know! Please download them from Curseforge/Modrinth");
        }
    }
}
