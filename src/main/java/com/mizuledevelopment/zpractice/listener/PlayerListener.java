package com.mizuledevelopment.zpractice.listener;

import com.mizuledevelopment.zpractice.util.items.Items;
import com.mizuledevelopment.zpractice.zPractice;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.Objects;

public class PlayerListener implements Listener {

    private final zPractice plugin;

    public PlayerListener(zPractice plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {

        event.getPlayer().teleport(new Location(Bukkit.getWorld(Objects.requireNonNull(this.plugin.getConfiguration().getConfiguration().getString("spawn.world"))),
            this.plugin.getConfiguration().getConfiguration().getInt("spawn.x"),
            this.plugin.getConfiguration().getConfiguration().getInt("spawn.y"),
            this.plugin.getConfiguration().getConfiguration().getInt("spawn.z")));

        event.getPlayer().getInventory().setItem(
            this.plugin.getConfiguration().getConfiguration().getInt("selector.slot"), Items.SELECTOR(this.plugin));
        event.getPlayer().getInventory().setItem(
            this.plugin.getConfiguration().getConfiguration().getInt("kit.slot"), Items.KIT(this.plugin));
        event.getPlayer().getInventory().setItem(
            this.plugin.getConfiguration().getConfiguration().getInt("statistics.slot"), Items.STATS(this.plugin));
    }
}
