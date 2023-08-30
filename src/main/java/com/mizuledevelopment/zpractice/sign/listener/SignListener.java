package com.mizuledevelopment.zpractice.sign.listener;

import com.destroystokyo.paper.MaterialSetTag;
import com.mizuledevelopment.zpractice.arena.Arena;
import com.mizuledevelopment.zpractice.arena.ArenaState;
import com.mizuledevelopment.zpractice.arena.handler.ArenaHandler;
import com.mizuledevelopment.zpractice.util.LazyLocation;
import com.mizuledevelopment.zpractice.util.color.MessageType;
import com.mizuledevelopment.zpractice.util.color.TextUtil;
import com.mizuledevelopment.zpractice.util.config.Config;
import com.mizuledevelopment.zpractice.zPractice;
import com.mizuledevelopment.zpractice.queue.Queue;
import com.mizuledevelopment.zpractice.sign.DataSign;
import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import javax.swing.*;
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
            DataSign dataSign = this.plugin.getDataSignManager().getSignByLocation(LazyLocation.fromLocation(clickedBlock.getLocation()));

            if (dataSign != null) {
                if (this.plugin.getQueueManager().containsPlayer(event.getPlayer().getUniqueId())) {
                    event.getPlayer().sendMessage(TextUtil.parse(this.plugin.getMessages()
                        .getConfiguration().getString("queue-in-queue"), MessageType.from(Objects.requireNonNull(this.plugin.getConfiguration()
                        .getConfiguration().getString("queue-in-queue")))));
                    return;
                }
                if (!this.plugin.getQueueManager().has(dataSign.getName())) {
                    this.plugin.getQueueManager().getQueues().add(new Queue(dataSign.getName(), dataSign.getArena(), dataSign.getKit(), new ArrayList<>(Collections.singletonList(event.getPlayer().getUniqueId())), dataSign.getMaxPlayers()));
                    event.getPlayer().sendMessage(TextUtil.parse(Objects.requireNonNull(this.plugin.getMessages()
                            .getConfiguration().getString("queue-joined"))
                        .replace("%arena%", dataSign.getArena())
                        .replace("%kit%", dataSign.getKit())
                        .replace("%name%", dataSign.getName())
                        .replace("%players%", String.valueOf(this.plugin.getQueueManager().get(dataSign.getName()).getPlayers().size()))
                        .replace("%max%", String.valueOf(dataSign.getMaxPlayers())), MessageType.from(Objects.requireNonNull(this.plugin.getMessages()
                        .getConfiguration().getString("queue-joined")))));
                } else {
                    this.plugin.getQueueManager().get(dataSign.getName()).getPlayers().add(event.getPlayer().getUniqueId());
                    event.getPlayer().sendMessage(TextUtil.parse(Objects.requireNonNull(this.plugin.getMessages()
                            .getConfiguration().getString("queue-joined"))
                        .replace("%arena%", dataSign.getArena())
                        .replace("%kit%", dataSign.getKit())
                        .replace("%name%", dataSign.getName())
                        .replace("%players%", String.valueOf(this.plugin.getQueueManager().get(dataSign.getName()).getPlayers().size()))
                        .replace("%max%", String.valueOf(dataSign.getMaxPlayers())), MessageType.from(Objects.requireNonNull(this.plugin.getMessages()
                        .getConfiguration().getString("queue-joined")))));
                    if (this.plugin.getQueueManager().get(dataSign.getName()).getPlayers().size() == dataSign.getMaxPlayers()) {
                        Queue queue = this.plugin.getQueueManager().get(dataSign.getName());
                        if (this.plugin.getArenaManager().getArenaByName(dataSign.getName()).getState() == ArenaState.WAITING) {
                            ArenaHandler arenaHandler = new ArenaHandler(this.plugin.getArenaManager().getArenaByName(dataSign.getArena()), this.plugin);

                            arenaHandler.addPlayers(queue);
                            arenaHandler.sendPlayers();
                            arenaHandler.applyKits();
                            arenaHandler.start();
                        }
                    }
                }
            }
        }
    }
}

