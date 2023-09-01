package com.mizuledevelopment.zpractice.arena.command;

import com.mizuledevelopment.zpractice.arena.Arena;
import com.mizuledevelopment.zpractice.util.color.MessageType;
import com.mizuledevelopment.zpractice.util.color.TextUtil;
import com.mizuledevelopment.zpractice.util.command.Command;
import com.mizuledevelopment.zpractice.zPractice;
import net.kyori.adventure.text.Component;
import org.bukkit.command.CommandSender;

import java.util.Objects;

public class ArenaCreateCommand extends Command {

    private final zPractice plugin;

    public ArenaCreateCommand(zPractice plugin) {
        this.plugin = plugin;
    }

    @Override
    public void execute(final CommandSender sender, final String[] args) {
        if (args.length == 1) {
            sender.sendMessage(getUsage());
        } else {
            String arenaName = args[1];

            if (this.plugin.getArenaManager().getArenaByName(arenaName) != null) {
                sender.sendMessage(TextUtil.parse(this.plugin.getMessages().getConfiguration().getString("arena-exists")
                ,MessageType.from(Objects.requireNonNull(this.plugin.getMessages().getConfiguration().getString("arena-exists")))));
                return;
            }

            this.plugin.getArenaManager().getArenas().add(new Arena(this.plugin, arenaName,
                null, null, null, null));
            sender.sendMessage(TextUtil.parse(this.plugin.getMessages().getConfiguration().getString("arena-created")
            ,MessageType.from(Objects.requireNonNull(this.plugin.getMessages().getConfiguration().getString("arena-created")))));
        }
    }

    @Override
    public String getName() {
        return "create";
    }

    @Override
    public boolean allow() {
        return true;
    }

    @Override
    public Component getUsage() {
        return TextUtil.parse(this.plugin.getMessages().getConfiguration().getString("arena-create-usage"),
            MessageType.from(Objects.requireNonNull(this.plugin.getMessages().getConfiguration().getString("arena-create-usage"))));
    }
}
