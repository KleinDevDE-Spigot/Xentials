package de.kleindev.xentials;

import de.kleindev.xentials.commands.player.options.Feed;
import de.kleindev.xentials.listener.*;
import de.kleindev.xentials.commands.player.chat.Helpop;
import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.logging.Logger;

public class Main extends JavaPlugin {
    private static final Logger log = Logger.getLogger("Minecraft");
    private static Economy econ = null;
    private static Permission perms = null;
    private static Chat chat = null;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        saveResource("perms.yml", false);
        saveResource("language", false);
        saveResource("language/de_DE.yml", false);
        /*
        if (!setupEconomy() ) {
            log.severe(String.format("[%s] - Disabled due to no Vault dependency found!", getDescription().getName()));
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        setupPermissions();
        setupChat();
        */
        registerCommands();
        registerListeners();
    }


    @Override
    public void onDisable() {

    }

    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }

    private boolean setupChat() {
        RegisteredServiceProvider<Chat> rsp = getServer().getServicesManager().getRegistration(Chat.class);
        chat = rsp.getProvider();
        return chat != null;
    }

    private boolean setupPermissions() {
        RegisteredServiceProvider<Permission> rsp = getServer().getServicesManager().getRegistration(Permission.class);
        perms = rsp.getProvider();
        return perms != null;
    }

    public static Economy getEconomy() {
        return econ;
    }

    public static Permission getPermissions() {
        return perms;
    }

    public static Chat getChat() {
        return chat;
    }

    private void registerListeners() {
        Bukkit.getPluginManager().registerEvents(new CustomCommands(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerCommandPreprocess(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerJoin(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerLeave(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerLogin(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerMove(), this);
    }

    private void registerCommands() {
        this.getCommand("helpop").setExecutor(new Helpop());
        this.getCommand("feed").setExecutor(new Feed());
    }

    public void check() {

    }

    private void install() {
        if (!new File("plugins/Xentials/language").exists() && !new File("plugins/Xentials/perms.yml").exists()) {

        }
    }
}
