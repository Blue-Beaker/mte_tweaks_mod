package io.bluebeaker.mtetweaks;

import io.bluebeaker.mtetweaks.wrench.ConfigWindmill;
import io.bluebeaker.mtetweaks.wrench.ConfigWrench;

public class ConfigHandler {

    public static void loadConfig() {
        ConfigWindmill.update();
        ConfigWrench.update();

        if (MTETweaksConfig.log_debug) {
            MTETweaksMod.getLogger().info("WindlessDims:" + ConfigWindmill.windlessDims.toString());
            MTETweaksMod.getLogger().info("Wrenches:" + ConfigWrench.wrenches.toString());
            MTETweaksMod.getLogger().info("Wrenchables:" + ConfigWrench.wrenchableBlocks.toString());
        }
    }

}
