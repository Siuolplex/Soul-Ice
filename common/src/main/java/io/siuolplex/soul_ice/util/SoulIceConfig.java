package io.siuolplex.soul_ice.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;

public class SoulIceConfig extends MidnightConfig {
    @Comment public static String warn = "While the configs sync to the server, they probably dont change real time, so please restart your game when you change configs";
    @Entry public static float slipperiness = 1.075f;
    @Entry public static boolean enableUnfaltering = true;
    @Entry public static boolean enableFreezing = true;
    @Entry public static boolean enableLightstoneGeneration = true;
    @Entry public static boolean enableRujoneBerryGeneration = true;
    @Entry public static boolean enableOrangeRoseGeneration = true;
}
