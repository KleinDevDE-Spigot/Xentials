package de.kleindev.xentials.tools;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Config {
    private final static FileConfiguration cfg = YamlConfiguration.loadConfiguration(new File("plugins/Xentials/config.yml"));
    private final static FileConfiguration language = YamlConfiguration.loadConfiguration(new File("plugins/Xentials/language/"+cfg.getString("lang")+".yml"));
    private final static FileConfiguration perms = YamlConfiguration.loadConfiguration(new File("plugins/Xentials/perms.yml"));

    public static Permission getPermission(String permission){
        return new Permission(perms.getString(permission));
    }

    public static String getConfigValue(String key){
        return cfg.getString(key);
    }

    public static boolean isActive(String key){
        return cfg.getBoolean(key);
    }

    public static boolean setConfigValue(String key, String value){
        cfg.set(key, value);
        try {
            cfg.save(new File("plugins/Xentials/config.yml"));
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static String getMessage(String message){
        return ChatColor.translateAlternateColorCodes('&', language.getString(message)
                .replaceAll("%prefix%", language.getString("prefix")));
    }

    public static String[] getMessages(String message){
        String[] strings;
        strings = language.getStringList(message).toArray(new String[0]);
        int count = 0;
        for(String s : language.getStringList(message)){
            strings[count] = ChatColor.translateAlternateColorCodes('&', s.replaceAll("%prefix%", language.getString("prefix")));
            count++;
        }
        return strings;
    }

    public static ArrayList<String> getCustomCommands(){
        return  new ArrayList<>(cfg.getConfigurationSection("CustomCommands").getKeys(false));
    }
}
