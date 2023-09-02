package com.mizuledevelopment.zpractice.arena.listener;

import com.mizuledevelopment.zpractice.arena.Arena;
import com.mizuledevelopment.zpractice.arena.ArenaState;
import com.mizuledevelopment.zpractice.zPractice;
import io.papermc.paper.event.entity.EntityDamageItemEvent;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.*;

import java.util.*;

public class ArenaListener implements Listener {

    private final zPractice plugin;

    public ArenaListener(zPractice plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        Player player = event.getPlayer();

        Arena arena = this.plugin.getArenaManager().find(player.getUniqueId());

        if (arena != null) {
            List<UUID> one = arena.getTeamOne();
            List<UUID> two = arena.getTeamTwo();

            if (arena.getTeamOne().contains(player.getUniqueId())) {
                arena.getTeamOne().remove(player.getUniqueId());
            } else {
                arena.getTeamTwo().remove(player.getUniqueId());
            }

            player.setHealth(20);
            player.setFoodLevel(20);
            player.getInventory().clear();
            player.setGameMode(GameMode.SPECTATOR);
            arena.getSpecatators().add(player.getUniqueId());

            if (!arena.getTeamOne().isEmpty() && arena.getTeamTwo().isEmpty()
                || !arena.getTeamTwo().isEmpty() && arena.getTeamOne().isEmpty()) {
                if (!arena.getSpecatators().isEmpty()) {
                    for (UUID uuid : arena.getSpecatators()) {
                        if (Bukkit.getPlayer(uuid) != null) {
                            Objects.requireNonNull(Bukkit.getPlayer(uuid)).setGameMode(GameMode.SURVIVAL);
                            Objects.requireNonNull(Bukkit.getPlayer(uuid)).teleport(
                                new Location(Bukkit.getWorld("world"),
                                    this.plugin.getConfiguration().getConfiguration().getInt("spawn.x"),
                                    this.plugin.getConfiguration().getConfiguration().getInt("spawn.y"),
                                    this.plugin.getConfiguration().getConfiguration().getInt("spawn.z"))
                            );
                        }
                    }
                }
                if (!arena.getTeamOne().isEmpty()) {
                    for (UUID uuid : arena.getTeamOne()) {
                        if (Bukkit.getPlayer(uuid) != null) {
                            Objects.requireNonNull(Bukkit.getPlayer(uuid)).setHealth(20);
                            Objects.requireNonNull(Bukkit.getPlayer(uuid)).setFoodLevel(20);
                            Objects.requireNonNull(Bukkit.getPlayer(uuid)).getInventory().clear();
                            Objects.requireNonNull(Bukkit.getPlayer(uuid)).teleport(
                                new Location(Bukkit.getWorld("world"),
                                    this.plugin.getConfiguration().getConfiguration().getInt("spawn.x"),
                                    this.plugin.getConfiguration().getConfiguration().getInt("spawn.y"),
                                    this.plugin.getConfiguration().getConfiguration().getInt("spawn.z")));
                        }
                    }

                    for (UUID uuid : one) {
                        this.plugin.getProfileManager().get(uuid).setWins(this.plugin.getProfileManager().get(uuid).getWins() + 1);
                    }

                    for (UUID uuid : two) {
                        this.plugin.getProfileManager().get(uuid).setLoses(this.plugin.getProfileManager().get(uuid).getLoses() + 1);
                    }
                }

                if (!arena.getTeamTwo().isEmpty()) {
                    for (UUID uuid : arena.getTeamTwo()) {
                        if (Bukkit.getPlayer(uuid) != null) {
                            Objects.requireNonNull(Bukkit.getPlayer(uuid)).setHealth(20);
                            Objects.requireNonNull(Bukkit.getPlayer(uuid)).setFoodLevel(20);
                            Objects.requireNonNull(Bukkit.getPlayer(uuid)).getInventory().clear();
                            Objects.requireNonNull(Bukkit.getPlayer(uuid)).teleport(
                                new Location(Bukkit.getWorld("world"),
                                    this.plugin.getConfiguration().getConfiguration().getInt("spawn.x"),
                                    this.plugin.getConfiguration().getConfiguration().getInt("spawn.y"),
                                    this.plugin.getConfiguration().getConfiguration().getInt("spawn.z")));
                        }
                    }

                    for (UUID uuid : one) {
                        this.plugin.getProfileManager().get(uuid).setLoses(this.plugin.getProfileManager().get(uuid).getLoses() + 1);
                    }

                    for (UUID uuid : two) {
                        this.plugin.getProfileManager().get(uuid).setWins(this.plugin.getProfileManager().get(uuid).getWins() + 1);
                    }
                }


                one.clear();
                two.clear();

                this.plugin.getQueueManager().get(arena.getName()).getPlayers().clear();
                arena.setState(ArenaState.RESETTING);
                this.plugin.getArenaManager().resetArena(arena);
                arena.getPlacedBlocks().clear();
                arena.getBrokenBlocks().clear();
                arena.getTeamTwo().clear();
                arena.getTeamOne().clear();
                arena.setState(ArenaState.WAITING);
            }
        }
    }

    @EventHandler
    public void onPvP(EntityDamageByBlockEvent event) {
        if (event.getEntity() == null || !(event.getEntity() instanceof Player)) return;
        if (this.plugin.getArenaManager().find(event.getEntity().getUniqueId()) != null && this.plugin.getArenaManager().find(event.getEntity().getUniqueId()).getState() == ArenaState.STARTING) {
            event.setDamage(0);
            event.setCancelled(true);
        }
    }
    @EventHandler
    public void onPvP(EntityDamageByEntityEvent event) {
        if (event.getEntity() == null || !(event.getEntity() instanceof Player)) return;
        if (this.plugin.getArenaManager().find(event.getEntity().getUniqueId()) != null && this.plugin.getArenaManager().find(event.getEntity().getUniqueId()).getState() == ArenaState.STARTING) {
            event.setDamage(0);
            event.setCancelled(true);
        }
    }
    @EventHandler
    public void onPvP(EntityDamageEvent event) {
        if (event.getEntity() == null || !(event.getEntity() instanceof Player)) return;
        if (this.plugin.getArenaManager().find(event.getEntity().getUniqueId()) != null &&this.plugin.getArenaManager().find(event.getEntity().getUniqueId()).getState() == ArenaState.STARTING) {
            event.setDamage(0);
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPvP(EntityDamageItemEvent event) {
        if (event.getEntity() == null || !(event.getEntity() instanceof Player)) return;
        if (this.plugin.getArenaManager().find(event.getEntity().getUniqueId()) != null &&this.plugin.getArenaManager().find(event.getEntity().getUniqueId()).getState() == ArenaState.STARTING) {
            event.setDamage(0);
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent event) {
        if (this.plugin.getArenaManager().find(event.getPlayer().getUniqueId()) != null) {
            List<Location> placed =  this.plugin.getArenaManager().find(event.getPlayer().getUniqueId()).getPlacedBlocks();
            placed.add(event.getBlockPlaced().getLocation());
        }
    }

    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        if (this.plugin.getArenaManager().find(event.getPlayer().getUniqueId()) != null) {
            Map<Location, Material> map = this.plugin.getArenaManager().find(event.getPlayer().getUniqueId()).getBrokenBlocks();
            map.put(event.getBlock().getLocation(), event.getBlock().getType());
        }
    }
}
