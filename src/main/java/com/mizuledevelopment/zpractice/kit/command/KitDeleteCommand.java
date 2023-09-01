package com.mizuledevelopment.zpractice.kit.command;

import com.mizuledevelopment.zpractice.util.color.MessageType;
import com.mizuledevelopment.zpractice.util.color.TextUtil;
import com.mizuledevelopment.zpractice.util.command.Command;
import com.mizuledevelopment.zpractice.zPractice;
import net.kyori.adventure.text.Component;
import org.bukkit.command.CommandSender;

import java.util.Objects;

public class KitDeleteCommand extends Command {

    private final zPractice plugin;

    public KitDeleteCommand(zPractice plugin){
        this.plugin = plugin;
    }

    @Override
    public void execute(final CommandSender sender, final String[] args) {
        if (args.length == 1) {
            sender.sendMessage(getUsage());
        } else {
            String kit = args[1];

            if (this.plugin.getKitManager().get(kit) == null) {
                sender.sendMessage(TextUtil.parse(this.plugin.getMessages().getConfiguration().getString("kit-not-exists"),
                    MessageType.from(Objects.requireNonNull(this.plugin.getMessages().getConfiguration().getString("kit-not-exists")))));
                return;
            }

            this.plugin.getKitManager().getKits().remove(this.plugin.getKitManager().get(kit));
            sender.sendMessage(TextUtil.parse(this.plugin.getMessages().getConfiguration().getString("kit-deleted"),
                MessageType.from(Objects.requireNonNull(this.plugin.getMessages().getConfiguration().getString("kit-deleted")))));
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
        return TextUtil.parse(this.plugin.getMessages().getConfiguration().getString("kit-delete-usage"),
            MessageType.from(Objects.requireNonNull(this.plugin.getMessages().getConfiguration().getString("kit-delete-usage"))));
    }
}
