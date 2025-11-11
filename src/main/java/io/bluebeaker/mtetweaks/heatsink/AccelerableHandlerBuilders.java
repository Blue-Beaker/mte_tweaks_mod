package io.bluebeaker.mtetweaks.heatsink;

import io.bluebeaker.mtetweaks.heatsink.handlers.BasicAccelerableHandler;
import io.bluebeaker.mtetweaks.heatsink.handlers.DummyHandler;
import io.bluebeaker.mtetweaks.heatsink.handlers.IAccelerableHandler;

import java.util.HashMap;
import java.util.Map;

public class AccelerableHandlerBuilders {
    public static final BasicAccelerableHandler.Factory BASIC = new BasicAccelerableHandler.Factory();
    public static final DummyHandler.Factory DUMMY = new DummyHandler.Factory();

    private static final Map<String, HandlerFactory<? extends IAccelerableHandler>> builders = new HashMap<>();

    public static void init(){
        builders.put(HandlerIDs.BASIC,BASIC);
        builders.put(HandlerIDs.DUMMY,DUMMY);
    }

    public static IAccelerableHandler getForName(String str){
        String[] split = str.split(":",2);
        if(split.length>1){
            return builders.getOrDefault(split[0].toUpperCase(),BASIC).build(split[1]);
        }
        return BASIC.build(str);
    }
}
