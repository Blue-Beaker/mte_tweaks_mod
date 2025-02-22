package io.bluebeaker.mtetweaks.launch;

import io.bluebeaker.mtetweaks.MTETweaksConfig;
import io.bluebeaker.mtetweaks.MTETweaksMod;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.text.ITextComponent;

import javax.annotation.Nullable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FirstLaunchInfoScreen extends GuiScreen {

    private @Nullable ITextComponent text = null;

    public FirstLaunchInfoScreen()
    {
        try {
            this.text = ITextComponent.Serializer.jsonToComponent(MTETweaksConfig.launch.launch_message);
        }
        catch (Exception e){
            MTETweaksMod.getLogger().error("Exception parsing first-launch text",e);
        }
    }

    public void initGui()
    {
        super.initGui();
        this.buttonList.add(new GuiButton(0, this.width / 2 - 100, this.height - 50, I18n.format("mtepatches.firstlaunch.continue")));
    }

    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        this.drawDefaultBackground();
        if(this.text!=null)
            drawMultiLinedString(this,this.fontRenderer,this.text.getFormattedText(),this.width/2,60,16777215, this.width-30);
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    public static void drawMultiLinedString(GuiScreen screen, FontRenderer font,String string, int x, int y, int color, int maxWidth){
        List<String> lines = new ArrayList<>();
        for(String line : string.split("\\n")){
            lines.addAll(
            font.listFormattedStringToWidth(line,maxWidth));
        }
        int y2=y;
        for (String line:lines){
            screen.drawCenteredString(font,line ,x ,y2 , color);
            y2=y2+font.FONT_HEIGHT+1;
        }
    }

    protected void keyTyped(char typedChar, int keyCode) throws IOException
    {
    }

    protected void actionPerformed(GuiButton button) throws IOException
    {
        this.mc.displayGuiScreen((GuiScreen)null);
    }
}
