package com.mizuledevelopment.zpractice.listener;

import com.mizuledevelopment.zpractice.util.color.MessageType;
import com.mizuledevelopment.zpractice.util.color.TextUtil;
import com.mizuledevelopment.zpractice.zPractice;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ItemListener implements Listener {

    private final zPractice plugin;

    public ItemListener(zPractice plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onClick(PlayerInteractEvent event) {
        if (event.getPlayer().getInventory().getItemInMainHand() == null
            || event.getPlayer().getInventory().getItemInMainHand().getItemMeta() == null
            || event.getPlayer().getInventory().getItemInMainHand().getItemMeta().displayName() == null) return;

        if (event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            if (event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getPersistentDataContainer().has(this.plugin.getNamespacedKey())) {
                String name = event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getPersistentDataContainer().get(this.plugin.getNamespacedKey(), PersistentDataType.STRING);
                if (name == null) return;

                if (name.equalsIgnoreCase("selector")) {
                    Inventory inventory = Bukkit.createInventory(event.getPlayer(), this.plugin.getConfiguration().getConfiguration().getInt("inventory.selector.size"),
                        TextUtil.parse(this.plugin.getConfiguration().getConfiguration().getString("inventory.selector.title"), MessageType.from(Objects.requireNonNull(this.plugin.getConfiguration().getConfiguration().getString("inventory.selector.title")))));
                    for (final String items : Objects.requireNonNull(this.plugin.getConfiguration().getConfiguration().getConfigurationSection("inventory.selector.items")).getKeys(false)) {
                        ItemStack itemStack = new ItemStack(Material.valueOf(this.plugin.getConfiguration().getConfiguration().getString("inventory.selector.items." + items + ".item")));
                        itemStack.setAmount(this.plugin.getConfiguration().getConfiguration().getInt("inventory.selector.items." + items + ".amount"));
                        this.plugin.getConfiguration().getConfiguration().getStringList("inventory.selector.items." + items + ".enchantments")
                            .forEach(enchantment -> itemStack.addUnsafeEnchantment(Objects.requireNonNull(Enchantment.getByName(enchantment.split(":")[0])), Integer.parseInt(enchantment.split(":")[1])));
                        ItemMeta meta = itemStack.getItemMeta();
                        meta.displayName(TextUtil.parse(this.plugin.getConfiguration().getConfiguration().getString("inventory.selector.items." + items + ".name")
                            ,MessageType.from(Objects.requireNonNull(this.plugin.getConfiguration().getConfiguration().getString("inventory.selector.items." + items + ".name")))));
                        List<Component> lore = new ArrayList<>();
                        for (String string : this.plugin.getConfiguration().getConfiguration().getStringList("inventory.selector.items." + items + ".lore")) {
                            if (string.isEmpty()) {
                                lore.add(Component.text(" "));
                            } else {
                                lore.add(TextUtil.parse(string, MessageType.from(string)));
                            }
                        }
                        meta.lore(lore);
                        itemStack.setItemMeta(meta);
                        inventory.setItem(this.plugin.getConfiguration().getConfiguration().getInt("inventory.selector.items." + items + ".slot"), itemStack);
                    }

                    event.getPlayer().openInventory(inventory);
                } else if (name.equalsIgnoreCase("kit")) {
                    Inventory inventory = Bukkit.createInventory(event.getPlayer(), this.plugin.getConfiguration().getConfiguration().getInt("inventory.kit.size"),
                        TextUtil.parse(this.plugin.getConfiguration().getConfiguration().getString("inventory.kit.title"), MessageType.from(Objects.requireNonNull(this.plugin.getConfiguration().getConfiguration().getString("inventory.selector.title")))));
                    for (final String items : Objects.requireNonNull(this.plugin.getConfiguration().getConfiguration().getConfigurationSection("inventory.kit.items")).getKeys(false)) {
                        if (!Objects.requireNonNull(this.plugin.getConfiguration().getConfiguration().getString("inventory.kit.items." + items + ".item")).equalsIgnoreCase("null")) {
                            ItemStack itemStack = new ItemStack(Material.valueOf(this.plugin.getConfiguration().getConfiguration().getString("inventory.kit.items." + items + ".item")));
                            itemStack.setAmount(this.plugin.getConfiguration().getConfiguration().getInt("inventory.kit.items." + items + ".amount"));
                            this.plugin.getConfiguration().getConfiguration().getStringList("inventory.kit.items." + items + ".enchantments")
                                .forEach(enchantment -> itemStack.addUnsafeEnchantment(Objects.requireNonNull(Enchantment.getByName(enchantment.split(":")[0])), Integer.parseInt(enchantment.split(":")[1])));
                            ItemMeta meta = itemStack.getItemMeta();
                            meta.displayName(TextUtil.parse(this.plugin.getConfiguration().getConfiguration().getString("inventory.kit.items." + items + ".name")
                                , MessageType.from(Objects.requireNonNull(this.plugin.getConfiguration().getConfiguration().getString("inventory.kit.items." + items + ".name")))));
                            List<Component> lore = new ArrayList<>();
                            for (String string : this.plugin.getConfiguration().getConfiguration().getStringList("inventory.kit.items." + items + ".lore")) {
                                if (string.isEmpty()) {
                                    lore.add(Component.text(" "));
                                } else {
                                    lore.add(TextUtil.parse(string, MessageType.from(string)));
                                }
                            }
                            meta.lore(lore);
                            itemStack.setItemMeta(meta);
                            inventory.setItem(this.plugin.getConfiguration().getConfiguration().getInt("inventory.kit.items." + items + ".slot"), itemStack);
                        } else {
                            if (this.plugin.getProfileManager().get(event.getPlayer().getUniqueId()).getSelectedKit() == 0) {
                                ItemStack itemStack = new ItemStack(Material.valueOf(this.plugin.getConfiguration().getConfiguration().getString("selection.unselected-item")));
                                itemStack.setAmount(this.plugin.getConfiguration().getConfiguration().getInt("inventory.kit.items." + items + ".amount"));
                                this.plugin.getConfiguration().getConfiguration().getStringList("inventory.kit.items." + items + ".enchantments")
                                    .forEach(enchantment -> itemStack.addUnsafeEnchantment(Objects.requireNonNull(Enchantment.getByName(enchantment.split(":")[0])), Integer.parseInt(enchantment.split(":")[1])));
                                ItemMeta meta = itemStack.getItemMeta();
                                meta.displayName(TextUtil.parse(this.plugin.getConfiguration().getConfiguration().getString("selection.unselected-name")
                                    , MessageType.from(Objects.requireNonNull(this.plugin.getConfiguration().getConfiguration().getString("selection.unselected-name")))));
                                List<Component> lore = new ArrayList<>();
                                for (String string : this.plugin.getConfiguration().getConfiguration().getStringList("inventory.kit.items." + items + ".lore")) {
                                    if (string.isEmpty()) {
                                        lore.add(Component.text(" "));
                                    } else {
                                        lore.add(TextUtil.parse(string, MessageType.from(string)));
                                    }
                                }
                                meta.lore(lore);
                                itemStack.setItemMeta(meta);
                                inventory.setItem(this.plugin.getConfiguration().getConfiguration().getInt("inventory.kit.items." + items + ".slot"), itemStack);
                            } else if (this.plugin.getProfileManager().get(event.getPlayer().getUniqueId()).getSelectedKit() == 1) {
                                if (this.plugin.getConfiguration().getConfiguration().getInt("inventory.kit.items." + items + ".kit") != 0) {
                                    if (this.plugin.getConfiguration().getConfiguration().getInt("inventory.kit.items." + items + ".kit") == 1) {
                                        ItemStack itemStack = new ItemStack(Material.valueOf(this.plugin.getConfiguration().getConfiguration().getString("selection.selected-item")));
                                        itemStack.setAmount(this.plugin.getConfiguration().getConfiguration().getInt("inventory.kit.items." + items + ".amount"));
                                        this.plugin.getConfiguration().getConfiguration().getStringList("inventory.kit.items." + items + ".enchantments")
                                            .forEach(enchantment -> itemStack.addUnsafeEnchantment(Objects.requireNonNull(Enchantment.getByName(enchantment.split(":")[0])), Integer.parseInt(enchantment.split(":")[1])));
                                        ItemMeta meta = itemStack.getItemMeta();
                                        meta.displayName(TextUtil.parse(this.plugin.getConfiguration().getConfiguration().getString("selection.selected-name")
                                            , MessageType.from(Objects.requireNonNull(this.plugin.getConfiguration().getConfiguration().getString("selection.selected-name")))));
                                        List<Component> lore = new ArrayList<>();
                                        for (String string : this.plugin.getConfiguration().getConfiguration().getStringList("inventory.kit.items." + items + ".lore")) {
                                            if (string.isEmpty()) {
                                                lore.add(Component.text(" "));
                                            } else {
                                                lore.add(TextUtil.parse(string, MessageType.from(string)));
                                            }
                                        }
                                        meta.lore(lore);
                                        itemStack.setItemMeta(meta);
                                        inventory.setItem(this.plugin.getConfiguration().getConfiguration().getInt("inventory.kit.items." + items + ".slot"), itemStack);
                                    } else {
                                        ItemStack itemStack = new ItemStack(Material.valueOf(this.plugin.getConfiguration().getConfiguration().getString("selection.unselected-item")));
                                        itemStack.setAmount(this.plugin.getConfiguration().getConfiguration().getInt("inventory.kit.items." + items + ".amount"));
                                        this.plugin.getConfiguration().getConfiguration().getStringList("inventory.kit.items." + items + ".enchantments")
                                            .forEach(enchantment -> itemStack.addUnsafeEnchantment(Objects.requireNonNull(Enchantment.getByName(enchantment.split(":")[0])), Integer.parseInt(enchantment.split(":")[1])));
                                        ItemMeta meta = itemStack.getItemMeta();
                                        meta.displayName(TextUtil.parse(this.plugin.getConfiguration().getConfiguration().getString("selection.unselected-name")
                                            , MessageType.from(Objects.requireNonNull(this.plugin.getConfiguration().getConfiguration().getString("selection.unselected-name")))));
                                        List<Component> lore = new ArrayList<>();
                                        for (String string : this.plugin.getConfiguration().getConfiguration().getStringList("inventory.kit.items." + items + ".lore")) {
                                            if (string.isEmpty()) {
                                                lore.add(Component.text(" "));
                                            } else {
                                                lore.add(TextUtil.parse(string, MessageType.from(string)));
                                            }
                                        }
                                        meta.lore(lore);
                                        itemStack.setItemMeta(meta);
                                        inventory.setItem(this.plugin.getConfiguration().getConfiguration().getInt("inventory.kit.items." + items + ".slot"), itemStack);
                                    }
                                }
                            } else if (this.plugin.getProfileManager().get(event.getPlayer().getUniqueId()).getSelectedKit() == 2) {
                                if (this.plugin.getConfiguration().getConfiguration().getInt("inventory.kit.items." + items + ".kit") != 0) {
                                    if (this.plugin.getConfiguration().getConfiguration().getInt("inventory.kit.items." + items + ".kit") == 2) {
                                        ItemStack itemStack = new ItemStack(Material.valueOf(this.plugin.getConfiguration().getConfiguration().getString("selection.selected-item")));
                                        itemStack.setAmount(this.plugin.getConfiguration().getConfiguration().getInt("inventory.kit.items." + items + ".amount"));
                                        this.plugin.getConfiguration().getConfiguration().getStringList("inventory.kit.items." + items + ".enchantments")
                                            .forEach(enchantment -> itemStack.addUnsafeEnchantment(Objects.requireNonNull(Enchantment.getByName(enchantment.split(":")[0])), Integer.parseInt(enchantment.split(":")[1])));
                                        ItemMeta meta = itemStack.getItemMeta();
                                        meta.displayName(TextUtil.parse(this.plugin.getConfiguration().getConfiguration().getString("selection.selected-name")
                                            , MessageType.from(Objects.requireNonNull(this.plugin.getConfiguration().getConfiguration().getString("selection.selected-name")))));
                                        List<Component> lore = new ArrayList<>();
                                        for (String string : this.plugin.getConfiguration().getConfiguration().getStringList("inventory.kit.items." + items + ".lore")) {
                                            if (string.isEmpty()) {
                                                lore.add(Component.text(" "));
                                            } else {
                                                lore.add(TextUtil.parse(string, MessageType.from(string)));
                                            }
                                        }
                                        meta.lore(lore);
                                        itemStack.setItemMeta(meta);
                                        inventory.setItem(this.plugin.getConfiguration().getConfiguration().getInt("inventory.kit.items." + items + ".slot"), itemStack);
                                    } else {
                                        ItemStack itemStack = new ItemStack(Material.valueOf(this.plugin.getConfiguration().getConfiguration().getString("selection.unselected-item")));
                                        itemStack.setAmount(this.plugin.getConfiguration().getConfiguration().getInt("inventory.kit.items." + items + ".amount"));
                                        this.plugin.getConfiguration().getConfiguration().getStringList("inventory.kit.items." + items + ".enchantments")
                                            .forEach(enchantment -> itemStack.addUnsafeEnchantment(Objects.requireNonNull(Enchantment.getByName(enchantment.split(":")[0])), Integer.parseInt(enchantment.split(":")[1])));
                                        ItemMeta meta = itemStack.getItemMeta();
                                        meta.displayName(TextUtil.parse(this.plugin.getConfiguration().getConfiguration().getString("selection.unselected-name")
                                            , MessageType.from(Objects.requireNonNull(this.plugin.getConfiguration().getConfiguration().getString("selection.unselected-name")))));
                                        List<Component> lore = new ArrayList<>();
                                        for (String string : this.plugin.getConfiguration().getConfiguration().getStringList("inventory.kit.items." + items + ".lore")) {
                                            if (string.isEmpty()) {
                                                lore.add(Component.text(" "));
                                            } else {
                                                lore.add(TextUtil.parse(string, MessageType.from(string)));
                                            }
                                        }
                                        meta.lore(lore);
                                        itemStack.setItemMeta(meta);
                                        inventory.setItem(this.plugin.getConfiguration().getConfiguration().getInt("inventory.kit.items." + items + ".slot"), itemStack);
                                    }
                                }
                            } else if (this.plugin.getProfileManager().get(event.getPlayer().getUniqueId()).getSelectedKit() == 3) {
                                if (this.plugin.getConfiguration().getConfiguration().getInt("inventory.kit.items." + items + ".kit") != 0) {
                                    if (this.plugin.getConfiguration().getConfiguration().getInt("inventory.kit.items." + items + ".kit") == 3) {
                                        ItemStack itemStack = new ItemStack(Material.valueOf(this.plugin.getConfiguration().getConfiguration().getString("selection.selected-item")));
                                        itemStack.setAmount(this.plugin.getConfiguration().getConfiguration().getInt("inventory.kit.items." + items + ".amount"));
                                        this.plugin.getConfiguration().getConfiguration().getStringList("inventory.kit.items." + items + ".enchantments")
                                            .forEach(enchantment -> itemStack.addUnsafeEnchantment(Objects.requireNonNull(Enchantment.getByName(enchantment.split(":")[0])), Integer.parseInt(enchantment.split(":")[1])));
                                        ItemMeta meta = itemStack.getItemMeta();
                                        meta.displayName(TextUtil.parse(this.plugin.getConfiguration().getConfiguration().getString("selection.selected-name")
                                            , MessageType.from(Objects.requireNonNull(this.plugin.getConfiguration().getConfiguration().getString("selection.selected-name")))));
                                        List<Component> lore = new ArrayList<>();
                                        for (String string : this.plugin.getConfiguration().getConfiguration().getStringList("inventory.kit.items." + items + ".lore")) {
                                            if (string.isEmpty()) {
                                                lore.add(Component.text(" "));
                                            } else {
                                                lore.add(TextUtil.parse(string, MessageType.from(string)));
                                            }
                                        }
                                        meta.lore(lore);
                                        itemStack.setItemMeta(meta);
                                        inventory.setItem(this.plugin.getConfiguration().getConfiguration().getInt("inventory.kit.items." + items + ".slot"), itemStack);
                                    } else {
                                        ItemStack itemStack = new ItemStack(Material.valueOf(this.plugin.getConfiguration().getConfiguration().getString("selection.unselected-item")));
                                        itemStack.setAmount(this.plugin.getConfiguration().getConfiguration().getInt("inventory.kit.items." + items + ".amount"));
                                        this.plugin.getConfiguration().getConfiguration().getStringList("inventory.kit.items." + items + ".enchantments")
                                            .forEach(enchantment -> itemStack.addUnsafeEnchantment(Objects.requireNonNull(Enchantment.getByName(enchantment.split(":")[0])), Integer.parseInt(enchantment.split(":")[1])));
                                        ItemMeta meta = itemStack.getItemMeta();
                                        meta.displayName(TextUtil.parse(this.plugin.getConfiguration().getConfiguration().getString("selection.unselected-name")
                                            , MessageType.from(Objects.requireNonNull(this.plugin.getConfiguration().getConfiguration().getString("selection.unselected-name")))));
                                        List<Component> lore = new ArrayList<>();
                                        for (String string : this.plugin.getConfiguration().getConfiguration().getStringList("inventory.kit.items." + items + ".lore")) {
                                            if (string.isEmpty()) {
                                                lore.add(Component.text(" "));
                                            } else {
                                                lore.add(TextUtil.parse(string, MessageType.from(string)));
                                            }
                                        }
                                        meta.lore(lore);
                                        itemStack.setItemMeta(meta);
                                        inventory.setItem(this.plugin.getConfiguration().getConfiguration().getInt("inventory.kit.items." + items + ".slot"), itemStack);
                                    }
                                }
                            }
                        }
                    }

                    event.getPlayer().openInventory(inventory);
                } else if (name.equalsIgnoreCase("stats")) {
                    Inventory inventory = Bukkit.createInventory(event.getPlayer(), this.plugin.getConfiguration().getConfiguration().getInt("inventory.statistics.size"),
                        TextUtil.parse(this.plugin.getConfiguration().getConfiguration().getString("inventory.statistics.title"), MessageType.from(Objects.requireNonNull(this.plugin.getConfiguration().getConfiguration().getString("inventory.selector.title")))));
                    for (final String items : Objects.requireNonNull(this.plugin.getConfiguration().getConfiguration().getConfigurationSection("inventory.statistics.items")).getKeys(false)) {
                        ItemStack itemStack = new ItemStack(Material.valueOf(this.plugin.getConfiguration().getConfiguration().getString("inventory.statistics.items." + items + ".item")));
                        itemStack.setAmount(this.plugin.getConfiguration().getConfiguration().getInt("inventory.statistics.items." + items + ".amount"));
                        this.plugin.getConfiguration().getConfiguration().getStringList("inventory.statistics.items." + items + ".enchantments")
                            .forEach(enchantment -> itemStack.addUnsafeEnchantment(Objects.requireNonNull(Enchantment.getByName(enchantment.split(":")[0])), Integer.parseInt(enchantment.split(":")[1])));
                        ItemMeta meta = itemStack.getItemMeta();
                        meta.displayName(TextUtil.parse(this.plugin.getConfiguration().getConfiguration().getString("inventory.statistics.items." + items + ".name")
                            ,MessageType.from(Objects.requireNonNull(this.plugin.getConfiguration().getConfiguration().getString("inventory.statistics.items." + items + ".name")))));
                        List<Component> lore = new ArrayList<>();
                        for (String string : this.plugin.getConfiguration().getConfiguration().getStringList("inventory.statistics.items." + items + ".lore")) {
                            if (string.isEmpty()) {
                                lore.add(Component.text(" "));
                            } else {
                                lore.add(TextUtil.parse(string
                                    .replace("%player%", event.getPlayer().getName())
                                    .replace("%kills%", String.valueOf(this.plugin.getProfileManager().get(event.getPlayer().getUniqueId()).getKills()))
                                    .replace("%deaths%", String.valueOf(this.plugin.getProfileManager().get(event.getPlayer().getUniqueId()).getDeaths()))
                                    .replace("%wins%", String.valueOf(this.plugin.getProfileManager().get(event.getPlayer().getUniqueId()).getWins()))
                                    .replace("%loses%", String.valueOf(this.plugin.getProfileManager().get(event.getPlayer().getUniqueId()).getDeaths())), MessageType.from(string)));
                            }
                        }
                        meta.lore(lore);
                        itemStack.setItemMeta(meta);
                        inventory.setItem(this.plugin.getConfiguration().getConfiguration().getInt("inventory.statistics.items." + items + ".slot"), itemStack);
                    }

                    event.getPlayer().openInventory(inventory);
                }
            }
        }
    }
}
