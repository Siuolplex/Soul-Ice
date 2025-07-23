package io.siuolplex.untitledlib.builder;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import org.jetbrains.annotations.ApiStatus;

import java.util.function.Function;

/**
 * UNFINISHED UNFINISHED UNFINISHED
 */
@ApiStatus.Experimental
public class BlockBuilder {
    static BlockBuilder test = new BlockBuilder(BlockBehaviour.Properties.of(), Block::new, ResourceLocation.fromNamespaceAndPath("soul_ice", "test"));
    private Function<BlockBehaviour.Properties, Block> blockBeforeMaking;
    private BlockBehaviour.Properties properties;
    private ResourceKey<Block> key;


    public BlockBuilder(BlockBehaviour.Properties properties, Function<BlockBehaviour.Properties, Block> blockBeforeMaking, ResourceLocation id) {
        this.blockBeforeMaking = blockBeforeMaking;
        this.properties = properties;
        this.key = ResourceKey.create(Registries.BLOCK, id);
    }

    public BlockBuilder createItem() {
        return this;
    }

    public BlockBuilder addModel() {
        return this;
    }

    public BlockBuilder createRecipe() {
        return this;
    }

    public BlockBuilder createLootTable() {
        return this;
    }

    public Block registerBlock() {
        return Registry.register(BuiltInRegistries.BLOCK, key, blockBeforeMaking.apply(properties));
    }
}
