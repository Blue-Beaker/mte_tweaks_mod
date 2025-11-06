package io.bluebeaker.mtetweaks;

import io.bluebeaker.mtetweaks.heatsink.HeatSinkHandler;
import net.minecraft.util.ResourceLocation;

import java.util.*;

public class ConfigHandler {
    public static Set<Integer> windlessDims = new HashSet<Integer>();

    public static Map<ResourceLocation, Set<Integer>> wrenches = new HashMap<>();

    public static Map<ResourceLocation, List<Map<String, String>>> wrenchableBlocks = new HashMap<>();

    public static void loadConfig() {

        windlessDims.clear();
        for (int id : MTETweaksConfig.windlessDims) {
            ConfigHandler.windlessDims.add(id);
        }

        wrenches.clear();
        for (String name : MTETweaksConfig.wrenches) {
            String[] split0 = name.split(":", 3);
            ResourceLocation id = Utils.getResourceLocationFromStr(name);
            Set<Integer> metas = null;
            if (split0.length >= 3) {
                metas = Utils.getIntegersFromString(split0[2]);
            }
            if(wrenches.containsKey(id)){
                wrenches.get(id).addAll(metas);
            }else{
                wrenches.put(id, metas);
            }
        }

        wrenchableBlocks.clear();
        for (String name : MTETweaksConfig.wrenchable_blocks) {
            String[] split0 = name.split(":", 3);
            ResourceLocation id = Utils.getResourceLocationFromStr(name);
            List<Map<String, String>> allowedStates = null;
            if (split0.length >= 3) {
                allowedStates = Utils.getMappingsFromString(split0[2]);
            }
            if(wrenchableBlocks.containsKey(id)){
                wrenchableBlocks.get(id).addAll(allowedStates);
            }else{
                wrenchableBlocks.put(id, allowedStates);
            }
        }

        if (MTETweaksConfig.log_debug) {
            MTETweaksMod.getLogger().info("WindlessDims:" + windlessDims.toString());
            MTETweaksMod.getLogger().info("Wrenches:" + wrenches.toString());
            MTETweaksMod.getLogger().info("Wrenchables:" + wrenchableBlocks.toString());
        }

        HeatSinkHandler.updateConfig();
    }

}
