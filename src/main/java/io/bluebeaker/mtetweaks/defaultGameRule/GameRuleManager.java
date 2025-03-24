package io.bluebeaker.mtetweaks.defaultGameRule;

import io.bluebeaker.mtetweaks.MTETweaksConfig;
import io.bluebeaker.mtetweaks.MTETweaksMod;
import net.minecraft.world.GameRules;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.*;

public class GameRuleManager {

    public static List<String> getGamerules(GameRules gameRules){
        List<String> rules = new ArrayList<>();
        for (String rule : gameRules.getRules()) {
            String value = gameRules.getString(rule);
            rules.add(rule+"="+value);
        }
        return rules;
    }
    public static void writeToConfig(GameRules gameRules){
        List<String> rules = getGamerules(gameRules);
        MTETweaksConfig.worldInit.defaultGamerules = rules.toArray(new String[]{});
        MTETweaksMod.saveConfigs();
    }
    public static GameRules mergeRules(GameRules rules, List<String> overrides){
        for (String line : overrides) {
            String[] split = line.split("=", 2);
            rules.setOrCreateGameRule(split[0],split[1]);
        }
        return rules;
    }
}
