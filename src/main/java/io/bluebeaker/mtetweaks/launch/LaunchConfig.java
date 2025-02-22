package io.bluebeaker.mtetweaks.launch;


import io.bluebeaker.mtetweaks.MTETweaksMod;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
@Config(modid = MTETweaksMod.MODID, name = MTETweaksMod.MODID+"_launch",type = Config.Type.INSTANCE,category = "launch")
public class LaunchConfig {
    @Config.Comment({
        "Used to check whether the player is launching the modpack for the first time.",
        "For this to work, this config file shouldn't be included in the modpack."})
    public static boolean isModpackLaunched = false;
    @Config.Comment({
        "Whether the player have opened the quest menu for the first time.",
        "For this to work, this config file shouldn't be included in the modpack."})
    public static boolean isQuestsOpened = false;
}
