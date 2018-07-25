package de.kleindev.xentials.commands.player.chat;

import de.kleindev.xentials.tools.Config;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Helpop implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player){
            if (commandSender.hasPermission(Config.getPermission("Player.Chat.Helpop.Use"))){
                if (strings.length == 0){
                    commandSender.sendMessage(Config.getMessage("Commands.Player.Chat.Helpop.Usage"));
                    return true;
                }
                int count = 0;
                for (Player p : Bukkit.getOnlinePlayers()){
                    if (p.hasPermission(Config.getPermission("Player.Chat.Helpop.Receive")) || p.isOp()){
                        count++;
                        p.sendMessage(Config.getMessage("Commands.Player.Chat.Helpop.Format").replaceAll("%sender", commandSender.getName()).replaceAll("%message%", s.replaceFirst(strings[0]+" ", "")));
                    }
                }
                if (count == 0){
                    commandSender.sendMessage("Player.Chat.Helpop.NobodyOnline");
                } else commandSender.sendMessage("Player.Chat.Helpop.MessageSent");
            } else commandSender.sendMessage(Config.getMessage("General.NoPermission"));
        } else commandSender.sendMessage(Config.getMessage("General.NoPlayer"));
        return true;
    }
}
