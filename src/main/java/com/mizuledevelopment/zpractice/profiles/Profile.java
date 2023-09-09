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
    private int selectedKit;
    private List<String> items1, items2, items3;
    private List<String> items1Armor, items2Armor, items3Armor;
    private List<String> items1Bar, items2Bar, items3Bar;

    public Profile(
        String uuid,
        int kills,
        int deaths,
        int wins,
        int loses,
        List<String> items1,
        List<String> items2,
        List<String> items3,
        List<String> items1Armor,
        List<String> items2Armor,
        List<String> items3Armor,
        List<String> items1Bar,
        List<String> items2Bar,
        List<String> items3Bar,
        int selectedKit
    ) {
        this.uuid = uuid;
        this.kills = kills;
        this.wins = wins;
        this.loses = loses;
        this.deaths = deaths;
        this.items1 = items1;
        this.items2 = items2;
        this.items3 = items3;
        this.items1Armor = items1Armor;
        this.items2Armor = items2Armor;
        this.items3Armor = items3Armor;
        this.items1Bar = items1Bar;
        this.items2Bar = items2Bar;
        this.items3Bar = items3Bar;
        this.selectedKit = selectedKit;
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

    public List<String> getItems1Armor() {
        return items1Armor;
    }

    public List<String> getItems1Bar() {
        return items1Bar;
    }

    public List<String> getItems2Armor() {
        return items2Armor;
    }

    public List<String> getItems2Bar() {
        return items2Bar;
    }

    public List<String> getItems3Armor() {
        return items3Armor;
    }

    public List<String> getItems3Bar() {
        return items3Bar;
    }

    public void setDeaths(final int deaths) {
        this.deaths = deaths;
    }

    public void setKills(final int kills) {
        this.kills = kills;
    }

    public int getSelectedKit() {
        return selectedKit;
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

    public void setSelectedKit(final int selectedKit) {
        this.selectedKit = selectedKit;
    }

    public void setItems1Armor(final List<String> items1Armor) {
        this.items1Armor = items1Armor;
    }

    public void setItems1Bar(final List<String> items1Bar) {
        this.items1Bar = items1Bar;
    }

    public void setItems2Armor(final List<String> items2Armor) {
        this.items2Armor = items2Armor;
    }

    public void setItems2Bar(final List<String> items2Bar) {
        this.items2Bar = items2Bar;
    }

    public void setItems3Armor(final List<String> items3Armor) {
        this.items3Armor = items3Armor;
    }

    public void setItems3Bar(final List<String> items3Bar) {
        this.items3Bar = items3Bar;
    }
}

