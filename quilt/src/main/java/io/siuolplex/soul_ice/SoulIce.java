package io.siuolplex.soul_ice;


import io.siuolplex.soul_ice.network.quilt.ServerNetHelper;
import io.siuolplex.soul_ice.registry.*;
import io.siuolplex.soul_ice.registry.fabric.SoulIceWorldGen;
import io.siuolplex.soul_ice.util.Loader;
import io.siuolplex.soul_ice.util.MidnightConfig;
import io.siuolplex.soul_ice.util.SoulIceConfig;
import net.fabricmc.api.EnvType;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.loader.api.QuiltLoader;
import org.quiltmc.loader.api.minecraft.MinecraftQuiltLoader;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;
import org.quiltmc.qsl.item.group.api.QuiltItemGroup;

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
        Loader.setConstants("quilt", true, QuiltLoader.getConfigDir());
        if (MinecraftQuiltLoader.getEnvironmentType().equals(EnvType.CLIENT)) Loader.isClient = true;
        MidnightConfig.init("more_than_just_soul_ice", SoulIceConfig.class);
        SoulIceBlocks.init();
        SoulIceItems.init();
        MTJSIEntries.init();
        SoulIceEnchantments.init();
        SoulIceWorldGen.init();
        ServerNetHelper.readyThePackets();
    }
}
