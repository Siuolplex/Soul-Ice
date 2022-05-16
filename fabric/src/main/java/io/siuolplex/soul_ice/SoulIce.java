package io.siuolplex.soul_ice;

import io.siuolplex.soul_ice.fabric.network.ClientEventHelper;
import io.siuolplex.soul_ice.fabric.network.ServerNetHelper;
import io.siuolplex.soul_ice.fabric.registry.SoulIceWorldGen;
import io.siuolplex.soul_ice.fabric.util.SoulIceEnchantSyncer;
import io.siuolplex.soul_ice.fabric.util.SoulIceSlipSetter;
import io.siuolplex.soul_ice.fabric.registry.SoulIceBlocks;
import io.siuolplex.soul_ice.fabric.registry.SoulIceEnchantments;
import io.siuolplex.soul_ice.fabric.registry.SoulIceItems;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
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
        SoulIceBlocks.init();
        SoulIceItems.init();
        SoulIceEnchantments.init();
        SoulIceWorldGen.init();
        ServerNetHelper.readyThePackets();

        if (FabricLoader.getInstance().isModLoaded("quilt_loader")){
            LOGGER.warn("Quilt Loader detected, Soul Ice has native Quilt versions, you know! Please download them from Curseforge/Modrinth");
        }
    }
}
