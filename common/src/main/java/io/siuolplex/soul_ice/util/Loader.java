package io.siuolplex.soul_ice.util;

import java.nio.file.Path;

/** For the purposes of allowing for additional loaders to be supported easily, this exists.
 I could have hardcoded this, but oh well
 */
public class Loader {
    private static String name;
    private static Boolean fabricBased;
    private static Path configDir;
    public static Boolean isClient = false;


    public static void setConstants(String name, boolean fabricBased, Path configDir) {
        Loader.name = name;
        Loader.fabricBased = fabricBased;
        Loader.configDir = configDir;
    }

    public static String getName() {
        return name;
    }

    public static boolean isFabricBased() {
        return fabricBased;
    }

    public static Path getConfigDir() {
        return configDir;
    }

    public static boolean getIsClient() {
        return isClient;
    }
}
