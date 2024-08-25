package io.bluebeaker.mtetweaks;

import org.luaj.vm2.lib.PackageLib.searchpath;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.Config.Comment;
import net.minecraftforge.common.config.Config.LangKey;
import net.minecraftforge.common.config.Config.Type;

@Config(modid = MTETweaksMod.MODID,type = Type.INSTANCE,category = "general")
public class MTETweaksConfig {
    @Comment("List of Windless Dimensions.")
    @LangKey("config.mtetweaks.windlessdims.name")
    public static int[] windlessDims = new int[]{2,-30,-28,-27,-26};

    @LangKey("config.mtetweaks.etool_attack_speed.name")
    public static float etool_attack_speed=6.0f;

    @LangKey("config.mtetweaks.etool_attack_speed_2.name")
    public static float etool_attack_speed_2=0.0f;

    @LangKey("config.mtetweaks.etools_enchantable.name")
    public static boolean etools_enchantable=true;

}