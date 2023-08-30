package com.mizuledevelopment.zpractice.arena;

import com.mizuledevelopment.zpractice.zPractice;
import org.bukkit.Location;
import org.bukkit.Material;

import java.util.*;

public class Arena {

    private String name;
    private String world;
    private ArenaState state;
    private List<String> kitItems;
    private int startingTimer;
    private List<UUID> teamOne, teamTwo;
    private List<UUID> specatators;
    private List<Location> placedBlocks;
    private Map<Location, Material> brokenBlocks;
    private String locationOne, locationTwo;

    public Arena(zPractice plugin, String name, String world, String locationOne, String locationTwo){
        this.startingTimer = plugin.getConfiguration().getConfiguration().getInt("starting-timer");
        this.name = name;
        this.world = world;
        this.locationOne = locationOne;
        this.locationTwo = locationTwo;
        this.state = ArenaState.WAITING;
        this.kitItems = new ArrayList<>();
        this.teamOne = new ArrayList<>();
        this.teamTwo = new ArrayList<>();
        this.brokenBlocks = new HashMap<>();
        this.placedBlocks = new ArrayList<>();
        this.specatators = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public ArenaState getState() {
        return state;
    }

    public int getStartingTimer() {
        return startingTimer;
    }

    public List<String> getKitItems() {
        return kitItems;
    }

    public Map<Location, Material> getBrokenBlocks() {
        return brokenBlocks;
    }

    public List<UUID> getTeamOne() {
        return teamOne;
    }

    public void setBrokenBlocks(final Map<Location, Material> brokenBlocks) {
        this.brokenBlocks = brokenBlocks;
    }

    public List<UUID> getTeamTwo() {
        return teamTwo;
    }

    public String getLocationOne() {
        return locationOne;
    }

    public String getLocationTwo() {
        return locationTwo;
    }

    public List<Location> getPlacedBlocks() {
        return placedBlocks;
    }

    public String getWorld() {
        return world;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public void setKitItems(final List<String> kitItems) {
        this.kitItems = kitItems;
    }

    public void setPlacedBlocks(final List<Location> placedBlocks) {
        this.placedBlocks = placedBlocks;
    }

    public void setStartingTimer(final int startingTimer) {
        this.startingTimer = startingTimer;
    }

    public void setLocationOne(final String locationOne) {
        this.locationOne = locationOne;
    }

    public void setLocationTwo(final String locationTwo) {
        this.locationTwo = locationTwo;
    }

    public void setState(final ArenaState state) {
        this.state = state;
    }

    public void setTeamOne(final List<UUID> teamOne) {
        this.teamOne = teamOne;
    }

    public void setTeamTwo(final List<UUID> teamTwo) {
        this.teamTwo = teamTwo;
    }

    public void setWorld(final String world) {
        this.world = world;
    }

    public List<UUID> getSpecatators() {
        return specatators;
    }

    public void setSpecatators(final List<UUID> specatators) {
        this.specatators = specatators;
    }
}
