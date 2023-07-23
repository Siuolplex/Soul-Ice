package io.siuolplex.soulice.util;

import java.nio.file.Path;

public interface Loader {
    boolean isDevMode();
    boolean isClient();
    String getLoader();
    Path getPath(String string);
    boolean isModPresent(String mod);

}
