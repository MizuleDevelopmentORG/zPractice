package com.mizuledevelopment.zpractice.listener;

import com.mizuledevelopment.zpractice.util.color.MessageType;
import com.mizuledevelopment.zpractice.util.color.TextUtil;
import com.mizuledevelopment.zpractice.util.serializer.ItemStackSerializer;
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
import org.bukkit.inventory.meta.ItemMeta;
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
            int key = event.getCurrentItem().getItemMeta().getPersistentDataContainer().get(this.plugin.getEditorKey(), PersistentDataType.INTEGER);
            Inventory inventory = Bukkit.createInventory(player, this.plugin.getConfiguration().getConfiguration().getInt("editor.inventory.size"),
                TextUtil.parse(this.plugin.getConfiguration().getConfiguration().getString("editor.inventory.title"),
                    MessageType.from(this.plugin.getConfiguration().getConfiguration().getString("editor.inventory.title"))));
            if (key == 1) {
                for (final String hotbar : this.plugin.getConfiguration().getConfiguration().getStringList("editor.hotbar")) {
                    if (this.plugin.getProfileManager().get(player.getUniqueId()).getItems1Bar() != null
                    && !this.plugin.getProfileManager().get(player.getUniqueId()).getItems1Bar().isEmpty()) {
                        for (final String string : this.plugin.getProfileManager().get(player.getUniqueId()).getItems1Bar()) {
                            if (string != null) {
                                inventory.setItem(Integer.parseInt(hotbar), ItemStackSerializer.deSerialize(string));
                            }
                        }
                    }


                    ItemStack itemStack = new ItemStack(Material.valueOf(this.plugin.getConfiguration().getConfiguration().getString("editor.hotbar-item")));
                    ItemMeta meta = itemStack.getItemMeta();
                    meta.displayName(MiniMessage.miniMessage().deserialize(this.plugin.getConfiguration().getConfiguration().getString("editor.hotbar-name")));
                    itemStack.setItemMeta(meta);
                    inventory.setItem(Integer.parseInt(hotbar), itemStack);
                }
            }

            player.openInventory(inventory);
        }
    }
}

