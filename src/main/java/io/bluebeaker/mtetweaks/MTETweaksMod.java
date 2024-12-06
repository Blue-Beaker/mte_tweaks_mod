package io.bluebeaker.mtetweaks;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.common.config.Config.Type;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.client.event.ConfigChangedEvent.OnConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.event.FMLServerStoppingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import org.apache.logging.log4j.Logger;

@Mod(modid = Tags.MOD_ID, name = Tags.MOD_NAME, version = Tags.VERSION)
public class MTETweaksMod
{
    public static final String MODID = Tags.MOD_ID;
    public static final String NAME = Tags.MOD_NAME;
    public static final String VERSION = Tags.VERSION;
    
    public MinecraftServer server;

    private static Logger logger;
    
    public MTETweaksMod() {
        MinecraftForge.EVENT_BUS.register(this);
    }
    @EventHandler
    public void onServerStart(FMLServerStartingEvent event){
        this.server=event.getServer();
    }

    @EventHandler
    public void onServereStop(FMLServerStoppingEvent event){
        HazmatCharmLogic.onServerStop(event);
    }

    @EventHandler
    public void preInit(FMLPreInitializationEvent event){
        logger = event.getModLog();
        MinecraftForge.EVENT_BUS.register(WrenchTweaks.class);
        MinecraftForge.EVENT_BUS.register(StartupTimer.class);
        MinecraftForge.EVENT_BUS.register(MTETweaksItems.class);
        MinecraftForge.EVENT_BUS.register(HazmatCharmLogic.class);
    }
    @EventHandler
    public void onInit(FMLInitializationEvent event){
        ConfigHandler.loadConfig();
    }
    @SubscribeEvent
    public void registerBlocks(RegistryEvent.Register<Block> event) {
        new MTETweaksBlocksRegistry(event);
    }
    @SubscribeEvent
    public void registerItems(RegistryEvent.Register<Item> event) {
        MTETweaksBlocksRegistry.getInstance().registerItems(event);
    }

    @SubscribeEvent
    public void onConfigChangedEvent(OnConfigChangedEvent event) {
        if (event.getModID().equals(MODID)) {
            ConfigManager.sync(MODID, Type.INSTANCE);
            ConfigHandler.loadConfig();
        }
    }
    public static Logger getLogger(){
        return logger;
    }
}
