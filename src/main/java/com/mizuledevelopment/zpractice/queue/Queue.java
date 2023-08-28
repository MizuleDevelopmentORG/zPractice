package com.mizuledevelopment.zpractice.queue;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public class Queue {

    private String name;
    private String arena;
    private String kit;
    private List<UUID> players;
    private int maxPlayers;

    public Queue(String name, String arena, String kit, List<UUID> players, int maxPlayers) {
        this.name = name;
        this.arena = arena;
        this.kit = kit;
        this.players = players;
        this.maxPlayers = maxPlayers;
    }

    public String getName() {
        return name;
    }

    public String getKit() {
        return kit;
    }

    public String getArena() {
        return arena;
    }

    public List<UUID> getPlayers() {
        return players;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public void setKit(final String kit) {
        this.kit = kit;
    }

    public void setArena(final String arena) {
        this.arena = arena;
    }

    public void setPlayers(final List<UUID> players) {
        this.players = players;
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }

    public void setMaxPlayers(final int maxPlayers) {
        this.maxPlayers = maxPlayers;
    }
}
