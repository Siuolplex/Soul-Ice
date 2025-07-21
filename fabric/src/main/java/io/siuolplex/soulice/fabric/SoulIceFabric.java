package io.siuolplex.soulice.fabric;

import io.siuolplex.soulice.SoulIce;
import io.siuolplex.soulice.fabric.item.SoulIceItemGroup;
import io.siuolplex.soulice.registry.SoulIceItems;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.biome.v1.NetherBiomes;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.Climate;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;

public class SoulIceFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        SoulIce.initWithRegistry();
        SoulIceItemGroup.init();
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Decoration.UNDERGROUND_ORES, ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath("soul_ice", "lightstone")));
        BiomeModifications.addFeature(BiomeSelectors.tag(BiomeTags.HAS_DESERT_PYRAMID), GenerationStep.Decoration.VEGETAL_DECORATION, ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath("soul_ice", "rujone_berries")));
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(Biomes.PLAINS, Biomes.WINDSWEPT_HILLS, Biomes.GROVE, Biomes.FOREST, Biomes.FLOWER_FOREST), GenerationStep.Decoration.VEGETAL_DECORATION, ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath("soul_ice", "orange_rose")));
        NetherBiomes.addNetherBiome(ResourceKey.create(Registries.BIOME, ResourceLocation.fromNamespaceAndPath("soul_ice", "soul_husk")), Climate.parameters(-0.4f, -0.6f, 0f, 0f, 0f, 0f, 0f));

        /*LootTableEvents.MODIFY.register(((resourceManager, lootManager, id, tableBuilder, source) -> {
            if (source.isBuiltin() && ResourceLocation.fromNamespaceAndPath("minecraft", "entities/evoker").equals(id)) {
                LootPool.Builder poolBuilder = LootPool.lootPool().conditionally(LootItemRandomChanceCondition.randomChance(0.05f).build())
                        .with(LootItem.lootTableItem(SoulIceItems.EVOKATION_STAFF).build());

                tableBuilder.pool(poolBuilder.build());
            }
        }));*/
    }
}
