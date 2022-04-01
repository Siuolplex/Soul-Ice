package io.siuolplex.soul_ice.forge.mixin;

import io.siuolplex.soul_ice.SoulIce;
import io.siuolplex.soul_ice.SoulIceConfig;
import io.siuolplex.soul_ice.forge.util.SoulIceEnchantSyncer;
import io.siuolplex.soul_ice.forge.util.SoulIceSlipSetter;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.packet.s2c.play.CustomPayloadS2CPacket;
import net.minecraft.text.Text;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayNetworkHandler.class)
public class ClientPlayNetworkHandlerMixin {
    @Shadow @Final private MinecraftClient client;

    //Lnet/minecraft/network/packet/s2c/play/CustomPayloadS2CPacket;getData()Lnet/minecraft/network/PacketByteBuf;
    // Mixin based on Polymer version, by Patbox https://github.com/Patbox/polymer/blob/dev/1.18.1/polymer/src/main/java/eu/pb4/polymer/mixin/client/ClientPlayNetworkHandlerMixin.java
    // Polymer is licensed under LGPLv3
    @Inject(method = "onCustomPayload", at = @At("HEAD"), cancellable = true)
    private void soulIce$catchPackets(CustomPayloadS2CPacket packet, CallbackInfo ci) {
        if (packet.getChannel().getNamespace().equals("soul_ice") || packet.getName().getNamespace().equals("soul_ice")) {
            try {
                packet.getData().retain();
                boolean isUnfalteringEnabled = packet.getData().readBoolean();
                float serverSlip = packet.getData().readFloat();
                client.execute(() -> {
                    SoulIceEnchantSyncer.setIsUnfalteringEnabled(isUnfalteringEnabled);
                    SoulIceSlipSetter.soulIceSlip(serverSlip);
                });
            }
            catch (NullPointerException npe) {
                client.execute(() -> {
                    SoulIceEnchantSyncer.setIsUnfalteringEnabled(SoulIceConfig.instance().enableUnfaltering);
                    SoulIceSlipSetter.soulIceSlip(SoulIceConfig.instance().slipperiness);
                    client.player.sendMessage(Text.of("Soul Ice Sync failed! Check log for more info. Please be cautious while playing"), false);
                    SoulIce.LOGGER.warn("Soul Ice Sync Packet null, using client's configured slipperiness.");
                    SoulIce.LOGGER.warn("If this error appeared, and the server runs Soul Ice, please contact the server owner.");
                    SoulIce.LOGGER.warn("Otherwise, please send a bug report to https://github.com/Siuolthepic/Soul-Ice or talk to the mod's dev, Siuol.");
                });
            }
            packet.getData().release();
            ci.cancel();
        }
    }

    @Inject(method = "clearWorld", at = @At("HEAD"), cancellable = true)
    private void soulIce$onClearWorld(CallbackInfo ci){
        SoulIceEnchantSyncer.setIsUnfalteringEnabled(SoulIceConfig.instance().enableUnfaltering);
        SoulIceSlipSetter.soulIceSlip(SoulIceConfig.instance().slipperiness);
    }

}
