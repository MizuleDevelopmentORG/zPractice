package com.mizuledevelopment.zpractice.sign.listener;

import com.destroystokyo.paper.MaterialSetTag;
import com.mizuledevelopment.zpractice.zPractice;
import com.mizuledevelopment.zpractice.queue.Queue;
import com.mizuledevelopment.zpractice.sign.DataSign;
import org.bukkit.block.Block;
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
        final Block clickedBlock = event.getClickedBlock();
        if (clickedBlock == null) return;

        if (MaterialSetTag.SIGNS.isTagged(clickedBlock.getType())) {
            DataSign dataSign = this.plugin.getDataSignManager().getSignByLocation(clickedBlock.getLocation().toString());

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
