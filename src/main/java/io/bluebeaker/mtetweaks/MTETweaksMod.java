package io.bluebeaker.mtetweaks;

import io.bluebeaker.mtetweaks.blocks.MTETweaksBlocksRegistry;
import io.bluebeaker.mtetweaks.items.HazmatCharmLogic;
import io.bluebeaker.mtetweaks.items.MTETweaksItems;
import io.bluebeaker.mtetweaks.launch.LaunchChecker;
import io.bluebeaker.mtetweaks.launch.QuestScreenChecker;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Config.Type;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.client.event.ConfigChangedEvent.OnConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.event.FMLServerStoppingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import org.apache.logging.log4j.Logger;

@Mod(modid = Tags.MOD_ID, name = Tags.MOD_NAME, version = Tags.VERSION)
public class MTETweaksMod
{
    public static final String MODID = Tags.MOD_ID;
    public static final String NAME = Tags.MOD_NAME;
    public static final String VERSION = Tags.VERSION;
    
    public MinecraftServer server;

    private static Logger logger;
    private static boolean isServer = false;
    
    public MTETweaksMod() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    public static boolean isServer() {
        return isServer;
    }

    @EventHandler
    public void onServerStart(FMLServerStartingEvent event){
        this.server=event.getServer();
    }

    @EventHandler
    public void onServerStop(FMLServerStoppingEvent event){
        HazmatCharmLogic.onServerStop(event);
    }

    @EventHandler
    public void preInit(FMLPreInitializationEvent event){
        logger = event.getModLog();
        MinecraftForge.EVENT_BUS.register(WrenchTweaks.class);
        MinecraftForge.EVENT_BUS.register(MTETweaksItems.class);
        MinecraftForge.EVENT_BUS.register(HazmatCharmLogic.class);
        if(event.getSide()== Side.CLIENT){
            MinecraftForge.EVENT_BUS.register(StartupTimer.class);
            MinecraftForge.EVENT_BUS.register(LaunchChecker.class);
            MinecraftForge.EVENT_BUS.register(QuestScreenChecker.class);
        }
        if(event.getSide()==Side.SERVER){
            isServer =true;
        }
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
