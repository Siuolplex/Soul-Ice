package io.siuolplex.soul_ice.fabric.network;

import io.siuolplex.soul_ice.SoulIceConfig;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.PacketByteBuf;

import static io.siuolplex.soul_ice.SoulIce.*;

public class ServerNetHelper {
    public static void readyThePackets() {
        PacketByteBuf unfalteringBuf = PacketByteBufs.create();
        PacketByteBuf freezingBuf = PacketByteBufs.create();
        PacketByteBuf slipperinessBuf = PacketByteBufs.create();

        unfalteringBuf.writeBoolean(SoulIceConfig.instance().enableUnfaltering);
        freezingBuf.writeBoolean(SoulIceConfig.instance().enableFreezing);
        slipperinessBuf.writeFloat(SoulIceConfig.instance().slipperiness);

        ServerPlayConnectionEvents.JOIN.register((handler, sender, server) -> {
            ServerPlayNetworking.send(handler.getPlayer(), unfalteringSyncID, unfalteringBuf);
            ServerPlayNetworking.send(handler.getPlayer(), freezingSyncID, freezingBuf);
            ServerPlayNetworking.send(handler.getPlayer(), soulIceSyncID, slipperinessBuf);
            LOGGER.info("Sent Data");
        });
    }
}
