package de.kleindev.xentials.commands.server;

import de.kleindev.xentials.tools.Config;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class List implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender.hasPermission(Config.getPermission("Server.List"))){
            StringBuilder players = new StringBuilder();
            for (Player p : Bukkit.getOnlinePlayers()){
                String name = p.getName();
                if (p.isOp())
                    name = "§4"+p.getName();
                if (players.toString().equals(""))
                    players = new StringBuilder(name);
                else
                    players.append(", ").append(name);
            }
            sender.sendMessage(Config.getMessage("Commands.Server.List.Header")
            .replaceAll("%count_players%", String.valueOf(Bukkit.getServer().getOnlinePlayers().size()))
            .replaceAll("%max_players%", String.valueOf(Bukkit.getServer().getMaxPlayers())));
            sender.sendMessage(players.toString());
            return true;
        } else {
            sender.sendMessage(Config.getMessage("General.NoPermission"));
            return false;
        }
    }
}
