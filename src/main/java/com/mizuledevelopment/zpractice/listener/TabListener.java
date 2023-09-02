package com.mizuledevelopment.zpractice.listener;

import com.mizuledevelopment.zpractice.zPractice;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.Objects;

public class TabListener implements Listener {

    private final zPractice plugin;

    public TabListener(zPractice plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    @Deprecated
    public void onJoin(PlayerJoinEvent event) {
        //            case LEGACY -> LegacyComponentSerializer.legacyAmpersand().deserializeOr(input, Component.empty());
        /*
        Objects.requireNonNull(event.getPlayer().playerListHeader()).append(this.plugin.getTabManager().getHeader());
        Objects.requireNonNull(event.getPlayer().playerListFooter()).append(this.plugin.getTabManager().getFooter());
        */
    }
}
