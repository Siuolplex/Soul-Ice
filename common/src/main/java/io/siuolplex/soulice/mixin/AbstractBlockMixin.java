package io.siuolplex.soulice.mixin;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.block.state.properties.SlabType;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.LootTable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.List;

// Based on similar code by supersaiyansubtlety, made for Defaulted Drops, which is licensed under MIT, and found here: https://gitlab.com/supersaiyansubtlety-group/minecraft-mods/defaulted_drops/-/tree/master/

@Mixin(BlockBehaviour.class)
public abstract class AbstractBlockMixin {
    @Inject(method = "getDrops", cancellable = true, locals = LocalCapture.CAPTURE_FAILHARD, at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/storage/loot/LootTable;getRandomItems(Lnet/minecraft/world/level/storage/loot/LootParams;)Lit/unimi/dsi/fastutil/objects/ObjectArrayList;"))
    private void soulIce$addSelfIfNoLootTable(BlockState blockState, LootParams.Builder builder, CallbackInfoReturnable<List<ItemStack>> cir, ResourceLocation resourceLocation, LootParams lootParams, ServerLevel serverLevel, LootTable lootTable) {
        if (lootTable.equals(LootTable.EMPTY) && resourceLocation.getNamespace().equals("soul_ice")) {
            if (blockState.getBlock() instanceof SlabBlock && blockState.getValue(SlabBlock.TYPE).equals(SlabType.DOUBLE)) {
                cir.setReturnValue(List.of(new ItemStack(blockState.getBlock().asItem()), new ItemStack(blockState.getBlock().asItem())));
            } else if (blockState.getBlock() instanceof DoorBlock && blockState.getValue(DoorBlock.HALF).equals(DoubleBlockHalf.UPPER)) {
                cir.setReturnValue(List.of(Items.AIR.getDefaultInstance()));
            } else cir.setReturnValue(List.of(new ItemStack(blockState.getBlock().asItem())));
        }
    }
}

