package com.mizuledevelopment.zpractice.arena.manager;

import com.mizuledevelopment.zpractice.arena.Arena;
import com.mizuledevelopment.zpractice.util.helper.ArenaHelper;
import com.mizuledevelopment.zpractice.zPractice;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.UpdateOptions;
import org.bson.Document;
import org.bukkit.Bukkit;
import org.bukkit.Material;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class ArenaManager {

    private final zPractice plugin;
    private final Set<Arena> arenas = new HashSet<>();
    private final Set<Arena> startingArenas = new HashSet<>();


    public ArenaManager(zPractice plugin) {
        this.plugin = plugin;
    }

    public void load(){
        FindIterable<Document> iterator = this.plugin.getMongoHandler().getArenas().find();
        try (MongoCursor<Document> cursor = iterator.iterator()) {
            while (cursor.hasNext()) {
                arenas.add(ArenaHelper.from(this.plugin, cursor.next()));
            }
        }
    }

    public void reset(){
        arenas.forEach(arena -> {
            arena.getPlacedBlocks().forEach(location -> {
                Objects.requireNonNull(Bukkit.getWorld(arena.getWorld())).getBlockAt(location).setType(Material.AIR);
            });
            arena.getBrokenBlocks().forEach((location, material) -> {
                Objects.requireNonNull(Bukkit.getWorld(arena.getWorld())).getBlockAt(location).setType(material);
            });
        });
    }

    @Deprecated
    public void save(){
        arenas.forEach(arena -> {
            this.plugin.getMongoHandler().getArenas()
                .replaceOne(Filters.eq("name", arena.getName()), ArenaHelper.from(arena), new UpdateOptions().upsert(true));
        });
    }

    public Arena getArenaByName(String name) {
        for (Arena arena : arenas) {
            if (arena.getName().equalsIgnoreCase(name)) {
                return arena;
            }
        }
        return null;
    }

    public Set<Arena> getStartingArenas() {
        return startingArenas;
    }
}
