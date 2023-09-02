package com.mizuledevelopment.zpractice.profiles;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public class Profile {

    private final String uuid;
    private int kills;
    private int deaths;
    private int wins;
    private int loses;
    private List<String> items1;
    private List<String> items2;
    private List<String> items3;

    public Profile(
        String uuid,
        int kills,
        int deaths,
        int wins,
        int loses,
        List<String> items1,
        List<String> items2,
        List<String> items3
    ) {
        this.uuid = uuid;
        this.kills = kills;
        this.wins = wins;
        this.loses = loses;
        this.deaths = deaths;
        this.items1 = items1;
        this.items2 = items2;
        this.items3 = items3;
    }

    public String getUuid() {
        return uuid;
    }

    public int getDeaths() {
        return deaths;
    }

    public int getKills() {
        return kills;
    }

    public int getLoses() {
        return loses;
    }

    public int getWins() {
        return wins;
    }

    public List<String> getItems1() {
        return items1;
    }

    public List<String> getItems2() {
        return items2;
    }

    public List<String> getItems3() {
        return items3;
    }

    public void setDeaths(final int deaths) {
        this.deaths = deaths;
    }

    public void setKills(final int kills) {
        this.kills = kills;
    }

    public void setLoses(final int loses) {
        this.loses = loses;
    }

    public void setWins(final int wins) {
        this.wins = wins;
    }

    public void setItems1(final List<String> items1) {
        this.items1 = items1;
    }

    public void setItems2(final List<String> items2) {
        this.items2 = items2;
    }

    public void setItems3(final List<String> items3) {
        this.items3 = items3;
    }
}

