package io.siuolplex.untitledlib.util;

import java.nio.file.Path;

/**
 * A set of small util stuff for Loaders.
 * <br> Might need to be expanded over time, who knows.
 *
 * <p> Defaults, under no condition, should ever get loaded. This is just so I can make it stop yelling at me easier. </p>
 */
public interface Loader {
    default boolean isDevMode() {
        return true;
    }

    default boolean isClient() {
        return false;
    }

    default String getLoader() {
        return "lmfao_what";
    }

    default Path getPath(String string) {
        return null;
    }

    default boolean isModPresent(String mod) {
        return false;
    }
}
