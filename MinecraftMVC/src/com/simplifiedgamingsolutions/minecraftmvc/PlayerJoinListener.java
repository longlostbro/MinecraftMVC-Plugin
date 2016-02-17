package com.simplifiedgamingsolutions.minecraftmvc;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * Created by longl on 2/16/2016.
 */
public class PlayerJoinListener implements Listener {
    private Client client;
    private MinecraftMVC plugin;

    public PlayerJoinListener(MinecraftMVC plugin)
    {
        this.plugin = plugin;
        String host = plugin.getConfig().getString("host.address");
        int port = plugin.getConfig().getInt("host.port");
        client = new Client(host,port);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event)
    {
        Player player = event.getPlayer();
        try {
            client.PlayerJoined(player.getDisplayName());
        } catch (Exception e) {
            plugin.log.severe("[MinecraftMVC] failed to notify MVC server on player join.");
        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        // Called when a player leaves a server
        Player player = event.getPlayer();
        String quitMessage = event.getQuitMessage();
        try {
            client.PlayerLeft(player.getDisplayName());
        } catch (Exception e) {
            plugin.log.severe("[MinecraftMVC] failed to notify MVC server on player quit.");
        }
    }
}
