package de.kleindev.xentials.commands.player.options;

import de.kleindev.xentials.tools.Config;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Feed implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (strings.length == 0){
            if (commandSender instanceof Player){
                if (commandSender.hasPermission(Config.getPermission("Player.Options.Feed.own"))){
                    Player p = ((Player) commandSender);
                    p.setFoodLevel(20);
                    p.sendMessage(Config.getMessage("Commands.Player.Options.Feed.Self"));
                }
            } else commandSender.sendMessage(Config.getMessage("General.NoPlayer"));
        } else if (strings.length == 1){
            if (commandSender.hasPermission(Config.getPermission("Player.Options.Feed.others"))){
                if (Bukkit.getPlayer(strings[0]) != null){
                    Player p = Bukkit.getPlayer(strings[0]);
                    p.setFoodLevel(20);
                    commandSender.sendMessage(Config.getMessage("Commands.Player.Options.Feed.Player1").replaceAll("%sender", commandSender.getName()).replaceAll("%player%", p.getName()));
                    p.sendMessage(Config.getMessage("Commands.Player.Options.Feed.Player2").replaceAll("%sender", commandSender.getName()).replaceAll("%player%", p.getName()));
                } else if (Bukkit.getOfflinePlayer(strings[0]) != null){
                    commandSender.sendMessage(Config.getMessage("General.PlayerNotOnline"));
                } else commandSender.sendMessage(Config.getMessage("General.PlayerNotFound"));
            }
        } else commandSender.sendMessage(Config.getMessage("Commands.Player.Options.Feed.Usage"));
        return false;
    }
}
