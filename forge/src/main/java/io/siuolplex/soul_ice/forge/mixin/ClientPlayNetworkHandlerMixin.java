package io.siuolplex.soul_ice.forge.mixin;

import io.siuolplex.soul_ice.SoulIceConfig;
import io.siuolplex.soul_ice.SoulIceSlipSetter;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.packet.s2c.play.CustomPayloadS2CPacket;
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

    @Shadow @Final private static Logger LOGGER;

    //Lnet/minecraft/network/packet/s2c/play/CustomPayloadS2CPacket;getData()Lnet/minecraft/network/PacketByteBuf;
    // Mixin based on Polymer version, by Patbox https://github.com/Patbox/polymer/blob/dev/1.18.1/polymer/src/main/java/eu/pb4/polymer/mixin/client/ClientPlayNetworkHandlerMixin.java
    // Polymer is licensed under LGPLv3
    @Inject(method = "onCustomPayload", at = @At("HEAD"), cancellable = true)
    private void soulIce$catchPackets(CustomPayloadS2CPacket packet, CallbackInfo ci) {
        if (packet.getChannel().getNamespace().equals("soul_ice") || packet.getName().getNamespace().equals("soul_ice")) {
            try {
                float serverSlip = packet.getData().readNbt().getFloat("slipperiness");

                client.execute(() -> new SoulIceSlipSetter(serverSlip));
            } catch (NullPointerException npe) {
                client.execute(() -> {
                    new SoulIceSlipSetter(SoulIceConfig.instance().slipperiness);
                    LOGGER.warn("Soul Ice Sync Packet null, using client's configured slipperiness.");
                    LOGGER.warn("If this error appeared, and the server runs Soul Ice, please contact the server owner.");
                    LOGGER.warn("Otherwise, please send a bug report to https://github.com/Siuolthepic/Soul-Ice or talk to the mod's dev, Siuol.");
                });
            }
            ci.cancel();
        }
    }

    @Inject(method = "clearWorld", at = @At("HEAD"), cancellable = true)
    private void soulIce$onClearWorld(CallbackInfo ci){
        new SoulIceSlipSetter(SoulIceConfig.instance().slipperiness);
    }

}
