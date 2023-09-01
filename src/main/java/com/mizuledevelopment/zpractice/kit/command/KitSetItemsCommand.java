package com.mizuledevelopment.zpractice.kit.command;

import com.mizuledevelopment.zpractice.util.color.MessageType;
import com.mizuledevelopment.zpractice.util.color.TextUtil;
import com.mizuledevelopment.zpractice.util.command.Command;
import com.mizuledevelopment.zpractice.util.serializer.ItemStackSerializer;
import com.mizuledevelopment.zpractice.zPractice;
import net.kyori.adventure.text.Component;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class KitSetItemsCommand extends Command {

    private final zPractice plugin;

    public KitSetItemsCommand(zPractice plugin) {
        this.plugin = plugin;
    }

    @Override
    public void execute(final CommandSender sender, final String[] args) {
        if (!(sender instanceof Player player)) return;

        if (args.length == 1) {
            player.sendMessage(getUsage());
        } else {
            String kit = args[1];

            if (this.plugin.getKitManager().get(kit) == null) {
                player.sendMessage(TextUtil.parse(this.plugin.getMessages().getConfiguration().getString("kit-not-exists"),
                    MessageType.from(Objects.requireNonNull(this.plugin.getMessages().getConfiguration().getString("kit-not-exists")))));
                return;
            }
            List<String> items = new ArrayList<>();
            for (ItemStack itemStack : player.getInventory().getContents()) {
                items.add(ItemStackSerializer.serialize(itemStack));
            }
            this.plugin.getKitManager().get(kit).setItems(items);
            player.sendMessage(TextUtil.parse(this.plugin.getMessages().getConfiguration().getString("kit-setitems"),
                MessageType.from(Objects.requireNonNull(this.plugin.getMessages().getConfiguration().getString("kit-setitems")))));
        }
    }

    @Override
    public String getName() {
        return "setitems";
    }

    @Override
    public boolean allow() {
        return true;
    }

    @Override
    public Component getUsage() {
        return TextUtil.parse(this.plugin.getMessages().getConfiguration().getString("kit-setitems-usage"),
            MessageType.from(Objects.requireNonNull(this.plugin.getMessages().getConfiguration().getString("kit-setitems-usage"))));
    }
}
