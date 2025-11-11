package io.bluebeaker.mtetweaks;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.Config.Comment;
import net.minecraftforge.common.config.Config.LangKey;
import net.minecraftforge.common.config.Config.RangeDouble;
import net.minecraftforge.common.config.Config.Type;

@Config(modid = MTETweaksMod.MODID,type = Type.INSTANCE,category = "general")
public class MTETweaksConfig {
    @Comment("List of Windless Dimensions.")
    @LangKey("config.mtetweaks.ic2.windlessdims.name")
    public static int[] windlessDims = new int[]{2,-30,-28,-27,-26};

    @LangKey("config.mtetweaks.ic2.etool_attack_speed.name")
    public static float etool_attack_speed=6.0f;

    @LangKey("config.mtetweaks.ic2.etool_attack_speed_2.name")
    public static float etool_attack_speed_2=-2.4f;

    @LangKey("config.mtetweaks.ic2.etools_enchantable.name")
    public static boolean etools_enchantable=true;

    @LangKey("config.mtetweaks.road_walk_speed.name")
    @RangeDouble(min=0.0001,max=10000.0)
    public static double road_walk_speed=1.25D;

    @Config.RangeInt(min = 0,max = 10000000)
    @LangKey("config.mtetweaks.ic2.hazmat_charm_cost.name")
    public static int hazmat_charm_cost = 100;

    @Comment("Tweak speed of harvesting IC2 machines with thermal wrench.")
    @LangKey("config.mtetweaks.cofh_wrench_on_ic2_machines.name")
    @RangeDouble(min=0.0001,max=10000.0)
    public static float cofh_wrench_on_ic2_machines=4.0f;

    @LangKey("config.mtetweaks.wrenches.name")
    public static String[] wrenches={};
    @LangKey("config.mtetweaks.wrenchable_blocks.name")
    @Comment("Extra blocks to be broken when sneak-using a wrench defined above.")
    public static String[] wrenchable_blocks={"minecraft:piston","minecraft:sticky_piston"};

    @Comment("When flying with elytra + IC2 jetpack, hold forward + jump to boost forward. Set 0 to disable.")
    @LangKey("config.mtetweaks.ic2.jetpack_boost_forward.name")
    @RangeDouble(min=0,max = 10000.0)
    public static double jetpack_elytra_boost=0.1D;

    public static boolean log_debug=false;

    @LangKey("config.mtetweaks.ic2.name")
    public static IC2 ic2 = new IC2();

    public static class IC2{

        @Comment("Print additional growth-related crop info when using cropnalyzer.")
        @LangKey("config.mtetweaks.ic2.moreCropInfo.name")
        public boolean moreCropInfo=true;

        @Comment("Add the hazmat charm, which can prevent radiation without hazmat suit, at cost of energy.")
        @LangKey("config.mtetweaks.ic2.hazmat_charm.name")
        public boolean hazmat_charm=true;

        @Comment("Disable jetpack when creative-type flying.")
        @LangKey("config.mtetweaks.ic2.disable_jetpack_creative_flying.name")
        public boolean disable_jetpack_creative_flying =true;

        @Comment("Stops elytra flying when hovering with jetpack.")
        @LangKey("config.mtetweaks.ic2.stop_elytra_hover.name")
        public boolean stop_elytra_hover=true;
    }

    public static Thermal thermal = new Thermal();
    public static class Thermal{
        @Comment({"Additional tiles to be accelerated by Thermal Mediator.",
                "Format: modid:tile_id=HANDLER[:params]",
                "HANDLER can be BASIC, DUMMY or FORESTRY, BUILDCRAFT(when those mods are loaded)"})
        @LangKey("config.mtetweaks.acceleratableTiles.name")
        public String[] acceleratableTiles ={};

        @Comment({"Acceleration cost per MJ"})
        @LangKey("config.mtetweaks.accelCostPerMJ.name")
        public float accelCostPerMJ = 10;
    }

    @Comment("Configurations related to first-launch options.")
    public static Launch launch = new Launch();

    public static class Launch{
        @Comment({"JSON text to be used in first-launch message.","Leave empty to disable the feature."})
        @LangKey("config.mtetweaks.launch.launch_message.name")
        public String launch_message="{\"type\":\"translatable\",\"translate\":\"mtepatches.firstlaunch.default_title\",\"extra\":[{\"text\":\"\\n\\n\"},{\"type\":\"translatable\",\"translate\":\"mtepatches.firstlaunch.default_l1\"},{\"text\":\"\\n\"},{\"type\":\"translatable\",\"translate\":\"mtepatches.firstlaunch.default_l2\"},{\"text\":\"\\n\"},{\"type\":\"translatable\",\"translate\":\"mtepatches.firstlaunch.default_l3\"}]}";

        @Comment({"Always show the first-launch message even it's not the first-launch. Useful when writing the message."})
        @LangKey("config.mtetweaks.launch.always_show_launch_message.name")
        public boolean always_show_launch_message=false;
    }

    public static WorldInit worldInit = new WorldInit();
    public static class WorldInit {
        @LangKey("config.mtetweaks.worldInit.defaultGamerules.name")
        public String[] defaultGamerules = {};
        @Comment({"Commands to be executed on world creation."})
        @LangKey("config.mtetweaks.worldInit.initialCommands.name")
        public String[] initialCommands = {};
    }



}