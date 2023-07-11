package io.siuolplex.soulice;

import io.siuolplex.soulice.registry.SoulIceBlocks;
import io.siuolplex.soulice.registry.SoulIceEnchantments;
import io.siuolplex.soulice.registry.SoulIceEntityTypes;
import io.siuolplex.soulice.registry.SoulIceItems;
import net.minecraft.world.level.block.SculkSensorBlock;
import net.minecraft.world.level.block.entity.SculkSensorBlockEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.simple.SimpleLogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The goal of this mod is to obtain a HEALTHY
 * and SUSTAINABLE game, darling.
 *
 * @author Siuol Plex from Siuolmodding
 *
 */
public class SoulIce {
    public static Logger LOGGER = LoggerFactory.getLogger("Soul Ice");
    public static void init() {
        SoulIceBlocks.init();
        SoulIceItems.init();
        SoulIceEnchantments.init();
        SoulIceEntityTypes.init();
        // STUFF
    }
}
