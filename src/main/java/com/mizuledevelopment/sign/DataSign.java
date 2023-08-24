package com.mizuledevelopment.sign;

public class DataSign {

    private String name;
    private String arena;
    private String kit;

    public DataSign(String name, String arena, String kit) {
        this.name = name;
        this.arena = arena;
        this.kit = kit;
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
}
