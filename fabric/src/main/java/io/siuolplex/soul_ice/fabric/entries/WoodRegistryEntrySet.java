package io.siuolplex.soul_ice.fabric.entries;

import io.siuolplex.soul_ice.block.SoulIceStairBlock;
import io.siuolplex.soul_ice.fabric.registry.SoulIceBlocks;
import io.siuolplex.soul_ice.fabric.registry.SoulIceItems;
import io.siuolplex.soul_ice.util.FalseBlock;
import io.siuolplex.soul_ice.util.SoulIceIDHandler;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.registry.Registry;

import java.util.Locale;

import static io.siuolplex.soul_ice.fabric.registry.SoulIceItems.sharedItemSettings;

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

    public Block blockRegistration(PlankShapes shape) {
        return switch (shape) {
            default -> new Block(sharedSettings());
            case SLAB -> new SlabBlock(sharedSettings());
            case STAIRS -> new SoulIceStairBlock(Blocks.OAK_PLANKS.getDefaultState(), sharedSettings());
            case FENCE -> new FenceBlock(sharedSettings());
            case FENCE_GATE -> new FenceGateBlock(sharedSettings());
            case PRESSURE_PLATE -> new PressurePlateBlock(PressurePlateBlock.ActivationRule.EVERYTHING, sharedSettings());
            case BUTTON -> new WoodenButtonBlock(sharedSettings());
            case DOOR -> new DoorBlock(sharedSettings().nonOpaque());
            case TRAPDOOR -> new TrapdoorBlock(sharedSettings().nonOpaque());
        };
    }

    private static Item registerWithBlock(String name, Block block) {
        Registry.register(Registry.BLOCK, SoulIceIDHandler.idFormatter(name), block);
        return Registry.register(Registry.ITEM, SoulIceIDHandler.idFormatter(name), new BlockItem(block, sharedItemSettings()));
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
