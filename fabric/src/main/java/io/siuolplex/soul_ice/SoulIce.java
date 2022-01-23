package io.siuolplex.soul_ice;

import io.siuolplex.soul_ice.blocks.SoulIceSlipSetter;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTablesProvider;
import net.fabricmc.fabric.api.event.player.UseItemCallback;
import net.fabricmc.fabric.api.loot.v1.FabricLootPoolBuilder;
import net.fabricmc.fabric.api.loot.v1.event.LootTableLoadingCallback;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.fabricmc.fabric.api.tag.TagFactory;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.LootNumberProvider;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.tag.BlockTags;
import net.minecraft.tag.Tag;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.registry.Registry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class SoulIce implements ModInitializer {
    public static String id = "soul_ice";

    public static Identifier slipFixId = idFormatter("slipperiness_fix");

    public static Identifier idFormatter(String string){
        return new Identifier(id, string);
    }
    
    public static Logger LOGGER = LogManager.getLogger("Soul Ice");


    @Override
    public void onInitialize() {

        SoulIceBlocks.init();
        SoulIceItems.init();
        if (FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT) {
            ClientPlayNetworking.registerGlobalReceiver(slipFixId, (client, handler, buf, responseSender) -> {
                try {
                    float serverSlip = buf.readNbt().getFloat("slipperiness");
                    client.execute(() -> new SoulIceSlipSetter(serverSlip));
                } catch (NullPointerException npe) {
                    client.execute(() -> {
                        new SoulIceSlipSetter(SoulIceConfig.instance().slipperiness);
                        LOGGER.warn("Soul Ice Sync Packet null, using client's configured slipperiness.");
                        LOGGER.warn("If this error appeared, and the server runs Soul Ice, please contact the server owner.");
                        LOGGER.warn("Otherwise, please send a bug report to https://github.com/Siuolthepic/Soul-Ice or talk to the mod's dev, Siuol.");
                    });
                }
            });
            ClientPlayConnectionEvents.DISCONNECT.register(((handler, client) -> new SoulIceSlipSetter(SoulIceConfig.instance().slipperiness)));
        }

        if (FabricLoader.getInstance().getEnvironmentType() == EnvType.SERVER) {
            PacketByteBuf buf = PacketByteBufs.create();
            NbtCompound slipNBT = new NbtCompound();
            slipNBT.putFloat("slipperiness", SoulIceConfig.instance().slipperiness);
            buf.writeNbt(slipNBT);
            ServerPlayConnectionEvents.JOIN.register((handler, sender, server) -> ServerPlayNetworking.send(handler.getPlayer(), slipFixId, buf));
        }
    }
}
