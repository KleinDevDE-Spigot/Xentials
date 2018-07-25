package de.kleindev.xentials.listener;

import de.kleindev.xentials.objects.CustomCommand;
import de.kleindev.xentials.tools.Config;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import java.util.ArrayList;

public class CustomCommands implements Listener{
    private final ArrayList<String> commands = Config.getCustomCommands();

    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent e){
        Player sender = e.getPlayer();
        String label = e.getMessage();
        String cmd = label.split(" ")[0];
        String[] args = label.replaceFirst(cmd + " ", "").split(" ");

        if (commands.contains(cmd)){
            e.setCancelled(true);
            CustomCommand customCommand = new CustomCommand(cmd);
            if (sender.hasPermission(customCommand.getPermission())){
                if (customCommand.hasArgs()){
                    if (customCommand.getArgs().containsKey(args[0])){
                        for (String s : customCommand.getArgs().get(args[0])){
                            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', s));
                        }
                    } else sender.sendMessage(Config.getMessage("General.WrongArguments")
                    .replaceAll("%usage%", customCommand.getUsage())
                    .replaceAll("%command%", customCommand.getCmd()));
                } else for (String s : customCommand.getOutput()){
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', s));
                }
            } else sender.sendMessage(Config.getMessage("General.NoPermission")
                    .replaceAll("%command%", cmd)
                    .replaceAll("%permission%", customCommand.getPermission()));
        }
    }
}
