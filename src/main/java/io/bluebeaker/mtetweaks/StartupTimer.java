package io.bluebeaker.mtetweaks;

import java.lang.management.ManagementFactory;

import net.minecraft.client.gui.GuiMainMenu;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class StartupTimer {
    public static long startupTime = 0L;
    private static boolean triggered = false;
    @SubscribeEvent
    public static void onMainmenuOpen(GuiOpenEvent event){
        if(!triggered && event.getGui() instanceof GuiMainMenu)
        {
            startupTime = ManagementFactory.getRuntimeMXBean().getUptime();
        }

    }
}
