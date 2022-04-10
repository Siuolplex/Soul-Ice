package io.siuolplex.soul_ice.forge.mixin;

import io.siuolplex.soul_ice.SoulIce;
import io.siuolplex.soul_ice.SoulIceConfig;
import io.siuolplex.soul_ice.forge.util.SoulIceEnchantSyncer;
import io.siuolplex.soul_ice.forge.util.SoulIceSlipSetter;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.network.packet.s2c.play.CustomPayloadS2CPacket;
import net.minecraft.text.Text;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static io.siuolplex.soul_ice.SoulIce.LOGGER;

@Mixin(ClientPlayNetworkHandler.class)
public class ClientPlayNetworkHandlerMixin {
    @Shadow @Final private MinecraftClient client;

    //Lnet/minecraft/network/packet/s2c/play/CustomPayloadS2CPacket;getData()Lnet/minecraft/network/PacketByteBuf;
    // Mixin based on Polymer version, by Patbox https://github.com/Patbox/polymer/blob/dev/1.18.1/polymer/src/main/java/eu/pb4/polymer/mixin/client/ClientPlayNetworkHandlerMixin.java
    // Polymer is licensed under LGPLv3
    @Inject(method = "onCustomPayload", at = @At("HEAD"), cancellable = true)
    private void soulIce$catchPackets(CustomPayloadS2CPacket packet, CallbackInfo ci) {
        try {
            if (packet.getName().equals(SoulIce.soulIceSyncID)) {
                float serverSlip = packet.getData().readFloat();
                client.execute(() -> SoulIceSlipSetter.soulIceSlip(serverSlip));
            } else if (packet.getName().equals(SoulIce.unfalteringSyncID)) {
                boolean isUnfalteringEnabled = packet.getData().readBoolean();
                client.execute(() -> SoulIceEnchantSyncer.setIsUnfalteringEnabled(isUnfalteringEnabled));
            } else if (packet.getName().equals(SoulIce.freezingSyncID)) {
                boolean isFreezingEnabled = packet.getData().readBoolean();
                client.execute(() -> SoulIceEnchantSyncer.setIsFreezingEnabled(isFreezingEnabled));
            }
        } catch (NullPointerException npe) {
            client.execute(() -> handleNPE(client.player));
        }

        ci.cancel();
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

    @Inject(method = "clearWorld", at = @At("HEAD"), cancellable = true)
    private void soulIce$onClearWorld(CallbackInfo ci){
        SoulIceEnchantSyncer.setIsUnfalteringEnabled(SoulIceConfig.instance().enableUnfaltering);
        SoulIceEnchantSyncer.setIsFreezingEnabled(SoulIceConfig.instance().enableFreezing);
        SoulIceSlipSetter.soulIceSlip(SoulIceConfig.instance().slipperiness);
    }

}
