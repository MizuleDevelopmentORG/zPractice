package com.mizuledevelopment.zpractice.sign.command;

import com.mizuledevelopment.zpractice.sign.DataSign;
import com.mizuledevelopment.zpractice.util.LazyLocation;
import com.mizuledevelopment.zpractice.util.color.MessageType;
import com.mizuledevelopment.zpractice.util.color.TextUtil;
import com.mizuledevelopment.zpractice.util.command.Command;
import com.mizuledevelopment.zpractice.util.serializer.LocationSerializer;
import com.mizuledevelopment.zpractice.zPractice;
import net.kyori.adventure.text.Component;
import org.bukkit.block.Sign;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Objects;

public class SignCreateCommand extends Command {

    private final zPractice plugin;

    public SignCreateCommand(final zPractice plugin) {
        this.plugin = plugin;
    }

    @Override
    public void execute(final CommandSender sender, final String[] args) {
        if (!(sender instanceof final Player player)) return;

        if (args.length == 1) {
            player.sendMessage(getUsage());
        } else {
            final String name = args[1];

            if (this.plugin.getDataSignManager().getSignByName(name) != null) {
                player.sendMessage(TextUtil.parse(this.plugin.getMessages().getConfiguration().getString("sign-name-exists"),
                    MessageType.from(Objects.requireNonNull(this.plugin.getMessages().getConfiguration().getString("sign-name-exists")))));
                return;
            }

            if (args.length == 2) {
                player.sendMessage(getUsage());
            } else {
                final String arena = args[2];
                if (args.length == 3) {
                    player.sendMessage(getUsage());
                } else {
                    final String kit = args[3];
                    if (args.length == 4) {
                        player.sendMessage(getUsage());
                    } else {
                        final int maxPlayers = Integer.parseInt(args[4]);

                        if (Objects.requireNonNull(player.getTargetBlock(4)).getType().name().contains("WALL_SIGN")) {
                            if (this.plugin.getDataSignManager().getSignByLocation
                                (LazyLocation.fromLocation(Objects.requireNonNull(player.getTargetBlock(5)).getLocation())) == null) {
                                this.plugin.getDataSignManager().getDataSigns()
                                    .add(new DataSign(name, arena, kit, LazyLocation.fromLocation(Objects.requireNonNull(player.getTargetBlock(5)).getLocation()), maxPlayers));

                                final Sign sign = (Sign) player.getWorld().getBlockAt(Objects.requireNonNull(player.getTargetBlock(5)).getLocation()).getState();

                                int i = 0;
                                for (final String line : this.plugin.getConfiguration().getConfiguration().getStringList("sign-format")) {
                                    if (!line.isEmpty()) {
                                        sign.line(i, TextUtil.parse(line.replace("%arena%", arena).replace("%kit%", kit).replace("%name%", name), MessageType.from(line)));
                                        sign.update();
                                        i++;
                                    }
                                }

                                player.sendMessage(TextUtil.parse(this.plugin.getMessages().getConfiguration().getString("sign-created"),
                                    MessageType.from(Objects.requireNonNull(this.plugin.getMessages().getConfiguration().getString("sign-created")))));
                            } else {
                                player.sendMessage(TextUtil.parse(this.plugin.getMessages().getConfiguration().getString("sign-location-exists"),
                                    MessageType.from(Objects.requireNonNull(this.plugin.getMessages().getConfiguration().getString("sign-location-exists")))));
                            }
                        }
                    }
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
    public Component getUsage() {
        return TextUtil.parse(this.plugin.getMessages().getConfiguration().getString("sign-create-usage"),
            MessageType.from(Objects.requireNonNull(this.plugin.getMessages().getConfiguration().getString("sign-create-usage"))));
    }
}
