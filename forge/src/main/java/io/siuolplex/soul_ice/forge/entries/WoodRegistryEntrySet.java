package io.siuolplex.soul_ice.forge.entries;

import io.siuolplex.soul_ice.SoulIce;
import io.siuolplex.soul_ice.block.SoulIceStairBlock;
import io.siuolplex.soul_ice.forge.registry.SoulIceBlocks;
import io.siuolplex.soul_ice.forge.registry.SoulIceItems;
import io.siuolplex.soul_ice.util.FalseBlock;
import io.siuolplex.soul_ice.util.SoulIceIDHandler;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.registries.IRegistryDelegate;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static io.siuolplex.soul_ice.forge.registry.SoulIceItems.sharedItemSettings;

// Based on https://github.com/ConsistencyPlus/ConsistencyPlus/blob/master/common/src/main/java/io/github/consistencyplus/consistency_plus/core/entries/block/RegistryEntryGroup.java, from Consistency+ which is licensed under LGPLv3.
public class WoodRegistryEntrySet {
    public final String name;
    public final boolean makeLogs;
    public FalseBlock settingsBlock;

    public WoodRegistryEntrySet(String name, boolean makeLogs, AbstractBlock.Settings blockSettings) {
        this.name = name;
        this.makeLogs = makeLogs;
        this.settingsBlock = new FalseBlock(blockSettings);
        for (PlankShapes shape : PlankShapes.values()) registerWithBlock(shape.withShape(this.name), blockRegistration(shape));
    }

    public RegistryObject<Block> blockRegistration(PlankShapes shape) {
        String name = shape.withShape(this.name);
        return switch (shape) {
            default -> SoulIce.BLOCKS.register(name, () -> new Block(sharedSettings()));
            case SLAB -> SoulIce.BLOCKS.register(name, () -> new SlabBlock(sharedSettings()));
            case STAIRS -> SoulIce.BLOCKS.register(name, () -> new SoulIceStairBlock(Blocks.OAK_PLANKS.getDefaultState(), sharedSettings()));
            case FENCE -> SoulIce.BLOCKS.register(name, () -> new FenceBlock(sharedSettings()));
            case FENCE_GATE -> SoulIce.BLOCKS.register(name, () -> new FenceGateBlock(sharedSettings()));
            case PRESSURE_PLATE -> SoulIce.BLOCKS.register(name, () -> new PressurePlateBlock(PressurePlateBlock.ActivationRule.EVERYTHING, sharedSettings()));
            case BUTTON -> SoulIce.BLOCKS.register(name, () -> new WoodenButtonBlock(sharedSettings()));
            case DOOR -> SoulIce.BLOCKS.register(name, () -> new DoorBlock(sharedSettings().nonOpaque()));
            case TRAPDOOR -> SoulIce.BLOCKS.register(name, () -> new TrapdoorBlock(sharedSettings().nonOpaque()));
        };
    }

    private static void registerWithBlock(String name, RegistryObject<Block> block) {
        SoulIce.ITEMS.register(name, () -> new BlockItem(block.get(), sharedItemSettings()));
    }

    public AbstractBlock.Settings sharedSettings() {
        return AbstractBlock.Settings.copy(settingsBlock);
    }

    public enum PlankShapes {
        BLOCK, SLAB, STAIRS, FENCE, FENCE_GATE, BUTTON, PRESSURE_PLATE, DOOR, TRAPDOOR;

        public String withShape(String name) {
            return name + ((this.equals(BLOCK)) ? "_planks" : "_plank_" + this);
        }

        @Override
        public String toString() {
            if (this.equals(BLOCK)) return "";
            return name().toLowerCase(Locale.ROOT);
        }
    }
}
