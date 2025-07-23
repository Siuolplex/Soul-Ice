package io.siuolplex.soulice.fabric;

import io.siuolplex.soulice.SoulIce;
import io.siuolplex.soulice.fabric.item.SoulIceItemGroup;
import io.siuolplex.soulice.fabric.worldgen.BiomeSetup;
import io.siuolplex.soulice.registry.SoulIceItems;
import io.siuolplex.untitledlib.util.Loader;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;

import java.nio.file.Path;

public class SoulIceFabric implements ModInitializer {
    private static final Loader fabric = new Loader() {
        @Override
        public boolean isDevMode() {
            return FabricLoader.getInstance().isDevelopmentEnvironment();
        }

        @Override
        public boolean isClient() {
            return FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT;
        }

        @Override
        public String getLoader() {
            return "fabric";
        }

        // Chat what did I mean by this
        @Override
        public Path getPath(String string) {
            return FabricLoader.getInstance().getGameDir();
        }

        @Override
        public boolean isModPresent(String modID) {
            return FabricLoader.getInstance().isModLoaded(modID);
        }
    };



    @Override
    public void onInitialize() {
        SoulIce.initWithRegistry(fabric);
        SoulIceItemGroup.init();

        BiomeSetup.setupBiomes();

        LootTableEvents.MODIFY.register((key, builder, source, provider) -> {
            if (source.isBuiltin() && ResourceLocation.fromNamespaceAndPath("minecraft", "entities/evoker").equals(key.location())) {
                LootPool.Builder poolBuilder = LootPool.lootPool().conditionally(LootItemRandomChanceCondition.randomChance(0.05f).build())
                        .with(LootItem.lootTableItem(SoulIceItems.EVOKATION_STAFF).build());

                builder.pool(poolBuilder.build());
            }
        });
    }
}
