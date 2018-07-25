package de.kleindev.xentials.objects;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.HashMap;
import java.util.List;

public class CustomCommand {
    private String cmd;
    private String permission;
    private List<String> output;
    private FileConfiguration cfg = YamlConfiguration.loadConfiguration(new File("plugins/Utils/config.yml"));
    private HashMap<String, List<String>> args;
    private boolean hasArgs;
    private String usage;

    public CustomCommand(String command){
        this.cmd = command;
        this.permission = cfg.getString("Modules.CustomCommands."+command+".permission");
        this.output = cfg.getStringList("Modules.CustomCommands."+command+".output");
        this.hasArgs = cfg.getConfigurationSection("Modules.CustomCommands."+command).contains("args");
        if (this.hasArgs){
            for (String a : cfg.getConfigurationSection("Modules.CustomCommands."+command+".args").getKeys(false)){
                this.args.put(a, cfg.getStringList("Modules.CustomCommands."+command+".args."+a));
            }
        }
    }

    public String getCmd() {
        return cmd;
    }

    public String getPermission() {
        return permission;
    }

    public List<String> getOutput() {
        return output;
    }

    public boolean hasArgs() {
        return hasArgs;
    }

    public HashMap<String, List<String>> getArgs() {
        return args;
    }

    public String getUsage() {
        String arg = "";
        for (String s : args.keySet())
            arg = arg + s + " | ";
        return "/"+cmd+" (" + arg + ")";
    }
}
