package com.mizuledevelopment.sign.command;

import com.mizuledevelopment.util.command.Command;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Objects;

public class SignCreateCommand extends Command {
    @Override
    public void execute(final CommandSender sender, final String[] args) {
        if (!(sender instanceof Player player)) return;

        if (args.length == 1) {
            player.sendMessage(getUsage());
        } else {
            String arena = args[1];
            if (args.length == 2) {
                player.sendMessage(getUsage());
            } else {
                String kit = args[2];

                if (Objects.requireNonNull(player.getTargetBlock(5)).getType().name().contains("WALL_SIGN")) {
                    Bukkit.broadcastMessage("true");
                } else {
                    Bukkit.broadcastMessage("false");
                }
            }
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
    public String getUsage() {
        return "sign create arena kit";
    }
}
