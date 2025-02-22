package io.bluebeaker.mtetweaks.launch;

import com.feed_the_beast.ftblib.lib.gui.GuiWrapper;
import com.feed_the_beast.ftbquests.gui.tree.GuiQuestTree;
import io.bluebeaker.mtetweaks.MTETweaksMod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class QuestScreenChecker {
    public static final Minecraft mc = Minecraft.getMinecraft();
    public static QuestToast questToast = null;
    @SubscribeEvent
    public static void onScreenOpen(GuiOpenEvent event){
        GuiScreen gui = event.getGui();
        if(gui == null) {
            return;
        }
        if(!LaunchConfig.isQuestsOpened && gui instanceof GuiWrapper)
        {
            GuiWrapper gui2 = (GuiWrapper) gui;
            if(gui2.getGui() instanceof GuiQuestTree){
                LaunchConfig.isQuestsOpened=true;
                ConfigManager.sync(MTETweaksMod.MODID, Config.Type.INSTANCE);
                closeQuestToast();
            }
            MTETweaksMod.getLogger().info(gui2.getClass().getName());
        }
    }

    private static void closeQuestToast() {
        if (questToast!=null) {
            questToast.hide();
            questToast = null;
        }
    }

    @SubscribeEvent
    public static void onScreenChange(GuiOpenEvent event){
        if(mc.world==null || mc.player==null) {
            closeQuestToast();
            return;
        }
        if(!LaunchConfig.isQuestsOpened && questToast==null){
            questToast=new QuestToast();
            mc.getToastGui().add(questToast);
        }
        if(LaunchConfig.isQuestsOpened)
            closeQuestToast();
    }
}
