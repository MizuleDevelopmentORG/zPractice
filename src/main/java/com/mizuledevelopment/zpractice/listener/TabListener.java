package com.mizuledevelopment.zpractice.listener;

import com.mizuledevelopment.zpractice.zPractice;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class TabListener implements Listener {

    private final zPractice plugin;

    public TabListener(zPractice plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    @Deprecated
    public void onJoin(PlayerJoinEvent event) {
        event.getPlayer().setPlayerListHeaderFooter(LegacyComponentSerializer.legacyAmpersand().serialize(this.plugin.getTabManager().getHeader()),
            LegacyComponentSerializer.legacyAmpersand().serialize(this.plugin.getTabManager().getFooter()));
    }
}
