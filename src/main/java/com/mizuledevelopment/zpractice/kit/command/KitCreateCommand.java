package com.mizuledevelopment.zpractice.kit.command;

import com.mizuledevelopment.zpractice.kit.Kit;
import com.mizuledevelopment.zpractice.util.color.MessageType;
import com.mizuledevelopment.zpractice.util.color.TextUtil;
import com.mizuledevelopment.zpractice.util.command.Command;
import com.mizuledevelopment.zpractice.zPractice;
import net.kyori.adventure.text.Component;
import org.bukkit.command.CommandSender;

import java.util.Objects;

public class KitCreateCommand extends Command {

    private final zPractice plugin;

    public KitCreateCommand(zPractice plugin) {
        this.plugin = plugin;
    }

    @Override
    public void execute(final CommandSender sender, final String[] args) {
        if (args.length == 1) {
            sender.sendMessage(getUsage());
        } else {
            String kit = args[1];

            if (this.plugin.getKitManager().get(kit) != null) {
                sender.sendMessage(TextUtil.parse(this.plugin.getMessages().getConfiguration().getString("kit-exists"),
                    MessageType.from(Objects.requireNonNull(this.plugin.getMessages().getConfiguration().getString("kit-exists")))));
                return;
            }

            this.plugin.getKitManager().getKits().add(new Kit(kit, null));
            sender.sendMessage(TextUtil.parse(this.plugin.getMessages().getConfiguration().getString("kit-created"),
                MessageType.from(Objects.requireNonNull(this.plugin.getMessages().getConfiguration().getString("kit-created")))));
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
        return TextUtil.parse(this.plugin.getMessages().getConfiguration().getString("kit-create-usage"),
            MessageType.from(Objects.requireNonNull(this.plugin.getMessages().getConfiguration().getString("kit-create-usage"))));
    }
}
