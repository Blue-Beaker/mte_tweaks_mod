package io.bluebeaker.mtetweaks.heatsink;

import io.bluebeaker.mtetweaks.MTETweaksMod;
import io.bluebeaker.mtetweaks.ModChecker;
import io.bluebeaker.mtetweaks.heatsink.handlers.*;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;

public class AccelerableHandlerBuilders {
    public static final BasicAccelerableHandler.Factory BASIC = new BasicAccelerableHandler.Factory();
    public static final DummyHandler.Factory DUMMY = new DummyHandler.Factory();
    public static final RedstoneFluxHandler.Factory RF = new RedstoneFluxHandler.Factory();

    private static final Map<String, HandlerFactory<?>> builders = new HashMap<>();

    public static void init(){
        add(HandlerIDs.BASIC,BASIC);
        add(HandlerIDs.DUMMY,DUMMY);
        add(HandlerIDs.RF,RF);
        if(ModChecker.forestry.isLoaded()){
            ForestryPlugin.init();
        }
        if(ModChecker.buildcraftcore.isLoaded()){
            BuildCraftPlugin.init();
        }
    }

    public static void add(String id, HandlerFactory<?> factory){
        builders.put(id.toUpperCase(),factory);
    }
    @Nullable
    public static IAccelerableHandler getForName(String str){
        String[] split = str.split(":",2);
        String upperCase = split[0].toUpperCase();

        HandlerFactory<?> handlerFactory = builders.get(upperCase);
        if(handlerFactory==null){
            MTETweaksMod.getLogger().warn("No handler type found for '{}'! Available handlers: {}", upperCase,builders.keySet());
            return null;
        }
        return handlerFactory.build(split.length>1?split[1]:"");
    }
}
