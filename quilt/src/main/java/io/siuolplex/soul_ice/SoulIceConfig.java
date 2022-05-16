package io.siuolplex.soul_ice;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.loader.api.FabricLoader;
import org.quiltmc.loader.api.QuiltLoader;

import java.io.*;

public class SoulIceConfig {
    // Config code based on code from NotEnoughCrashes by natanfudge, found here: https://github.com/natanfudge/Not-Enough-Crashes/blob/1.18/common/src/main/java/fudge/notenoughcrashes/NecConfig.java
    public float slipperiness = 1.025f;
    public boolean enableUnfaltering = true;
    public boolean enableFreezing = true;
    public boolean enableLightstoneGeneration = true;
    public boolean enableRujoneBerryGeneration = true;
    public boolean enableOrangeRoseGeneration = true;


    public static SoulIceConfig instance() {
        if (instance != null) {
            return instance;
        }

        if (CONFIG_FILE.exists()) {
            try {
                return instance = new Gson().fromJson(new FileReader(CONFIG_FILE), SoulIceConfig.class);
            } catch (FileNotFoundException e) {
                throw new IllegalStateException(e);
            }
        }

        instance = new SoulIceConfig();

        try (FileWriter writer = new FileWriter(CONFIG_FILE)) {
            GSON.toJson(instance, writer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return instance;
    }

    private static final File CONFIG_FILE = new File(QuiltLoader.getConfigDir().toFile(),  "soul_ice_config.json");
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static SoulIceConfig instance = null;
}
