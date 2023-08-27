package com.mizuledevelopment.zpractice.listener;

import com.mizuledevelopment.zpractice.zPractice;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class ProfileListener implements Listener {

    private final zPractice plugin;

    public ProfileListener(zPractice plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        if (!this.plugin.getProfileManager().containsProfile(event.getPlayer().getUniqueId())) {
            this.plugin.getProfileManager().createProfile(event.getPlayer().getUniqueId().toString());
        }
    }
}
