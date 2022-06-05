package io.siuolplex.soul_ice.entries;

import dev.architectury.injectables.annotations.ExpectPlatform;
import io.siuolplex.soul_ice.block.*;
import io.siuolplex.soul_ice.util.FalseBlock;
import io.siuolplex.soul_ice.util.SoulIceIDHandler;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.util.registry.Registry;

import java.util.Locale;

import static io.siuolplex.soul_ice.registry.SoulIceItems.sharedItemSettings;

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
            case PRESSURE_PLATE -> new SoulIcePressurePlateBlock(PressurePlateBlock.ActivationRule.EVERYTHING, sharedSettings().noCollision());
            case BUTTON -> new SoulIceWoodenButtonBlock(sharedSettings().noCollision());
            case DOOR -> new SoulIceDoorBlock(sharedSettings().nonOpaque());
            case TRAPDOOR -> new SoulIceTrapdoorBlock(sharedSettings().nonOpaque());
        };
    }

    @ExpectPlatform
    private static void registerWithBlock(String name, Block block) {
        throw new RuntimeException("Architectury failed!");
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
