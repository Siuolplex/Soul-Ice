package io.siuolplex.soul_ice.util;

import net.minecraft.util.Identifier;

public class SoulIceIDHandler {
    public static String id = "soul_ice";

    public static Identifier idFormatter(String string){
        return new Identifier(id, string);
    }
}
