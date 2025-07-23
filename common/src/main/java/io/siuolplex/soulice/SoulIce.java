package io.siuolplex.soulice;

import io.siuolplex.soulice.registry.*;
import io.siuolplex.untitledlib.util.Loader;
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
    public static Loader LOADER = new Loader() {};
    public static boolean IS_TEST_BUILD = LOADER.isDevMode() && false;

    /**
     For usage with Fabric, Quilt, and (1.21) Neo
     Serves to register during initialization directly.
     */
    public static void initWithRegistry(Loader loader) {
        LOADER = loader;

        SoulIceArmorMaterials.init();
        SoulIceBlocks.init();
        SoulIceItems.init();
        SoulIceEntityTypes.init();
        SoulIceFeatures.init();
    }
}
