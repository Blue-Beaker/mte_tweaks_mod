package io.bluebeaker.mtetweaks;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.minecraft.util.ResourceLocation;

public class ConfigHandler {
    public static Set<Integer> windlessDims=new HashSet<Integer>() ;

    public static Map<ResourceLocation,Set<Integer>> wrenches = new HashMap<>();
    
    public static Map<ResourceLocation,List<Map<String,String>>> wrenchableBlocks = new HashMap<>();
    public static void loadConfig(){
        windlessDims.clear();
        for (int id:MTETweaksConfig.windlessDims){
            ConfigHandler.windlessDims.add(id);
        }

        wrenches.clear();
        for (String name:MTETweaksConfig.wrenches){
            String[] split0 = name.split(":",3);
            ResourceLocation id = new ResourceLocation(name);
            Set<Integer> metas = null;
            if(split0.length>3){
                metas=Utils.getIntegersFromString(split0[2]);
            }
            wrenches.put(id, metas);
        }

        wrenchableBlocks.clear();
        for (String name:MTETweaksConfig.wrenchable_blocks){
            String[] split0 = name.split(":",3);
            ResourceLocation id = new ResourceLocation(name);
            List<Map<String,String>> allowedStates = null;
            if(split0.length>3){
                allowedStates=Utils.getMappingsFromString(split0[2]);
            }
            wrenchableBlocks.put(id, allowedStates);
        }
    }
    
}
