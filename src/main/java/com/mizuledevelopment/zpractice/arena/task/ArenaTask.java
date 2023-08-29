package com.mizuledevelopment.zpractice.arena.task;

import com.mizuledevelopment.zpractice.arena.Arena;
import com.mizuledevelopment.zpractice.zPractice;
import org.bukkit.scheduler.BukkitRunnable;

public class ArenaTask extends BukkitRunnable {

    private final zPractice plugin;

    public ArenaTask(zPractice plugin){
        this.plugin = plugin;

        this.runTaskLater(this.plugin, 20L * this.plugin.getConfiguration().getConfiguration().getInt("starting-timer"));
    }

    @Override
    public void run() {

        for (Arena arena : this.plugin.getArenaManager().getStartingArenas()) {

        }
    }
}
