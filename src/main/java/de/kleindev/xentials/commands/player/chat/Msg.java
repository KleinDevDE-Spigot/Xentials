package de.kleindev.xentials.commands.player.chat;

import de.kleindev.xentials.tools.Config;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Msg implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender.hasPermission(Config.getPermission("Player.Chat.Msg"))) {
            if (strings.length == 0) {
                commandSender.sendMessage(Config.getMessage("Commands.Chat.Msg.Usage"));
            } else if (strings.length == 1) {
                commandSender.sendMessage(Config.getMessage("Commands.Chat.Msg.Usage"));
            } else {
                if (Bukkit.getPlayer(strings[0]) != null){
                    commandSender.sendMessage("");
                    Bukkit.getPlayer(strings[0]).sendMessage(Config.getMessage(""));
                } else if (Bukkit.getOfflinePlayer(strings[0]) != null){
                    commandSender.sendMessage("Commands.General.PlayerNotOnline");
                } else commandSender.sendMessage("Commands.General.PlayerNotFound");
            }
        } else commandSender.sendMessage(Config.getMessage("General.NoPermission"));
        return false;
    }
}
