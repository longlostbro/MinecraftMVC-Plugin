package com.simplifiedgamingsolutions.minecraftmvc;


import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;
import java.util.logging.Logger;

/**
 * Created by longl on 2/16/2016.
 */
public class MinecraftMVC extends JavaPlugin {
    public Logger log;
    @Override
    public void onEnable() {
        super.onEnable();
        loadConfiguration();
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(this),this);
        log = this.getLogger();
        log.info("[MinecraftMVC] MinecraftMVC Enabled!");
    }

    @Override
    public void onLoad() {
        super.onLoad();
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        return super.onTabComplete(sender, command, alias, args);
    }

    @Override
    public void onDisable() {
        super.onDisable();
        HandlerList.unregisterAll();
        log.info("[MinecraftMVC] MinecraftMVC Disabled!");
    }

    public void loadConfiguration()
    {
        this.getConfig().addDefault("host.address", "localhost");
        this.getConfig().addDefault("host.port", 39640);
        this.getConfig().options().copyDefaults(true);
        this.saveConfig();
        log.info("[MinecraftMVC] Config loaded!");
    }
}
