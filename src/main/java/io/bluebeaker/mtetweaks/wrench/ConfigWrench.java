package io.bluebeaker.mtetweaks.wrench;

import io.bluebeaker.mtetweaks.MTETweaksConfig;
import io.bluebeaker.mtetweaks.Utils;
import net.minecraft.util.ResourceLocation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ConfigWrench {
    public static Map<ResourceLocation, Set<Integer>> wrenches = new HashMap<>();
    public static Map<ResourceLocation, List<Map<String, String>>> wrenchableBlocks = new HashMap<>();
    public static void update(){
        wrenches.clear();
        for (String name : MTETweaksConfig.wrenches) {
            String[] split0 = name.split(":", 3);
            ResourceLocation id = Utils.getResourceLocationFromStr(name);
            Set<Integer> metas = null;
            if (split0.length >= 3) {
                metas = Utils.getIntegersFromString(split0[2]);
            }
            if(wrenches.get(id)!=null){
                if (metas != null) {
                    wrenches.get(id).addAll(metas);
                }
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
            if(wrenchableBlocks.get(id)!=null){
                if (allowedStates != null) {
                    wrenchableBlocks.get(id).addAll(allowedStates);
                }
            }else{
                wrenchableBlocks.put(id, allowedStates);
            }
        }
    }
}
