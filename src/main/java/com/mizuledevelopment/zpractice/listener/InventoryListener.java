package com.mizuledevelopment.zpractice.listener;

import com.mizuledevelopment.zpractice.zPractice;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.*;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

import java.util.Objects;

public class InventoryListener implements Listener {

    private final zPractice plugin;

    public InventoryListener(zPractice plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        if (event.getClickedInventory() == null) return;
        if (Objects.requireNonNull(event.getClickedInventory()).contains(Material.valueOf(this.plugin.getConfiguration().getConfiguration().getString("selector.item")))
        && event.getClickedInventory().contains(Material.valueOf(this.plugin.getConfiguration().getConfiguration().getString("kit.item")))
        && event.getClickedInventory().contains(Material.valueOf(this.plugin.getConfiguration().getConfiguration().getString("statistics.item")))) {
            Objects.requireNonNull(event.getCursor()).setType(Material.AIR);
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onInventory(InventoryClickEvent event) {

        if (MiniMessage.miniMessage().serialize(event.getView().title()).equalsIgnoreCase(this.plugin.getConfiguration().getConfiguration().getString("inventory.selector.title"))
            || MiniMessage.miniMessage().serialize(event.getView().title()).equalsIgnoreCase(this.plugin.getConfiguration().getConfiguration().getString("inventory.statistics.title"))
            || MiniMessage.miniMessage().serialize(event.getView().title()).equalsIgnoreCase(this.plugin.getConfiguration().getConfiguration().getString("inventory.kit.title"))) {
            event.setCancelled(true);
        }

        Player player = (Player) event.getWhoClicked();

        for (final String items : Objects.requireNonNull(this.plugin.getConfiguration().getConfiguration().getConfigurationSection("inventory.selector.items")).getKeys(false)) {
            if (event.getCurrentItem() == null || event.getCurrentItem().getItemMeta() == null || event.getCurrentItem().getItemMeta().displayName() == null)
                return;
            if (MiniMessage.miniMessage().serialize(Objects.requireNonNull(event.getCurrentItem().getItemMeta().displayName()))
                .equalsIgnoreCase(this.plugin.getConfiguration().getConfiguration().getString("inventory.selector.items." + items + ".name"))) {

                player.teleport(new Location(Bukkit.getWorld(Objects.requireNonNull(
                    this.plugin.getConfiguration().getConfiguration().getString("inventory.selector.items." + items + ".spawn-world"))),
                    this.plugin.getConfiguration().getConfiguration().getInt("inventory.selector.items." + items + ".spawn-x"),
                    this.plugin.getConfiguration().getConfiguration().getInt("inventory.selector.items." + items + ".spawn-y"),
                    this.plugin.getConfiguration().getConfiguration().getInt("inventory.selector.items." + items + ".spawn-z")));
            }
        }

        if (event.getCurrentItem() == null || event.getCurrentItem().getItemMeta() == null || event.getCurrentItem().getItemMeta().displayName() == null) return;
        if (event.getCurrentItem().getItemMeta().getPersistentDataContainer().has(this.plugin.getKitKey())) {
            int value = event.getCurrentItem().getItemMeta().getPersistentDataContainer().get(this.plugin.getKitKey(), PersistentDataType.INTEGER);

            if (value == 1) {
                this.plugin.getProfileManager().get(player.getUniqueId()).setSelectedKit(1);
            } else if (value == 2) {
                this.plugin.getProfileManager().get(player.getUniqueId()).setSelectedKit(2);
            } else if (value == 3) {
                this.plugin.getProfileManager().get(player.getUniqueId()).setSelectedKit(3);
            }
            player.closeInventory();
        } else if (event.getCurrentItem().getItemMeta().getPersistentDataContainer().has(this.plugin.getEditorKey())) {

        }
    }
}

