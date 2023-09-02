package com.mizuledevelopment.zpractice.scoreboard;

import com.mizuledevelopment.zpractice.util.color.MessageType;
import com.mizuledevelopment.zpractice.util.color.TextUtil;
import com.mizuledevelopment.zpractice.zPractice;
import io.github.thatkawaiisam.assemble.AssembleAdapter;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BoardManager implements AssembleAdapter {

    private final zPractice plugin;

    public BoardManager(zPractice plugin) {
        this.plugin = plugin;
    }

    @Override
    public String getTitle(final Player player) {

        return LegacyComponentSerializer.legacyAmpersand().serialize(TextUtil.parse(this.plugin.getConfiguration().getConfiguration().getString("scoreboard.title"),
            MessageType.from(Objects.requireNonNull(this.plugin.getConfiguration().getConfiguration().getString("scoreboard.title")))));
    }

    @Override
    public List<String> getLines(final Player player) {
        List<String> lines = new ArrayList<>();
        List<String> toReturn = new ArrayList<>();

        lines.add("<red>hey");

        return lines;
    }
}
