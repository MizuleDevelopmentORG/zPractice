package com.mizuledevelopment.zpractice.arena.handler;

import com.mizuledevelopment.zpractice.arena.Arena;
import com.mizuledevelopment.zpractice.arena.ArenaState;
import com.mizuledevelopment.zpractice.queue.Queue;
import com.mizuledevelopment.zpractice.util.serializer.ItemStackSerializer;
import com.mizuledevelopment.zpractice.util.serializer.LocationSerializer;
import com.mizuledevelopment.zpractice.zPractice;
import org.bukkit.Bukkit;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class ArenaHandler {

    private final zPractice plugin;
    private final Arena arena;

    public ArenaHandler(Arena arena, zPractice plugin) {
        this.plugin = plugin;
        this.arena = arena;
    }

    public void addPlayers(Queue queue) {
        List<UUID> one = new ArrayList<>();
        List<UUID> two = new ArrayList<>();

        int size = queue.getPlayers().size();

        for (int i = 0; i < size / 2; i++)
            one.add(queue.getPlayers().get(i));

        for (int i = size / 2; i < size; i++)
            two.add(queue.getPlayers().get(i));

        this.arena.setTeamOne(one);
        this.arena.setTeamTwo(two);
    }

    public void sendPlayers(){
        for (UUID uuid : this.arena.getTeamOne()) {
            if (Bukkit.getPlayer(uuid) != null) {
                Objects.requireNonNull(Bukkit.getPlayer(uuid)).teleport(LocationSerializer.deSerialize(this.arena.getLocationOne()));
            }
        }

        for (UUID uuid : this.arena.getTeamTwo()) {
            if (Bukkit.getPlayer(uuid) != null) {
                Objects.requireNonNull(Bukkit.getPlayer(uuid)).teleport(LocationSerializer.deSerialize(this.arena.getLocationTwo()));
            }
        }
    }

    public void applyKits(){
        for (String items : this.arena.getKitItems()){

            for (UUID uuid : this.arena.getTeamOne()) {
                if (Bukkit.getPlayer(uuid) != null) {
                    Objects.requireNonNull(Bukkit.getPlayer(uuid)).getInventory().addItem(ItemStackSerializer.deSerialize(items));
                }
            }

            for (UUID uuid : this.arena.getTeamTwo()) {
                if (Bukkit.getPlayer(uuid) != null) {
                    Objects.requireNonNull(Bukkit.getPlayer(uuid)).getInventory().addItem(ItemStackSerializer.deSerialize(items));
                }
            }
        }
    }

    public void start(){
        arena.setState(ArenaState.STARTING);
        this.plugin.getArenaManager().getStartingArenas().add(arena);
    }
}
