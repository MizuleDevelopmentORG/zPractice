package com.mizuledevelopment.zpractice.sign.command;

import com.mizuledevelopment.zpractice.ZPractice;
import com.mizuledevelopment.zpractice.util.color.MessageType;
import com.mizuledevelopment.zpractice.util.color.TextUtil;
import com.mizuledevelopment.zpractice.util.command.Command;
import com.mizuledevelopment.zpractice.util.serializer.LocationSerializer;
import net.kyori.adventure.text.Component;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Objects;

public class SignDeleteCommand extends Command {

    private final ZPractice plugin;

    public SignDeleteCommand(ZPractice plugin) {
        this.plugin = plugin;
    }

    @Override
    public void execute(final CommandSender sender, final String[] args) {
        if (!(sender instanceof Player player)) return;

        if (this.plugin.getDataSignManager().getSignByLocation
                (LocationSerializer.serialize(Objects.requireNonNull(player.getTargetBlock(5)).getLocation())) == null) {
            player.sendMessage(TextUtil.parse(this.plugin.getMessages().getConfiguration().getString("sign-not-exists"),
                    MessageType.from(Objects.requireNonNull(this.plugin.getMessages().getConfiguration().getString("sign-not-exists")))));
            return;
        }

        this.plugin.getDataSignManager().remove(this.plugin.getDataSignManager().getSignByLocation(LocationSerializer
                .serialize(Objects.requireNonNull(player.getTargetBlock(5)).getLocation())));
        player.sendMessage(TextUtil.parse(this.plugin.getMessages().getConfiguration().getString("sign-deleted"),
                MessageType.from(Objects.requireNonNull(this.plugin.getMessages().getConfiguration().getString("sign-deleted")))));
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
        return TextUtil.parse(this.plugin.getMessages().getConfiguration().getString("sign-delete-usage"),
                MessageType.from(Objects.requireNonNull(this.plugin.getMessages().getConfiguration().getString("sign-delete-usage"))));
    }
}
