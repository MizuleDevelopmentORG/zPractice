package com.mizuledevelopment.util.items;

import com.mizuledevelopment.ZPractice;
import com.mizuledevelopment.util.color.MessageType;
import com.mizuledevelopment.util.color.TextUtil;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Objects;

@SuppressWarnings("ALL")
public class Items {

    public static ItemStack SELECTOR(ZPractice plugin){
        ItemStack itemStack = new ItemStack(Material.valueOf(plugin.getConfiguration().getConfiguration().getString("selector.item")));
        itemStack.setAmount(plugin.getConfiguration().getConfiguration().getInt("selector.amount"));
        plugin.getConfiguration().getConfiguration().getStringList("selector.enchantments")
                .forEach(enchantment -> itemStack.addUnsafeEnchantment(Objects.requireNonNull(Enchantment.getByName(enchantment.split(":")[0])), Integer.parseInt(enchantment.split(":")[1])));
        ItemMeta meta = itemStack.getItemMeta();
        meta.displayName(TextUtil.parse(plugin.getConfiguration().getConfiguration().getString("selector.name"),
                MessageType.from(Objects.requireNonNull(plugin.getConfiguration().getConfiguration().getString("selector.name")))));
        ArrayList<Component> lore = new ArrayList<>();
        plugin.getConfiguration().getConfiguration().getStringList("selector.lore")
                .forEach(line -> lore.add(TextUtil.parse(line, MessageType.from(line))));
        meta.lore(lore);
        itemStack.setItemMeta(meta);
        return itemStack;
    }
}

