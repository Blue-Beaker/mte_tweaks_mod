package io.bluebeaker.mtetweaks.wrench;

import io.bluebeaker.mtetweaks.MTETweaksConfig;

import java.util.HashSet;
import java.util.Set;

public class ConfigWindmill {
    public static Set<Integer> windlessDims = new HashSet<>();
    public static void update(){
        windlessDims.clear();
        for (int id : MTETweaksConfig.windlessDims) {
            windlessDims.add(id);
        }
    }
}
