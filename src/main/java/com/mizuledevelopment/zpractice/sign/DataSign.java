package com.mizuledevelopment.zpractice.sign;

import com.mizuledevelopment.zpractice.util.LazyLocation;

public class DataSign {

    private String name;
    private String arena;
    private String kit;
    private LazyLocation location;
    private int maxPlayers;

    public DataSign(String name, String arena, String kit, LazyLocation location, int maxPlayers) {
        this.name = name;
        this.arena = arena;
        this.kit = kit;
        this.location = location;
        this.maxPlayers = maxPlayers;
    }

    public String getArena() {
        return arena;
    }

    public String getKit() {
        return kit;
    }

    public String getName() {
        return name;
    }

    public void setArena(final String arena) {
        this.arena = arena;
    }

    public void setKit(final String kit) {
        this.kit = kit;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public LazyLocation getLocation() {
        return location;
    }

    public void setLocation(final LazyLocation location) {
        this.location = location;
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }

    public void setMaxPlayers(final int maxPlayers) {
        this.maxPlayers = maxPlayers;
    }
}
