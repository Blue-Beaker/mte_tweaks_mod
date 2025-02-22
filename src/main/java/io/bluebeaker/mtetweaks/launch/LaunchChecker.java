package io.bluebeaker.mtetweaks.launch;

import io.bluebeaker.mtetweaks.MTETweaksConfig;
import io.bluebeaker.mtetweaks.MTETweaksMod;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class LaunchChecker {
    @SubscribeEvent
    public static void onScreenOpen(GuiOpenEvent event){
        GuiScreen gui = event.getGui();
        if((!LaunchConfig.isModpackLaunched || MTETweaksConfig.launch.always_show_launch_message) && gui instanceof GuiMainMenu)
        {
            if(!MTETweaksConfig.launch.launch_message.isEmpty()){
                event.setGui(new FirstLaunchInfoScreen());
                LaunchConfig.isModpackLaunched=true;
                ConfigManager.sync(MTETweaksMod.MODID, Config.Type.INSTANCE);
            }
        }
    }
}
