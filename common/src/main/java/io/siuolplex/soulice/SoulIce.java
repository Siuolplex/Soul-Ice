package io.siuolplex.soulice;

import io.siuolplex.soulice.registry.*;
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

    /**
     Used by Forge and (1.20) Neo directly, will eventually set up loader stuff.
     */
    public static void init() {

    }

    /**
     For usage with Fabric, Quilt, and (1.21) Neo
     Serves to register during initialization directly.
     */
    public static void initWithRegistry() {
        SoulIceArmorMaterials.init();
        SoulIceBlocks.init();
        SoulIceItems.init();
        SoulIceEntityTypes.init();
        SoulIceFeatures.init();
        init();
    }
}
