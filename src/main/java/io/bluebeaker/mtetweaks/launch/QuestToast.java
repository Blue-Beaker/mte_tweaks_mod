package io.bluebeaker.mtetweaks.launch;

import net.minecraft.client.gui.toasts.GuiToast;
import net.minecraft.client.gui.toasts.IToast;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextComponentKeybind;

public class QuestToast implements IToast {

    private IToast.Visibility visibility = IToast.Visibility.SHOW;
    @Override
    public Visibility draw(GuiToast toastGui, long delta) {
        toastGui.getMinecraft().getTextureManager().bindTexture(TEXTURE_TOASTS);
        GlStateManager.color(1.0F, 1.0F, 1.0F);
        toastGui.drawTexturedModalRect(0, 0, 0, 96, 160, 32);
        toastGui.getMinecraft().getRenderItem().renderItemIntoGUI(new ItemStack(Items.BOOK),6,6);

        toastGui.getMinecraft().fontRenderer.drawString(I18n.format("mtepatches.questtoast.title"), 30, 7, -11534256);
        toastGui.getMinecraft().fontRenderer.drawString(I18n.format("mtepatches.questtoast.subtitle",new TextComponentKeybind("key.ftbquests.quests").getFormattedText()), 30, 18, -16777216);
        return this.visibility;
    }
    public void hide()
    {
        this.visibility = IToast.Visibility.HIDE;
    }
}
