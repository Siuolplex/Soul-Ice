package io.siuolplex.soul_ice;

import io.siuolplex.soul_ice.forge.registry.SoulIceBlocks;
import io.siuolplex.soul_ice.forge.registry.SoulIceEnchantments;
import io.siuolplex.soul_ice.forge.registry.SoulIceItems;
import io.siuolplex.soul_ice.forge.registry.SoulIceWorldGen;
import net.minecraft.block.Blocks;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.RegistryObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod("soul_ice")
public class SoulIce {
    public static String id = "soul_ice";

    public static Identifier unfalteringSyncID = idFormatter("unfaltering_sync");
    public static Identifier freezingSyncID = idFormatter("freezing_sync");
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
        IEventBus bus = MinecraftForge.EVENT_BUS;
        bus.addListener(SoulIce::onBiomeLoad);
    }

    public static void onBiomeLoad(BiomeLoadingEvent ble) {
        if (!ble.getCategory().equals(Biome.Category.THEEND) || !ble.getCategory().equals(Biome.Category.NETHER) && (SoulIceConfig.instance().enableLightstoneGeneration)) {
            ble.getGeneration().feature(GenerationStep.Feature.UNDERGROUND_ORES, SoulIceWorldGen.LIGHTSTONE_GEN);
        }
    }

    public static void init(FMLCommonSetupEvent event) {
        event.enqueueWork(SoulIceWorldGen::init);
    }
}
