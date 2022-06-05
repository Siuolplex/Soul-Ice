package io.siuolplex.soul_ice;

import io.siuolplex.soul_ice.entries.forge.WoodRegistryEntrySetImpl;
import io.siuolplex.soul_ice.registry.*;
import io.siuolplex.soul_ice.registry.forge.SoulIceWorldGen;
import io.siuolplex.soul_ice.registry.forge.SoulIceBlocksImpl;
import io.siuolplex.soul_ice.registry.forge.SoulIceEnchantmentsImpl;
import io.siuolplex.soul_ice.registry.forge.SoulIceItemsImpl;
import io.siuolplex.soul_ice.util.Loader;
import io.siuolplex.soul_ice.util.MidnightConfig;
import io.siuolplex.soul_ice.util.SoulIceConfig;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStep;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;
import net.minecraftforge.registries.ForgeRegistries;
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
        Loader.setConstants("forge", false, FMLPaths.CONFIGDIR.get());
        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> SoulIce::quickClient);
        MidnightConfig.init("more_than_just_soul_ice", SoulIceConfig.class);
        modEventBus.addGenericListener(ForgeRegistries.BLOCKS.getRegistrySuperType(), SoulIce::registerBlocks);
        modEventBus.addGenericListener(ForgeRegistries.ITEMS.getRegistrySuperType(), SoulIce::registerItems);
        modEventBus.addGenericListener(ForgeRegistries.ENCHANTMENTS.getRegistrySuperType(), SoulIce::registerEnchantment);
        IEventBus bus = MinecraftForge.EVENT_BUS;
        bus.addListener(SoulIce::onBiomeLoad);
    }

    public static void quickClient() {
        Loader.isClient = true;
    }

    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        SoulIceBlocks.init();
        MTJSIEntries.init();
        SoulIceBlocksImpl.blockMap.forEach((id, block) -> event.getRegistry().register(block.setRegistryName(id)));
    }

    public static void registerItems(RegistryEvent.Register<Item> event) {
        SoulIceItems.init();
        SoulIceItemsImpl.itemMap.forEach((id, item) -> event.getRegistry().register(item.setRegistryName(id)));
        WoodRegistryEntrySetImpl.entrySetItemMap.forEach((id, item) -> event.getRegistry().register(item.setRegistryName(id)));
    }

    public static void registerEnchantment(RegistryEvent.Register<Enchantment> event) {
        SoulIceEnchantments.init();
        SoulIceEnchantmentsImpl.enchantmentMap.forEach((id, enchantment) -> event.getRegistry().register(enchantment.setRegistryName(id)));
    }

    public static void onBiomeLoad(BiomeLoadingEvent ble) {
        if (!ble.getCategory().equals(Biome.Category.THEEND) || !ble.getCategory().equals(Biome.Category.NETHER) && SoulIceConfig.enableLightstoneGeneration) {
            ble.getGeneration().feature(GenerationStep.Feature.UNDERGROUND_ORES, SoulIceWorldGen.MTJSIPlacedFeatures.LIGHTSTONE);
        }
        if (ble.getCategory().equals(Biome.Category.PLAINS) && SoulIceConfig.enableOrangeRoseGeneration) {
            ble.getGeneration().feature(GenerationStep.Feature.VEGETAL_DECORATION, SoulIceWorldGen.MTJSIPlacedFeatures.ORANGE_ROSE);
        }
        if (ble.getCategory().equals(Biome.Category.DESERT) && SoulIceConfig.enableRujoneBerryGeneration) {
            ble.getGeneration().feature(GenerationStep.Feature.VEGETAL_DECORATION, SoulIceWorldGen.MTJSIPlacedFeatures.RUJONE_BERRY);
        }
    }
}
