package io.bluebeaker.mtetweaks.heatsink.handlers;

import io.bluebeaker.mtetweaks.heatsink.AccelerableHandlerBuilders;
import io.bluebeaker.mtetweaks.heatsink.HandlerIDs;

public class BuildCraftPlugin {
    public static void init(){
        AccelerableHandlerBuilders.add(HandlerIDs.MJ,new BuildCraftHandler.Factory());
    }
}
