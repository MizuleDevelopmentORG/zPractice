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
import org.bukkit.Location;
import org.bukkit.Material;

import java.util.*;

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

    public Arena find(UUID uniqueId) {
        for (Arena arena : arenas) {
            if (arena.getTeamOne().contains(uniqueId) || arena.getTeamTwo().contains(uniqueId)) {
                return arena;
            }
        }
        return null;
    }

    public void resetArena(Arena arena) {
        for (Location location : arena.getPlacedBlocks()) {
            location.getBlock().setType(Material.AIR);
        }

        for (Map.Entry<Location, Material> map : arena.getBrokenBlocks().entrySet()) {
            map.getKey().getBlock().setType(map.getValue());
        }
    }
}
