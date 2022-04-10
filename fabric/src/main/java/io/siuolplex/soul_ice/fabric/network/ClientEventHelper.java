package io.siuolplex.soul_ice.fabric.network;

import io.siuolplex.soul_ice.SoulIce;
import io.siuolplex.soul_ice.SoulIceConfig;
import io.siuolplex.soul_ice.fabric.util.SoulIceEnchantSyncer;
import io.siuolplex.soul_ice.fabric.util.SoulIceSlipSetter;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;

import static io.siuolplex.soul_ice.SoulIce.*;

public class ClientEventHelper {
    public static void registerClientPackets() {
        ClientPlayNetworking.registerGlobalReceiver(unfalteringSyncID, (client, handler, buf, responseSender) -> {
            try {
                boolean isUnfalteringEnabled = buf.readBoolean();
                client.execute(() -> SoulIceEnchantSyncer.setIsUnfalteringEnabled(isUnfalteringEnabled));
            } catch (NullPointerException npe) {client.execute(() -> handleNPE(client.player));}
        });

        ClientPlayNetworking.registerGlobalReceiver(freezingSyncID, (client, handler, buf, responseSender) -> {
            try {
                boolean isFreezingEnabled = buf.readBoolean();
                client.execute(() -> SoulIceEnchantSyncer.setIsFreezingEnabled(isFreezingEnabled));
            } catch (NullPointerException npe) {client.execute(() -> handleNPE(client.player));}
        });

        ClientPlayNetworking.registerGlobalReceiver(soulIceSyncID, (client, handler, buf, responseSender) -> {
            try {
                float serverSlip = buf.readFloat();
                client.execute(() -> SoulIceSlipSetter.soulIceSlip(serverSlip));
            } catch (NullPointerException npe) {client.execute(() -> handleNPE(client.player));}
        });

        ClientPlayConnectionEvents.DISCONNECT.register(((handler, client) -> {
            SoulIceEnchantSyncer.setIsUnfalteringEnabled(SoulIceConfig.instance().enableUnfaltering);
            SoulIceEnchantSyncer.setIsFreezingEnabled(SoulIceConfig.instance().enableFreezing);
            SoulIceSlipSetter.soulIceSlip(SoulIceConfig.instance().slipperiness);
        }));
    }

    private static void handleNPE(ClientPlayerEntity player) {
        SoulIceEnchantSyncer.setIsUnfalteringEnabled(SoulIceConfig.instance().enableUnfaltering);
        SoulIceEnchantSyncer.setIsFreezingEnabled(SoulIceConfig.instance().enableFreezing);
        SoulIceSlipSetter.soulIceSlip(SoulIceConfig.instance().slipperiness);
        player.sendChatMessage("Soul Ice Sync failed! Check log for more info. Please be cautious while playing");
        LOGGER.warn("Soul Ice Sync Packet null, continueing to play using client's configuration.");
        LOGGER.warn("If this error appeared, and the server runs Soul Ice, please contact the server owner.");
        LOGGER.warn("Otherwise, please send a bug report to https://github.com/Siuolthepic/Soul-Ice or talk to the mod's dev, Siuol.");
    }
}
