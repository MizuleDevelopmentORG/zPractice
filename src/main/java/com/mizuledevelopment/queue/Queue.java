package com.mizuledevelopment.queue;

import java.util.Set;
import java.util.UUID;

public class Queue {

    private String name;
    private String arena;
    private String kit;
    private Set<UUID> players;
    private int maxPlayers;

    public Queue(String name, String arena, String kit, Set<UUID> players, int maxPlayers) {
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

    public Set<UUID> getPlayers() {
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

    public void setPlayers(final Set<UUID> players) {
        this.players = players;
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }

    public void setMaxPlayers(final int maxPlayers) {
        this.maxPlayers = maxPlayers;
    }
}
