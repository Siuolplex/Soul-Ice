package io.siuolplex.soulice.fabric;

import io.siuolplex.soulice.fabric.worldgen.BiomeSetup;
import io.siuolplex.soulice.registry.SoulIceBiomes;
import io.siuolplex.soulice.registry.SoulIceBlocks;
import io.siuolplex.soulice.registry.SoulIceItems;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.client.gui.screens.CreateBuffetWorldScreen;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.models.BlockModelGenerators;
import net.minecraft.data.models.ItemModelGenerators;
import net.minecraft.data.models.model.ModelTemplates;
import net.minecraft.data.models.model.TextureMapping;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.properties.SlabType;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.concurrent.CompletableFuture;

public class SoulIceDatagen implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator generator) {
        FabricDataGenerator.Pack pack = generator.createPack();
        //pack.addProvider(SIAdvancementProvider::new);
        pack.addProvider(SILootTableProvider::new);
        pack.addProvider(SIRecipeProvider::new);
        //pack.addProvider(WYDItemTagProvider::new);
        //pack.addProvider(WYDBlockTagProvider::new);
        //pack.addProvider(SIModelProvider::new);

    }

    private static class SIModelProvider extends FabricModelProvider {
        public SIModelProvider(FabricDataOutput output) {
            super(output);

        }

        @Override
        public void generateBlockStateModels(BlockModelGenerators blockModelGenerators) {

        }

        @Override
        public void generateItemModels(ItemModelGenerators itemModelGenerators) {

        }
    }

    private static class SILootTableProvider extends FabricBlockLootTableProvider {
        protected SILootTableProvider(FabricDataOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
            super(dataOutput, registryLookup);
        }

        @Override
        public void generate() {
            HolderLookup.RegistryLookup<Enchantment> registryLookup = registries.lookupOrThrow(Registries.ENCHANTMENT);
            add(SoulIceBlocks.SOUL_ICE, createSingleItemTableWithSilkTouch(SoulIceBlocks.SOUL_ICE, Items.AIR));
            add(SoulIceBlocks.SOUL_ICE_SLAB, createSlabWithSilkTouch(SoulIceBlocks.SOUL_ICE_SLAB));
            add(SoulIceBlocks.SOUL_ICE_STAIRS, createSingleItemTableWithSilkTouch(SoulIceBlocks.SOUL_ICE_STAIRS, Items.AIR));
            add(SoulIceBlocks.SOUL_ICE_WALL, createSingleItemTableWithSilkTouch(SoulIceBlocks.SOUL_ICE_WALL, Items.AIR));
            add(SoulIceBlocks.SOUL_ICE_GATE, createSingleItemTableWithSilkTouch(SoulIceBlocks.SOUL_ICE_GATE, Items.AIR));

            add(SoulIceBlocks.POLISHED_SOUL_ICE, createSingleItemTableWithSilkTouch(SoulIceBlocks.POLISHED_SOUL_ICE, Items.AIR));
            add(SoulIceBlocks.POLISHED_SOUL_ICE_SLAB, createSlabWithSilkTouch(SoulIceBlocks.POLISHED_SOUL_ICE_SLAB));
            add(SoulIceBlocks.POLISHED_SOUL_ICE_STAIRS, createSingleItemTableWithSilkTouch(SoulIceBlocks.POLISHED_SOUL_ICE_STAIRS, Items.AIR));
            add(SoulIceBlocks.POLISHED_SOUL_ICE_WALL, createSingleItemTableWithSilkTouch(SoulIceBlocks.POLISHED_SOUL_ICE_WALL, Items.AIR));
            add(SoulIceBlocks.POLISHED_SOUL_ICE_GATE, createSingleItemTableWithSilkTouch(SoulIceBlocks.POLISHED_SOUL_ICE_GATE, Items.AIR));

            add(SoulIceBlocks.SOUL_ICE_BRICKS, createSingleItemTableWithSilkTouch(SoulIceBlocks.SOUL_ICE, Items.AIR));
            add(SoulIceBlocks.SOUL_ICE_BRICK_SLAB, createSlabWithSilkTouch(SoulIceBlocks.SOUL_ICE_BRICK_SLAB));
            add(SoulIceBlocks.SOUL_ICE_BRICK_STAIRS, createSingleItemTableWithSilkTouch(SoulIceBlocks.SOUL_ICE_STAIRS, Items.AIR));
            add(SoulIceBlocks.SOUL_ICE_BRICK_WALL, createSingleItemTableWithSilkTouch(SoulIceBlocks.SOUL_ICE_WALL, SoulIceBlocks.SOUL_ICE_WALL));
            add(SoulIceBlocks.SOUL_ICE_BRICK_GATE, createSingleItemTableWithSilkTouch(SoulIceBlocks.SOUL_ICE_GATE, SoulIceBlocks.SOUL_ICE_GATE));

            add(SoulIceBlocks.LIGHTSTONE, createSingleItemTable(SoulIceBlocks.LIGHTSTONE));
            add(SoulIceBlocks.LIGHTSTONE_SLAB, createSlabItemTable(SoulIceBlocks.LIGHTSTONE_SLAB));
            add(SoulIceBlocks.LIGHTSTONE_STAIRS, createSingleItemTable(SoulIceBlocks.LIGHTSTONE_STAIRS));
            add(SoulIceBlocks.LIGHTSTONE_WALL, createSingleItemTable(SoulIceBlocks.LIGHTSTONE_WALL));

            add(SoulIceBlocks.POLISHED_LIGHTSTONE, createSingleItemTable(SoulIceBlocks.POLISHED_LIGHTSTONE));
            add(SoulIceBlocks.POLISHED_LIGHTSTONE_SLAB, createSlabItemTable(SoulIceBlocks.POLISHED_LIGHTSTONE_SLAB));
            add(SoulIceBlocks.POLISHED_LIGHTSTONE_STAIRS, createSingleItemTable(SoulIceBlocks.POLISHED_LIGHTSTONE_STAIRS));
            add(SoulIceBlocks.POLISHED_LIGHTSTONE_WALL, createSingleItemTable(SoulIceBlocks.POLISHED_LIGHTSTONE_WALL));

            add(SoulIceBlocks.LIGHTSTONE_BRICKS, createSingleItemTable(SoulIceBlocks.LIGHTSTONE_BRICKS));
            add(SoulIceBlocks.LIGHTSTONE_BRICK_SLAB, createSlabItemTable(SoulIceBlocks.LIGHTSTONE_BRICK_SLAB));
            add(SoulIceBlocks.LIGHTSTONE_BRICK_STAIRS, createSingleItemTable(SoulIceBlocks.LIGHTSTONE_BRICK_STAIRS));
            add(SoulIceBlocks.LIGHTSTONE_BRICK_WALL, createSingleItemTable(SoulIceBlocks.LIGHTSTONE_BRICK_WALL));

            add(SoulIceBlocks.HARDENED_LIGHTSTONE, createSingleItemTable(SoulIceBlocks.HARDENED_LIGHTSTONE));
            add(SoulIceBlocks.HARDENED_LIGHTSTONE_SLAB, createSlabItemTable(SoulIceBlocks.HARDENED_LIGHTSTONE_SLAB));
            add(SoulIceBlocks.HARDENED_LIGHTSTONE_STAIRS, createSingleItemTable(SoulIceBlocks.HARDENED_LIGHTSTONE_STAIRS));
            add(SoulIceBlocks.HARDENED_LIGHTSTONE_WALL, createSingleItemTable(SoulIceBlocks.HARDENED_LIGHTSTONE_WALL));

            add(SoulIceBlocks.IGNIDIA_ICE, createSingleItemTable(SoulIceBlocks.IGNIDIA_ICE));
            add(SoulIceBlocks.MULVITE, createSingleItemTable(SoulIceBlocks.MULVITE));

            this.add(
                    SoulIceBlocks.RUJONE_BERRY_BUSH,
                    block -> this.applyExplosionDecay(
                            block,
                            LootTable.lootTable()
                                    .withPool(
                                            LootPool.lootPool()
                                                    .when(
                                                            LootItemBlockStatePropertyCondition.hasBlockStateProperties(SoulIceBlocks.RUJONE_BERRY_BUSH)
                                                                    .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(SweetBerryBushBlock.AGE, 3))
                                                    )
                                                    .add(LootItem.lootTableItem(SoulIceItems.RUJONE_BERRIES))
                                                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 3.0F)))
                                                    .apply(ApplyBonusCount.addUniformBonusCount(registryLookup.getOrThrow(Enchantments.FORTUNE)))
                                    )
                                    .withPool(
                                            LootPool.lootPool()
                                                    .when(
                                                            LootItemBlockStatePropertyCondition.hasBlockStateProperties(SoulIceBlocks.RUJONE_BERRY_BUSH)
                                                                    .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(SweetBerryBushBlock.AGE, 2))
                                                    )
                                                    .add(LootItem.lootTableItem(SoulIceItems.RUJONE_BERRIES))
                                                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))
                                                    .apply(ApplyBonusCount.addUniformBonusCount(registryLookup.getOrThrow(Enchantments.FORTUNE)))
                                    )
                    )
            );

            this.dropSelf(SoulIceBlocks.ORANGE_ROSE);
        }

        public LootTable.Builder createSlabWithSilkTouch(Block block) {
            return LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                                    .add(this.applyExplosionDecay(block, LootItem.lootTableItem(block)
                                                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(2.0F))
                                                                            .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(SlabBlock.TYPE, SlabType.DOUBLE)))
                                                            )
                                            )
                                    ).when(this.hasSilkTouch())
                    );
        }
    }

    public static class SIRecipeProvider extends RecipeProvider {
        public SIRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
            super(output, registries);
        }

        @Override
        public void buildRecipes(RecipeOutput recipeOutput) {
            /*ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, SoulIceItems.SOUL_ICE)
                    .define('I', Items.BLUE_ICE)
                    .define('S', ItemTags.SOUL_FIRE_BASE_BLOCKS)
                    .pattern("ISI")
                    .pattern("SIS")
                    .pattern("ISI")
                    .save(recipeOutput);*/
            ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, SoulIceItems.SOUL_ICE)
                    .define('I', Items.BLUE_ICE)
                    .define('S', ItemTags.SOUL_FIRE_BASE_BLOCKS)
                    .pattern("III")
                    .pattern("ISI")
                    .pattern("III")
                    .unlockedBy("player_get_the_item", has(ItemTags.SOUL_FIRE_BASE_BLOCKS)) //Item
                    .save(recipeOutput);

            RecipeProvider.slab(recipeOutput, RecipeCategory.BUILDING_BLOCKS, SoulIceItems.SOUL_ICE_SLAB, SoulIceItems.SOUL_ICE);
            RecipeProvider.stairBuilder(SoulIceItems.SOUL_ICE_STAIRS, Ingredient.of(SoulIceItems.SOUL_ICE)).unlockedBy("player_get_the_item", has(SoulIceItems.SOUL_ICE)).save(recipeOutput);
            RecipeProvider.wall(recipeOutput, RecipeCategory.BUILDING_BLOCKS, SoulIceItems.SOUL_ICE_WALL, SoulIceItems.SOUL_ICE);
            gate(recipeOutput, RecipeCategory.BUILDING_BLOCKS, SoulIceItems.SOUL_ICE_GATE, SoulIceBlocks.SOUL_ICE, SoulIceBlocks.SOUL_ICE_WALL);

            quadCraft(recipeOutput, RecipeCategory.BUILDING_BLOCKS, SoulIceItems.POLISHED_SOUL_ICE, SoulIceItems.SOUL_ICE);
            RecipeProvider.slab(recipeOutput, RecipeCategory.BUILDING_BLOCKS, SoulIceItems.POLISHED_SOUL_ICE_SLAB, SoulIceItems.POLISHED_SOUL_ICE);
            RecipeProvider.stairBuilder(SoulIceItems.POLISHED_SOUL_ICE_STAIRS, Ingredient.of(SoulIceItems.POLISHED_SOUL_ICE)).unlockedBy("player_get_the_item", has(SoulIceItems.POLISHED_SOUL_ICE)).save(recipeOutput);
            RecipeProvider.wall(recipeOutput, RecipeCategory.BUILDING_BLOCKS, SoulIceItems.POLISHED_SOUL_ICE_WALL, SoulIceItems.POLISHED_SOUL_ICE);
            gate(recipeOutput, RecipeCategory.BUILDING_BLOCKS, SoulIceItems.POLISHED_SOUL_ICE_GATE, SoulIceBlocks.POLISHED_SOUL_ICE, SoulIceBlocks.POLISHED_SOUL_ICE_WALL);

            quadCraft(recipeOutput, RecipeCategory.BUILDING_BLOCKS, SoulIceItems.SOUL_ICE_BRICKS, SoulIceItems.POLISHED_SOUL_ICE);
            RecipeProvider.slab(recipeOutput, RecipeCategory.BUILDING_BLOCKS, SoulIceItems.SOUL_ICE_BRICK_SLAB, SoulIceItems.SOUL_ICE_BRICKS);
            RecipeProvider.stairBuilder(SoulIceItems.SOUL_ICE_BRICK_STAIRS, Ingredient.of(SoulIceItems.SOUL_ICE_BRICKS)).unlockedBy("player_get_the_item", has(SoulIceItems.SOUL_ICE_BRICKS)).save(recipeOutput);
            RecipeProvider.wall(recipeOutput, RecipeCategory.BUILDING_BLOCKS, SoulIceItems.SOUL_ICE_BRICK_WALL, SoulIceItems.SOUL_ICE_BRICKS);
            gate(recipeOutput, RecipeCategory.BUILDING_BLOCKS, SoulIceItems.SOUL_ICE_BRICK_GATE, SoulIceBlocks.SOUL_ICE_BRICKS, SoulIceBlocks.SOUL_ICE_BRICK_WALL);


            RecipeProvider.slab(recipeOutput, RecipeCategory.BUILDING_BLOCKS, SoulIceItems.LIGHTSTONE_SLAB, SoulIceItems.LIGHTSTONE);
            RecipeProvider.stairBuilder(SoulIceItems.LIGHTSTONE_STAIRS, Ingredient.of(SoulIceItems.LIGHTSTONE)).unlockedBy("player_get_the_item", has(SoulIceItems.LIGHTSTONE)).save(recipeOutput);
            RecipeProvider.wall(recipeOutput, RecipeCategory.BUILDING_BLOCKS, SoulIceItems.LIGHTSTONE_WALL, SoulIceItems.LIGHTSTONE);

            quadCraft(recipeOutput, RecipeCategory.BUILDING_BLOCKS, SoulIceItems.POLISHED_LIGHTSTONE, SoulIceItems.LIGHTSTONE);
            RecipeProvider.slab(recipeOutput, RecipeCategory.BUILDING_BLOCKS, SoulIceItems.POLISHED_LIGHTSTONE_SLAB, SoulIceItems.POLISHED_LIGHTSTONE);
            RecipeProvider.stairBuilder(SoulIceItems.POLISHED_LIGHTSTONE_STAIRS, Ingredient.of(SoulIceItems.POLISHED_LIGHTSTONE)).unlockedBy("player_get_the_item", has(SoulIceItems.POLISHED_LIGHTSTONE)).save(recipeOutput);
            RecipeProvider.wall(recipeOutput, RecipeCategory.BUILDING_BLOCKS, SoulIceItems.POLISHED_LIGHTSTONE_WALL, SoulIceItems.POLISHED_LIGHTSTONE);

            quadCraft(recipeOutput, RecipeCategory.BUILDING_BLOCKS, SoulIceItems.LIGHTSTONE_BRICKS, SoulIceItems.POLISHED_LIGHTSTONE);
            RecipeProvider.slab(recipeOutput, RecipeCategory.BUILDING_BLOCKS, SoulIceItems.LIGHTSTONE_BRICK_SLAB, SoulIceItems.LIGHTSTONE_BRICKS);
            RecipeProvider.stairBuilder(SoulIceItems.LIGHTSTONE_BRICK_STAIRS, Ingredient.of(SoulIceItems.LIGHTSTONE_BRICKS)).unlockedBy("player_get_the_item", has(SoulIceItems.LIGHTSTONE_BRICKS)).save(recipeOutput);
            RecipeProvider.wall(recipeOutput, RecipeCategory.BUILDING_BLOCKS, SoulIceItems.LIGHTSTONE_BRICK_WALL, SoulIceItems.LIGHTSTONE_BRICKS);

            RecipeProvider.smeltingResultFromBase(recipeOutput, SoulIceItems.HARDENED_LIGHTSTONE, SoulIceItems.LIGHTSTONE);
            RecipeProvider.slab(recipeOutput, RecipeCategory.BUILDING_BLOCKS, SoulIceItems.HARDENED_LIGHTSTONE_SLAB, SoulIceItems.HARDENED_LIGHTSTONE);
            RecipeProvider.stairBuilder(SoulIceItems.HARDENED_LIGHTSTONE_STAIRS, Ingredient.of(SoulIceItems.HARDENED_LIGHTSTONE)).unlockedBy("player_get_the_item", has(SoulIceItems.HARDENED_LIGHTSTONE)).save(recipeOutput);
            RecipeProvider.wall(recipeOutput, RecipeCategory.BUILDING_BLOCKS, SoulIceItems.HARDENED_LIGHTSTONE_WALL, SoulIceItems.HARDENED_LIGHTSTONE);


            createArmor(recipeOutput, RecipeCategory.COMBAT, SoulIceItems.CACTUS_HELMET, SoulIceItems.CACTUS_CHESTPLATE, SoulIceItems.CACTUS_LEGGINGS, SoulIceItems.CACTUS_BOOTS, Items.CACTUS);

            ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, SoulIceItems.GLUTEN_BALL, 8).requires(Items.WHEAT, 4).unlockedBy("player_get_the_item", has(Items.WHEAT));
            RecipeProvider.smeltingResultFromBase(recipeOutput, SoulIceItems.BAKED_GLUTEN_BALL, SoulIceItems.GLUTEN_BALL);
        }

        public static void quadCraft(RecipeOutput recipeOutput, RecipeCategory category, ItemLike result, ItemLike original) {
            ShapedRecipeBuilder.shaped(category, result, 4)
                    .define('O', original)
                    .pattern("OO")
                    .pattern("OO")
                    .unlockedBy("player_get_the_item", has(original))
                    .save(recipeOutput);
        }

        public static void gate(RecipeOutput recipeOutput, RecipeCategory category, ItemLike gate, ItemLike mainPart, ItemLike secondaryPart) {
            ShapedRecipeBuilder.shaped(category, gate, 4)
                    .define('S', secondaryPart)
                    .define('M', mainPart)
                    .pattern("SMS")
                    .pattern("SMS")
                    .unlockedBy("player_get_the_item", has(mainPart))
                    .save(recipeOutput);
        }

        public static void createArmor(RecipeOutput recipeOutput, RecipeCategory category, ItemLike helmet, ItemLike chestplate, ItemLike leggings, ItemLike boots, ItemLike material) {
            ShapedRecipeBuilder.shaped(category, helmet)
                    .define('M', material)
                    .pattern("MMM")
                    .pattern("M M")
                    .unlockedBy("player_get_the_item", has(material))
                    .save(recipeOutput);

            ShapedRecipeBuilder.shaped(category, chestplate)
                    .define('M', material)
                    .pattern("M M")
                    .pattern("MMM")
                    .pattern("MMM")
                    .unlockedBy("player_get_the_item", has(material))
                    .save(recipeOutput);

            ShapedRecipeBuilder.shaped(category, leggings)
                    .define('M', material)
                    .pattern("MMM")
                    .pattern("M M")
                    .pattern("M M")
                    .unlockedBy("player_get_the_item", has(material))
                    .save(recipeOutput);

            ShapedRecipeBuilder.shaped(category, boots)
                    .define('M', material)
                    .pattern("M M")
                    .pattern("M M")
                    .unlockedBy("player_get_the_item", has(material))
                    .save(recipeOutput);
        }

        @Override
        public String getName() {
            return "SoulIceRecipes";
        }


    }

    public void buildRegistry(RegistrySetBuilder registryBuilder) {
        registryBuilder.add(Registries.BIOME, BiomeSetup::biomeBootstrap);
    }
}
