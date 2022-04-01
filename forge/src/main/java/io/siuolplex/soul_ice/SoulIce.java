package io.siuolplex.soul_ice;

import io.siuolplex.soul_ice.forge.registry.SoulIceBlocks;
import io.siuolplex.soul_ice.forge.registry.SoulIceEnchantments;
import io.siuolplex.soul_ice.forge.registry.SoulIceItems;
import net.minecraft.block.Blocks;
import net.minecraft.util.Identifier;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod("soul_ice")
public class SoulIce {
    public static String id = "soul_ice";

    public static Identifier soulIceSyncID = idFormatter("soul_ice_sync");

    public static Logger LOGGER = LogManager.getLogger("Soul Ice");

    public static Identifier idFormatter(String string){
        return new Identifier(id, string);
    }

    final ModLoadingContext modLoadingContext = ModLoadingContext.get();
    final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

    public SoulIce() {
        SoulIceBlocks.BLOCKS.register(modEventBus);
        SoulIceItems.ITEMS.register(modEventBus);
        SoulIceEnchantments.ENCHANTMENTS.register(modEventBus);
    }
}
