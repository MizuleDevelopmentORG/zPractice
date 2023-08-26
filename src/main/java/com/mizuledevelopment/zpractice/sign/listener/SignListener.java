package com.mizuledevelopment.zpractice.sign.listener;

import com.mizuledevelopment.zpractice.zPractice;
import com.mizuledevelopment.zpractice.queue.Queue;
import com.mizuledevelopment.zpractice.sign.DataSign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.*;

public class SignListener implements Listener {

    private final zPractice plugin;

    public SignListener(zPractice plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onClick(PlayerInteractEvent event) {
        if (event.getClickedBlock() == null) return;
        if (Objects.requireNonNull(event.getClickedBlock()).getType().name().contains("WALL_SIGN")) {
            DataSign dataSign = this.plugin.getDataSignManager().getSignByLocation(event.getClickedBlock().getLocation().toString());

            if (dataSign != null) {
                if (!this.plugin.getQueueManager().has(dataSign.getName())) {
                    this.plugin.getQueueManager().getQueues().add(new Queue(dataSign.getName(), dataSign.getArena(), dataSign.getKit(), new HashSet<>(Collections.singletonList(event.getPlayer().getUniqueId())), dataSign.getMaxPlayers()));
                } else {
                    this.plugin.getQueueManager().get(dataSign.getName()).getPlayers().add(event.getPlayer().getUniqueId());

                    if (this.plugin.getQueueManager().get(dataSign.getName()).getPlayers().size() == dataSign.getMaxPlayers()) {
                        // todo move players
                    }
                }
            }
        }
    }
}
