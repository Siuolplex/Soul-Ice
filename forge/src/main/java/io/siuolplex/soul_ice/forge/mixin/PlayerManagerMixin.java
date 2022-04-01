package io.siuolplex.soul_ice.forge.mixin;

import io.netty.buffer.Unpooled;
import io.siuolplex.soul_ice.SoulIce;
import io.siuolplex.soul_ice.SoulIceConfig;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.ClientConnection;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.packet.s2c.play.CustomPayloadS2CPacket;
import net.minecraft.server.PlayerManager;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerManager.class)
public class PlayerManagerMixin {
    @Inject(method = "Lnet/minecraft/server/PlayerManager;onPlayerConnect(Lnet/minecraft/network/ClientConnection;Lnet/minecraft/server/network/ServerPlayerEntity;)V", at = @At(value = "TAIL"))
    private void soulIceSync(ClientConnection clientConnection, ServerPlayerEntity serverPlayer, CallbackInfo ci) {
        PacketByteBuf buf = new PacketByteBuf(Unpooled.buffer());
        buf.writeBoolean(SoulIceConfig.instance().enableUnfaltering);
        buf.writeFloat(SoulIceConfig.instance().slipperiness);
        serverPlayer.networkHandler.sendPacket(new CustomPayloadS2CPacket(SoulIce.soulIceSyncID, buf));
    }
}
