package com.mizuledevelopment.zpractice.arena.command;

import com.mizuledevelopment.zpractice.util.color.MessageType;
import com.mizuledevelopment.zpractice.util.color.TextUtil;
import com.mizuledevelopment.zpractice.util.command.Command;
import com.mizuledevelopment.zpractice.zPractice;
import net.kyori.adventure.text.Component;
import org.bukkit.command.CommandSender;

import java.util.Objects;

public class ArenaDeleteCommand extends Command {

    private final zPractice plugin;

    public ArenaDeleteCommand(zPractice plugin) {
        this.plugin = plugin;
    }

    @Override
    public void execute(final CommandSender sender, final String[] args) {
        if (args.length == 1) {
            sender.sendMessage(getUsage());
        } else {
            String arenaName = args[1];

            if (this.plugin.getArenaManager().getArenaByName(arenaName) == null) {
                sender.sendMessage(TextUtil.parse(this.plugin.getMessages().getConfiguration().getString("arena-not-exists")
                    ,MessageType.from(Objects.requireNonNull(this.plugin.getMessages().getConfiguration().getString("arena-not-exists")))));
                return;
            }

            this.plugin.getArenaManager().getArenas().remove(this.plugin.getArenaManager().getArenaByName(arenaName));
            sender.sendMessage(TextUtil.parse(this.plugin.getMessages().getConfiguration().getString("arena-deleted")
                ,MessageType.from(Objects.requireNonNull(this.plugin.getMessages().getConfiguration().getString("arena-deleted")))));
        }
    }

    @Override
    public String getName() {
        return "delete";
    }

    @Override
    public boolean allow() {
        return true;
    }

    @Override
    public Component getUsage() {
        return TextUtil.parse(this.plugin.getMessages().getConfiguration().getString("arena-delete-usage"),
            MessageType.from(Objects.requireNonNull(this.plugin.getMessages().getConfiguration().getString("arena-delete-usage"))));
    }
}
