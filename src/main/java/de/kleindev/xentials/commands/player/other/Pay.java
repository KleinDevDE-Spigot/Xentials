package de.kleindev.xentials.commands.player.other;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Pay implements CommandExecutor{
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length <= 1){
            //Usage
        } else if(args.length == 2){
            if (Bukkit.getPlayer(args[0]) != null){

            } else{
                //Player Not Online
            }
        } else {
            // Wrong Arguments
        }
        return false;
    }
}
