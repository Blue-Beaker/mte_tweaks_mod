package io.bluebeaker.mtetweaks;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.Config.Comment;
import net.minecraftforge.common.config.Config.LangKey;
import net.minecraftforge.common.config.Config.Type;

@Config(modid = MTETweaksMod.MODID,type = Type.INSTANCE,category = "general")
public class MTETweaksConfig {
    @Comment("List of Windless Dimensions.")
    @LangKey("config.mtetweaks.windlessdims.name")
    public static int[] windlessDims = new int[]{2,-30,-28,-27,-26};

}