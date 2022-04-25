package io.siuolplex.soul_ice.mixin;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.DoorBlock;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.enums.DoubleBlockHalf;
import net.minecraft.block.enums.SlabType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.context.LootContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.List;

// Based on similar code by supersaiyansubtlety, made for Defaulted Drops, which is licensed under MIT, and found here: https://gitlab.com/supersaiyansubtlety-group/minecraft-mods/defaulted_drops/-/tree/master/
@Mixin(AbstractBlock.class)
public abstract class AbstractBlockMixin {
    @Shadow
    public abstract Item asItem();

    @Inject(method = "getDroppedStacks", cancellable = true, locals = LocalCapture.CAPTURE_FAILHARD, at = @At(value = "INVOKE", target = "Lnet/minecraft/loot/LootTable;generateLoot(Lnet/minecraft/loot/context/LootContext;)Ljava/util/List;"))
    private void addSelfIfNoLootTable(BlockState state, LootContext.Builder builder, CallbackInfoReturnable<List<ItemStack>> cir, Identifier id, LootContext context, ServerWorld world, LootTable lootTable) {
        if (lootTable.equals(LootTable.EMPTY) && id.getNamespace().equals("soul_ice")) {
            if (state.getBlock() instanceof SlabBlock && state.get(SlabBlock.TYPE).equals(SlabType.DOUBLE)) {
                cir.setReturnValue(List.of(new ItemStack(this.asItem()), new ItemStack(this.asItem())));
            } else if (state.getBlock() instanceof DoorBlock && state.get(DoorBlock.HALF).equals(DoubleBlockHalf.UPPER)) {
                cir.setReturnValue(List.of(Items.AIR.getDefaultStack()));
            } else cir.setReturnValue(List.of(new ItemStack(this.asItem())));
        }
    }
}

