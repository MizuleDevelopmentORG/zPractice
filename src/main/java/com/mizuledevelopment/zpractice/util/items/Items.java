package com.mizuledevelopment.zpractice.util.items;

import com.mizuledevelopment.zpractice.zPractice;
import com.mizuledevelopment.zpractice.util.color.MessageType;
import com.mizuledevelopment.zpractice.util.color.TextUtil;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.Objects;

public class Items {

    public static ItemStack SELECTOR(zPractice plugin){
        ItemStack itemStack = new ItemStack(Material.valueOf(plugin.getConfiguration().getConfiguration().getString("selector.item")));
        itemStack.setAmount(plugin.getConfiguration().getConfiguration().getInt("selector.amount"));
        plugin.getConfiguration().getConfiguration().getStringList("selector.enchantments")
                .forEach(enchantment -> itemStack.addUnsafeEnchantment(Objects.requireNonNull(Enchantment.getByName(enchantment.split(":")[0])), Integer.parseInt(enchantment.split(":")[1])));
        ItemMeta meta = itemStack.getItemMeta();
        meta.getPersistentDataContainer().set(plugin.getNamespacedKey(), PersistentDataType.STRING, "selector");
        meta.displayName(TextUtil.parse(plugin.getConfiguration().getConfiguration().getString("selector.name"),
                MessageType.from(Objects.requireNonNull(plugin.getConfiguration().getConfiguration().getString("selector.name")))));
        ArrayList<Component> lore = new ArrayList<>();
        for (String line : plugin.getConfiguration().getConfiguration().getStringList("selector.lore")) {
            if (!line.isEmpty()) {
                lore.add(TextUtil.parse(line, MessageType.from(line)));
            } else {
                lore.add(Component.text(" "));
            }
        }
        meta.lore(lore);
        itemStack.setItemMeta(meta);
        return itemStack;
    }

    public static ItemStack KIT(zPractice plugin){
        ItemStack itemStack = new ItemStack(Material.valueOf(plugin.getConfiguration().getConfiguration().getString("kit.item")));
        itemStack.setAmount(plugin.getConfiguration().getConfiguration().getInt("kit.amount"));
        plugin.getConfiguration().getConfiguration().getStringList("kit.enchantments")
            .forEach(enchantment -> itemStack.addUnsafeEnchantment(Objects.requireNonNull(Enchantment.getByName(enchantment.split(":")[0])), Integer.parseInt(enchantment.split(":")[1])));
        ItemMeta meta = itemStack.getItemMeta();
        meta.getPersistentDataContainer().set(plugin.getNamespacedKey(), PersistentDataType.STRING, "kit");
        meta.displayName(TextUtil.parse(plugin.getConfiguration().getConfiguration().getString("kit.name"),
            MessageType.from(Objects.requireNonNull(plugin.getConfiguration().getConfiguration().getString("kit.name")))));
        ArrayList<Component> lore = new ArrayList<>();
        for (String line : plugin.getConfiguration().getConfiguration().getStringList("kit.lore")) {
            if (!line.isEmpty()) {
                lore.add(TextUtil.parse(line, MessageType.from(line)));
            } else {
                lore.add(Component.text(" "));
            }
        }
        meta.lore(lore);
        itemStack.setItemMeta(meta);
        return itemStack;
    }

    public static ItemStack STATS(zPractice plugin){
        ItemStack itemStack = new ItemStack(Material.valueOf(plugin.getConfiguration().getConfiguration().getString("statistics.item")));
        itemStack.setAmount(plugin.getConfiguration().getConfiguration().getInt("statistics.amount"));
        plugin.getConfiguration().getConfiguration().getStringList("statistics.enchantments")
            .forEach(enchantment -> itemStack.addUnsafeEnchantment(Objects.requireNonNull(Enchantment.getByName(enchantment.split(":")[0])), Integer.parseInt(enchantment.split(":")[1])));
        ItemMeta meta = itemStack.getItemMeta();
        meta.getPersistentDataContainer().set(plugin.getNamespacedKey(), PersistentDataType.STRING, "stats");
        meta.displayName(TextUtil.parse(plugin.getConfiguration().getConfiguration().getString("statistics.name"),
            MessageType.from(Objects.requireNonNull(plugin.getConfiguration().getConfiguration().getString("statistics.name")))));
        ArrayList<Component> lore = new ArrayList<>();
        for (String line : plugin.getConfiguration().getConfiguration().getStringList("statistics.lore")) {
            if (!line.isEmpty()) {
                lore.add(TextUtil.parse(line, MessageType.from(line)));
            } else {
                lore.add(Component.text(" "));
            }
        }
        meta.lore(lore);
        itemStack.setItemMeta(meta);
        return itemStack;
    }
}

