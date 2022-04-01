package io.siuolplex.soul_ice;

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
        if (FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT) {
            ClientPlayNetworking.registerGlobalReceiver(soulIceSyncID, (client, handler, buf, responseSender) -> {
                try {
                    boolean isUnfalteringEnabled = buf.readBoolean();
                    float serverSlip = buf.readFloat();
                    client.execute(() -> {
                        SoulIceEnchantSyncer.setIsUnfalteringEnabled(isUnfalteringEnabled);
                        SoulIceSlipSetter.soulIceSlip(serverSlip);
                    });
                } catch (NullPointerException npe) {
                    try {
                        client.execute(() -> {
                            SoulIceEnchantSyncer.setIsUnfalteringEnabled(SoulIceConfig.instance().enableUnfaltering);
                            SoulIceSlipSetter.soulIceSlip(SoulIceConfig.instance().slipperiness);
                            client.player.sendChatMessage("Soul Ice Sync failed! Check log for more info. Please be cautious while playing");
                            LOGGER.warn("Soul Ice Sync Packet null, using client's configuration.");
                            LOGGER.warn("If this error appeared, and the server runs Soul Ice, please contact the server owner.");
                            LOGGER.warn("Otherwise, please send a bug report to https://github.com/Siuolthepic/Soul-Ice or talk to the mod's dev, Siuol.");
                        });
                    } catch (NullPointerException npe2) {
                        client.execute(() -> {
                            LOGGER.warn("Well uh, this is pretty awkward.");
                            LOGGER.warn("Somehow the Null Pointer Exception had a Null Pointer Exception");
                            LOGGER.warn("Well done. You have genuinely scared me.");
                            LOGGER.warn("Under this situation, please show this to Siuol and report this to https://github.com/Siuolthepic/Soul-Ice. Genuinely how did you manage to make this happen I am so confused.");
                        });
                    }
                }
            });

            ClientPlayConnectionEvents.DISCONNECT.register(((handler, client) -> {
                SoulIceEnchantSyncer.setIsUnfalteringEnabled(SoulIceConfig.instance().enableUnfaltering);
                SoulIceSlipSetter.soulIceSlip(SoulIceConfig.instance().slipperiness);
            }));
        }

        PacketByteBuf buf = PacketByteBufs.create();
        buf.writeBoolean(SoulIceConfig.instance().enableUnfaltering);
        buf.writeFloat(SoulIceConfig.instance().slipperiness);

        /*NbtCompound slipNBT = new NbtCompound();
        slipNBT.putFloat("slipperiness", SoulIceConfig.instance().slipperiness);
         */
        ServerPlayConnectionEvents.JOIN.register((handler, sender, server) -> {
            ServerPlayNetworking.send(handler.getPlayer(), soulIceSyncID, buf);
            LOGGER.info("Sent Data");
        });
    }
}
