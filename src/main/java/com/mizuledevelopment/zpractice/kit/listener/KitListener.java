package com.mizuledevelopment.zpractice.kit.listener;

import com.mizuledevelopment.zpractice.zPractice;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.Objects;

public class KitListener implements Listener {

    private final zPractice plugin;

    public KitListener(zPractice plugin) {
        this.plugin = plugin;
    }
}
