package io.bluebeaker.mtetweaks;

import net.minecraft.server.MinecraftServer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.common.config.Config.Type;
import net.minecraftforge.fml.client.event.ConfigChangedEvent.OnConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.HashSet;
import org.apache.logging.log4j.Logger;

@Mod(modid = MTETweaksMod.MODID, name = MTETweaksMod.NAME, version = MTETweaksMod.VERSION)
public class MTETweaksMod
{
    public static final String MODID = "mtetweaks";
    public static final String NAME = "MTETweaksMod";
    public static final String VERSION = "1.0";
    
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
    public void onInit(FMLPreInitializationEvent event){
        loadConfig();
    }

    @SubscribeEvent
    public void onConfigChangedEvent(OnConfigChangedEvent event) {
        if (event.getModID().equals(MODID)) {
            ConfigManager.sync(MODID, Type.INSTANCE);
            loadConfig();
        }
    }
    private void loadConfig(){
        ConfigHandler.windlessDims=new HashSet<Integer>();
        for (int id:MTETweaksConfig.windlessDims){
            ConfigHandler.windlessDims.add(id);
        }
    }
    public void logInfo(String log){
        logger.info(log);
    }
}
