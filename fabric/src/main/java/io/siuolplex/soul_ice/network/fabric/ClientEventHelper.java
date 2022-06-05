package io.siuolplex.soul_ice.network.fabric;

import io.siuolplex.soul_ice.util.SoulIceConfig;
import io.siuolplex.soul_ice.util.ConfigSyncer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.network.ClientPlayerEntity;

import static io.siuolplex.soul_ice.SoulIce.*;

public class ClientEventHelper {
    public static void registerClientPackets() {
        ClientPlayNetworking.registerGlobalReceiver(unfalteringSyncID, (client, handler, buf, responseSender) -> {
            try {
                boolean isUnfalteringEnabled = buf.readBoolean();
                client.execute(() -> ConfigSyncer.setIsUnfalteringEnabled(isUnfalteringEnabled));
            } catch (NullPointerException npe) {client.execute(() -> handleNPE(client.player));}
        });

        ClientPlayNetworking.registerGlobalReceiver(freezingSyncID, (client, handler, buf, responseSender) -> {
            try {
                boolean isFreezingEnabled = buf.readBoolean();
                client.execute(() -> ConfigSyncer.setIsFreezingEnabled(isFreezingEnabled));
            } catch (NullPointerException npe) {client.execute(() -> handleNPE(client.player));}
        });

        ClientPlayNetworking.registerGlobalReceiver(soulIceSyncID, (client, handler, buf, responseSender) -> {
            try {
                float serverSlip = buf.readFloat();
                client.execute(() -> ConfigSyncer.soulIceSlip(serverSlip));
            } catch (NullPointerException npe) {client.execute(() -> handleNPE(client.player));}
        });

        ClientPlayConnectionEvents.DISCONNECT.register(((handler, client) -> {
            ConfigSyncer.setIsUnfalteringEnabled(SoulIceConfig.enableUnfaltering);
            ConfigSyncer.setIsFreezingEnabled(SoulIceConfig.enableFreezing);
            ConfigSyncer.soulIceSlip(SoulIceConfig.slipperiness);
        }));
    }

    private static void handleNPE(ClientPlayerEntity player) {
        ConfigSyncer.setIsUnfalteringEnabled(SoulIceConfig.enableUnfaltering);
        ConfigSyncer.setIsFreezingEnabled(SoulIceConfig.enableFreezing);
        ConfigSyncer.soulIceSlip(SoulIceConfig.slipperiness);
        player.sendChatMessage("Soul Ice Sync failed! Check log for more info. Please be cautious while playing");
        LOGGER.warn("Soul Ice Sync Packet null, continuing to play using client's configuration.");
        LOGGER.warn("If this error appeared, and the server runs Soul Ice, please contact the server owner.");
        LOGGER.warn("Otherwise, please send a bug report to https://github.com/Siuolthepic/Soul-Ice or talk to the mod's dev, Siuol.");
    }
}
