package io.bluebeaker.mtetweaks;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.Config.Comment;
import net.minecraftforge.common.config.Config.LangKey;
import net.minecraftforge.common.config.Config.RangeDouble;
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

    @LangKey("config.mtetweaks.road_walk_speed.name")
    @RangeDouble(min=0.0001,max=10000.0)
    public static double road_walk_speed=1.25D;

    @Comment("Tweak speed of harvesting IC2 machines with thermal wrench.")
    @LangKey("config.mtetweaks.cofh_wrench_on_ic2_machines.name")
    @RangeDouble(min=0.0001,max=10000.0)
    public static float cofh_wrench_on_ic2_machines=4.0f;

    public static String[] wrenches={};
    @Comment("Extra blocks to be broken when sneak-using a wrench defined above.")
    public static String[] wrenchable_blocks={"minecraft:piston","minecraft:sticky_piston"};

    public static boolean jetpack_boost_forward=true;

    public static boolean log_debug=false;

}