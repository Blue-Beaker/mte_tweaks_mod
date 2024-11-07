package io.bluebeaker.mtetweaks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Nullable;

import net.minecraft.util.ResourceLocation;

public class Utils {
    /**
     * @param str comma-separated strings. also uses '-' for defining ranges
     * Example: 1,2,4-6 -> [1,2,4,5,6]
     * @return
     */
    public static @Nullable Set<Integer> getIntegersFromString(String str){
        String[] split0 = str.split(",");
        Set<Integer> ints = new HashSet<>();
        for(String substr:split0){
            if(substr.contains("-")){
                String[] split1 = substr.split("-");
                int start=Integer.valueOf(split1[0]);
                int end=Integer.valueOf(split1[1]);
                for(int i=start;i<=end;i++){
                    ints.add(i);
                }
            }else{
                ints.add(Integer.valueOf(substr.trim()));

            }
        }
        return null;
    }
    public static @Nullable List<Map<String,String>> getMappingsFromString(String str){
        List<Map<String,String>> maps = new ArrayList<>();
        String[] states = str.split(";");
        for(String stateDef:states){
            Map<String,String> propertyMap = new HashMap<>();
            String[] pairs = stateDef.split(",");
            for(String pair:pairs){
                String[] split2 = pair.split("=");
                String key=split2[0].trim();
                String value=split2[1].trim();
                propertyMap.put(key, value);
            }
            maps.add(propertyMap);
        }
        return maps;
    }
    public static ResourceLocation getResourceLocationFromStr(String str){
        String[] split0 = str.split(":",3);
        if(split0.length>=2){
            return new ResourceLocation(split0[0],split0[1]);
        }else{
            return new ResourceLocation(split0[0]);
        }
    }
}   
