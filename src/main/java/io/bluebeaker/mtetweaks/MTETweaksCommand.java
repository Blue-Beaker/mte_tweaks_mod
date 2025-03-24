package io.bluebeaker.mtetweaks;

import io.bluebeaker.mtetweaks.defaultGameRule.GameRuleManager;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.GameRules;
import net.minecraftforge.server.command.CommandTreeBase;

import java.util.Arrays;

public class MTETweaksCommand extends CommandTreeBase {
    public MTETweaksCommand(){
        addSubcommand(new SaveGamerulesCommand());
        addSubcommand(new ListGameRulesCommand());
    }
    @Override
    public int getRequiredPermissionLevel() {
        return 2;
    }

    @Override
    public String getName() {
        return "mtetweaks";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "commands.mtetweaks.usage";
    }

    public static class SaveGamerulesCommand extends CommandBase{

        @Override
        public int getRequiredPermissionLevel() {
            return 4;
        }

        @Override
        public String getName() {
            return "savegamerules";
        }

        @Override
        public String getUsage(ICommandSender sender) {
            return "commands.mtetweaks.savegamerules.usage";
        }

        @Override
        public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
            GameRules gameRules = server.getWorld(0).getGameRules();
            GameRuleManager.writeToConfig(gameRules);
            notifyCommandListener(sender,this,0,"commands.mtetweaks.savegamerules.feedback");
        }
    }

    public static class LoadGamerulesCommand extends CommandBase{

        @Override
        public int getRequiredPermissionLevel() {
            return 4;
        }

        @Override
        public String getName() {
            return "loadgamerules";
        }

        @Override
        public String getUsage(ICommandSender sender) {
            return "commands.mtetweaks.loadgamerules.usage";
        }

        @Override
        public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
            GameRules gameRules = server.getWorld(0).getGameRules();
            GameRuleManager.mergeRules(gameRules, Arrays.asList(MTETweaksConfig.worldInit.defaultGamerules));
            notifyCommandListener(sender,this,0,"commands.mtetweaks.loadgamerules.feedback");
        }
    }

    public static class ListGameRulesCommand extends CommandBase{

        @Override
        public int getRequiredPermissionLevel() {
            return 2;
        }

        @Override
        public String getName() {
            return "listgamerules";
        }

        @Override
        public String getUsage(ICommandSender sender) {
            return "commands.mtetweaks.listgamerules.usage";
        }

        @Override
        public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
            GameRules gameRules = server.getWorld(0).getGameRules();
            notifyCommandListener(sender,this,0,"commands.mtetweaks.listgamerules.feedback",String.join("\n",GameRuleManager.getGamerules(gameRules)));
        }
    }
}
