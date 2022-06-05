package io.siuolplex.soul_ice.registry;

import io.siuolplex.soul_ice.entries.WoodRegistryEntrySet;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.DyeColor;

public class MTJSIEntries {
    public static final WoodRegistryEntrySet RED = quickSet(DyeColor.RED);
    public static final WoodRegistryEntrySet YELLOW = quickSet(DyeColor.YELLOW);
    public static final WoodRegistryEntrySet ORANGE = quickSet(DyeColor.ORANGE);
    public static final WoodRegistryEntrySet BLUE = quickSet(DyeColor.BLUE);
    public static final WoodRegistryEntrySet LIGHT_BLUE = quickSet(DyeColor.LIGHT_BLUE);
    public static final WoodRegistryEntrySet CYAN = quickSet(DyeColor.CYAN);
    public static final WoodRegistryEntrySet GREEN = quickSet(DyeColor.GREEN);
    public static final WoodRegistryEntrySet LIME = quickSet(DyeColor.LIME);
    public static final WoodRegistryEntrySet PURPLE = quickSet(DyeColor.PURPLE);
    public static final WoodRegistryEntrySet PINK = quickSet(DyeColor.PINK);
    public static final WoodRegistryEntrySet MAGENTA = quickSet(DyeColor.MAGENTA);
    public static final WoodRegistryEntrySet BROWN = quickSet(DyeColor.BROWN);
    public static final WoodRegistryEntrySet WHITE = quickSet(DyeColor.WHITE);
    public static final WoodRegistryEntrySet LIGHT_GRAY = quickSet(DyeColor.LIGHT_GRAY);
    public static final WoodRegistryEntrySet GRAY = quickSet(DyeColor.GRAY);
    public static final WoodRegistryEntrySet BLACK = quickSet(DyeColor.BLACK);

    private static WoodRegistryEntrySet quickSet(DyeColor color) {
        return new WoodRegistryEntrySet(color.toString(), false, Block.Settings.of(Material.WOOD, color).strength(2.0F, 3.0F).sounds(BlockSoundGroup.WOOD));
    }

    public static void init() {}
}
