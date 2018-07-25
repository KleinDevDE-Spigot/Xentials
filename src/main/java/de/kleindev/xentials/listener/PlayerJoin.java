package de.kleindev.xentials.listener;

import de.kleindev.xentials.tools.DevTweaks;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.io.File;
import java.io.IOException;

public class PlayerJoin implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        if (!new File("plugins/Xentials/players/"+e.getPlayer().getUniqueId().toString()+".yml").exists()){
            FileConfiguration file = YamlConfiguration.loadConfiguration(new File("plugins/Xentials/players/"+e.getPlayer().getUniqueId().toString()+".yml"));
            file.set("Name", e.getPlayer().getName());
            file.set("LastIP", e.getPlayer().getAddress().getAddress().toString());
            file.set("LastLocation", DevTweaks.locationToString(e.getPlayer().getLocation()));

            // Kit

            try {
                file.save(new File("plugins/Xentials/players/"+e.getPlayer().getUniqueId().toString()+".yml"));
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        } else {
            FileConfiguration file = YamlConfiguration.loadConfiguration(new File("plugins/Xentials/players/"+e.getPlayer().getUniqueId().toString()+".yml"));

            if (DevTweaks.stringToLocation(file.getString("LastLocation")) != e.getPlayer().getLocation())
                e.getPlayer().teleport(DevTweaks.stringToLocation(file.getString("LastLocation")));
            DevTweaks.setInventory(e.getPlayer(), DevTweaks.loadInventory(file));

            if (!file.getString("Name").equals(e.getPlayer().getName()))
                file.set("Name", e.getPlayer().getName());

            try {
                file.save(new File("plugins/Xentials/players/"+e.getPlayer().getUniqueId().toString()+".yml"));
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }

        DevTweaks.sendActionbar(e.getPlayer(), "Willkommen auf Minecron");
    }
}
