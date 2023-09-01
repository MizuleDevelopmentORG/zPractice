package com.mizuledevelopment.zpractice.listener;

import com.mizuledevelopment.zpractice.zPractice;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerListener implements Listener {

    private final zPractice plugin;

    public PlayerListener(zPractice plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {

    }
}
