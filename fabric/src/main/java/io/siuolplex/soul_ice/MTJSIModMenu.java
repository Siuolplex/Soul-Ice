package io.siuolplex.soul_ice;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import io.siuolplex.soul_ice.util.SoulIceConfig;

public class MTJSIModMenu implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parent -> SoulIceConfig.getScreen(parent, "more_than_just_soul_ice");
    }
}
