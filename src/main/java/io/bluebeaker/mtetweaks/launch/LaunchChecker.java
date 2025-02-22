package io.bluebeaker.mtetweaks.launch;

import io.bluebeaker.mtetweaks.MTETweaksConfig;
import io.bluebeaker.mtetweaks.MTETweaksMod;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class LaunchChecker {
    public static boolean messageShown = false;
    @SubscribeEvent
    public static void onScreenOpen(GuiOpenEvent event){
        GuiScreen gui = event.getGui();
        if(!messageShown && (!LaunchConfig.isModpackLaunched || MTETweaksConfig.launch.always_show_launch_message) && gui instanceof GuiMainMenu)
        {
            if(!MTETweaksConfig.launch.launch_message.isEmpty()){
                event.setGui(new FirstLaunchInfoScreen());
                messageShown=true;
                LaunchConfig.isModpackLaunched=true;
                ConfigManager.sync(MTETweaksMod.MODID, Config.Type.INSTANCE);
            }
        }
    }
}
