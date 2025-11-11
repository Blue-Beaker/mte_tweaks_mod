package io.bluebeaker.mtetweaks.heatsink;

import io.bluebeaker.mtetweaks.heatsink.handlers.ForestryHandler;

public class ForestryPlugin {
    public static void init(){
        AccelerableHandlerBuilders.add(HandlerIDs.FORESTRY,new ForestryHandler.Factory());
    }
}
