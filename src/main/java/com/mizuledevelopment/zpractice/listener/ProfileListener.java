package com.mizuledevelopment.zpractice.listener;

import com.mizuledevelopment.zpractice.ZPractice;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class ProfileListener implements Listener {

    private final ZPractice plugin;

    public ProfileListener(ZPractice plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        if (!this.plugin.getProfileManager().containsProfile(event.getPlayer().getUniqueId().toString())) {
            this.plugin.getProfileManager().createProfile(event.getPlayer().getUniqueId().toString());
        }
    }
}
