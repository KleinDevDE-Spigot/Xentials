package de.kleindev.xentials.listener;

import de.kleindev.xentials.tools.Config;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class PlayerCommandPreprocess implements Listener {

    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent e){
        String label = e.getMessage().replaceFirst(e.getMessage().split(" ")[0], "");
        String[] args = e.getMessage().replaceFirst(e.getMessage().split(" ")[0], "").split(" ");
        String cmd = e.getMessage().split(" ")[0].replaceFirst("/", "");
        Player sender = e.getPlayer();

        if (cmd.equalsIgnoreCase("stop")){
            e.setCancelled(true);
            stopServer(sender, args);
        } else if (cmd.equalsIgnoreCase("reload")) {
            e.setCancelled(true);
            reloadServer(sender, args);
        }
    }

    private void stopServer(Player sender, String[] args){
        if (sender.hasPermission(Config.getPermission("server.stop"))){
            if (Config.getConfigValue("Modules.Commands.server.Stop.Password").equals("none")){
                for (Player p : Bukkit.getOnlinePlayers()){
                    if (p.hasPermission("server.stop.notifyStop")) p.sendMessage(Config.getMessage("Commands.server.Stop.TeamKickMessage").replaceAll("%sender%", sender.getName()));
                    else p.sendMessage(Config.getMessage("Commands.server.Stop.KickMessage").replaceAll("%sender%", sender.getName()));
                }
                Bukkit.shutdown();
            } else {
                if (args.length == 0)
                    sender.sendMessage(Config.getMessage("server.Stop.Usage"));
                else if (args.length == 1) {
                    if (args[0].equals(Config.getConfigValue("Modules.Commands.server.Stop.Password"))) {
                        for (Player p : Bukkit.getOnlinePlayers()){
                            if (p.hasPermission("server.stop.notifyStop")) p.kickPlayer(Config.getMessage("Commands.server.Stop.TeamKickMessage").replaceAll("%sender%", sender.getName()));
                            else p.kickPlayer(Config.getMessage("Commands.server.Stop.KickMessage").replaceAll("%sender%", sender.getName()));
                        }
                        Bukkit.shutdown();
                    } else {
                        sender.kickPlayer(Config.getMessage("General.WrongPassword"));
                        if (Config.isActive("Modules.Commands.server.Stop.Password.Notify")){
                            for (Player p : Bukkit.getOnlinePlayers()){
                                if (p.hasPermission(Config.getPermission("server.stop.notifyWrongPassword")))
                                    p.sendMessage(Config.getMessage("server.Stop.NotifyWrongPassword"));
                            }

                        }
                    }
                } else sender.sendMessage(Config.getMessage("General.WrongArguments"));
            }
        }
    }

    private void reloadServer(Player sender, String[] args){
        if (sender.hasPermission(Config.getPermission("server.reload"))){
            if (Config.getConfigValue("Modules.Commands.server.Reload.Password").equals("none")){
                for (Player p : Bukkit.getOnlinePlayers()){
                    if (p.hasPermission("server.reload")) p.sendMessage(Config.getMessage("Commands.server.Reload.ServerReload2").replaceAll("%sender%", sender.getName()));
                    else p.sendMessage(Config.getMessage("Commands.server.Reload.ServerReload1").replaceAll("%sender%", sender.getName()));
                }
                Bukkit.reload();
                for (Player p : Bukkit.getOnlinePlayers()){
                    p.sendMessage(Config.getMessage("Commands.server.Reload.ReloadFinished"));
                }
            } else {
                if (args.length == 0)
                    sender.sendMessage(Config.getMessage("server.Reload.Usage"));
                else if (args.length == 1) {
                    if (args[0].equals(Config.getConfigValue("Modules.Commands.server.Stop.Password"))) {
                        for (Player p : Bukkit.getOnlinePlayers()){
                            if (p.hasPermission("server.reload.notifyReload")) p.sendMessage(Config.getMessage("Commands.server.Reload.ServerReload2").replaceAll("%sender%", sender.getName()));
                            else p.sendMessage(Config.getMessage("Commands.server.Reload.ServerReload2").replaceAll("%sender%", sender.getName()));
                        }
                        Bukkit.reload();
                        for (Player p : Bukkit.getOnlinePlayers()){
                            p.sendMessage(Config.getMessage("Commands.server.Reload.ReloadFinished"));
                        }
                    } else {
                        sender.sendMessage(Config.getMessage("General.WrongPassword"));
                        if (Config.isActive("Modules.Commands.server.Reload.Password.Notify")){
                            for (Player p : Bukkit.getOnlinePlayers()){
                                if (p.hasPermission(Config.getPermission("server.reload.notifyWrongPassword")))
                                    p.sendMessage(Config.getMessage("Commands.server.Reload.NotifyWrongPassword"));
                            }
                        }
                    }
                } else sender.sendMessage(Config.getMessage("General.WrongArguments"));
            }
        }
    }
}
