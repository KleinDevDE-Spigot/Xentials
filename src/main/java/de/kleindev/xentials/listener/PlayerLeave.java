package de.kleindev.xentials.listener;

import de.kleindev.xentials.tools.DevTweaks;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.io.File;
import java.io.IOException;

public class PlayerLeave implements Listener{
    @EventHandler
    public void onLeave(PlayerQuitEvent e){
        FileConfiguration file = YamlConfiguration.loadConfiguration(new File("plugins/Xentials/players/"+e.getPlayer().getUniqueId().toString()+".yml"));

        file.set("LastIP", e.getPlayer().getAddress().getAddress().toString());
        file.set("LastLocation", DevTweaks.locationToString(e.getPlayer().getLocation()));
        DevTweaks.saveInventory(e.getPlayer().getInventory(), file);
        // Kit

        try {
            file.save(new File("plugins/Xentials/players/"+e.getPlayer().getUniqueId().toString()+".yml"));
        } catch (IOException e1) {
            e1.printStackTrace();
        }

    }
}
