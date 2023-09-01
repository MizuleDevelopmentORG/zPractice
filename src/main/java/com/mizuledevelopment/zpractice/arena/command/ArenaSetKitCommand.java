package com.mizuledevelopment.zpractice.arena.command;

import com.mizuledevelopment.zpractice.util.color.MessageType;
import com.mizuledevelopment.zpractice.util.color.TextUtil;
import com.mizuledevelopment.zpractice.util.command.Command;
import com.mizuledevelopment.zpractice.zPractice;
import net.kyori.adventure.text.Component;
import org.bukkit.command.CommandSender;

import java.util.Objects;

public class ArenaSetKitCommand extends Command {

    private final zPractice plugin;

    public ArenaSetKitCommand(zPractice plugin) {
        this.plugin = plugin;
    }

    @Override
    public void execute(final CommandSender sender, final String[] args) {
        if (args.length == 1) {
            sender.sendMessage(getUsage());
        } else {
            String arena = args[1];

            if (this.plugin.getArenaManager().getArenaByName(arena) == null) {
                sender.sendMessage(TextUtil.parse(this.plugin.getMessages().getConfiguration().getString("arena-not-exists")
                    ,MessageType.from(Objects.requireNonNull(this.plugin.getMessages().getConfiguration().getString("arena-not-exists")))));
                return;
            }

            if (args.length == 2) {
                sender.sendMessage(getUsage());
            } else {
                String kit = args[2];

                if (this.plugin.getKitManager().get(kit) == null && !kit.equalsIgnoreCase("custom")) {
                    sender.sendMessage(TextUtil.parse(this.plugin.getMessages().getConfiguration().getString("kit-not-exists")
                        ,MessageType.from(Objects.requireNonNull(this.plugin.getMessages().getConfiguration().getString("kit-not-exists")))));
                    return;
                }

                this.plugin.getArenaManager().getArenaByName(arena).setKitItems(this.plugin
                    .getKitManager().get(kit).getItems());
                sender.sendMessage(TextUtil.parse(this.plugin.getMessages().getConfiguration()
                    .getString("arena-kitset"),
                    MessageType.from(Objects.requireNonNull(this.plugin.getMessages().getConfiguration().getString("arena-kitset")))));
            }
        }
    }

    @Override
    public String getName() {
        return "setkit";
    }

    @Override
    public boolean allow() {
        return true;
    }

    @Override
    public Component getUsage() {
        return TextUtil.parse(this.plugin.getMessages().getConfiguration().getString("arena-setkit-usage"),
            MessageType.from(Objects.requireNonNull(this.plugin.getMessages().getConfiguration().getString("arena-setkit-usage"))));
    }
}
