package com.mizuledevelopment.zpractice.arena.command;

import com.mizuledevelopment.zpractice.util.color.MessageType;
import com.mizuledevelopment.zpractice.util.color.TextUtil;
import com.mizuledevelopment.zpractice.util.command.Command;
import com.mizuledevelopment.zpractice.util.serializer.LocationSerializer;
import com.mizuledevelopment.zpractice.zPractice;
import net.kyori.adventure.text.Component;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Objects;

public class ArenaSetLocationCommand extends Command {

    private final zPractice plugin;

    public ArenaSetLocationCommand(zPractice plugin) {
        this.plugin = plugin;
    }

    @Override
    public void execute(final CommandSender sender, final String[] args) {
        if (!(sender instanceof Player player)) return;

        if (args.length == 1) {
            sender.sendMessage(getUsage());
        } else {
            String arenaName = args[1];

            if (this.plugin.getArenaManager().getArenaByName(arenaName) == null) {
                sender.sendMessage(TextUtil.parse(this.plugin.getMessages().getConfiguration().getString("arena-not-exists")
                    ,MessageType.from(Objects.requireNonNull(this.plugin.getMessages().getConfiguration().getString("arena-not-exists")))));
                return;
            }

            if (args.length == 2) {
                sender.sendMessage(getUsage());
            } else {
                String position = args[2];

                if (position.equalsIgnoreCase("first")) {
                    this.plugin.getArenaManager().getArenaByName(arenaName).setLocationOne(LocationSerializer.serialize(player.getLocation()));
                    this.plugin.getArenaManager().getArenaByName(arenaName).setWorld(player.getLocation().getWorld().getName());
                    sender.sendMessage(TextUtil.parse(Objects.requireNonNull(this.plugin.getMessages().getConfiguration().getString("arena-location-set"))
                            .replace("%position%", "first")
                    ,MessageType.from(Objects.requireNonNull(this.plugin.getMessages().getConfiguration().getString("arena-location-set")))));
                } else if (position.equalsIgnoreCase("second")) {
                    this.plugin.getArenaManager().getArenaByName(arenaName).setLocationTwo(LocationSerializer.serialize(player.getLocation()));
                    this.plugin.getArenaManager().getArenaByName(arenaName).setWorld(player.getLocation().getWorld().getName());
                    sender.sendMessage(TextUtil.parse(Objects.requireNonNull(this.plugin.getMessages().getConfiguration().getString("arena-location-set"))
                            .replace("%position%", "second")
                        ,MessageType.from(Objects.requireNonNull(this.plugin.getMessages().getConfiguration().getString("arena-location-set")))));
                } else {
                    sender.sendMessage(getUsage());
                }
            }
        }
    }

    @Override
    public String getName() {
        return "setlocation";
    }

    @Override
    public boolean allow() {
        return true;
    }

    @Override
    public Component getUsage() {
        return TextUtil.parse(this.plugin.getMessages().getConfiguration().getString("arena-setlocation-usage"),
            MessageType.from(Objects.requireNonNull(this.plugin.getMessages().getConfiguration().getString("arena-setlocation-usage"))));
    }
}
