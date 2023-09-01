package com.mizuledevelopment.zpractice.arena.listener;

import com.mizuledevelopment.zpractice.arena.Arena;
import com.mizuledevelopment.zpractice.arena.ArenaState;
import com.mizuledevelopment.zpractice.zPractice;
import io.papermc.paper.event.entity.EntityDamageItemEvent;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

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
                        player.setHealth(20);
                        player.setFoodLevel(20);
                        player.getInventory().clear();
                        Objects.requireNonNull(Bukkit.getPlayer(uuid)).teleport(
                            new Location(Bukkit.getWorld("world"),
                                this.plugin.getConfiguration().getConfiguration().getInt("spawn.x"),
                                this.plugin.getConfiguration().getConfiguration().getInt("spawn.y"),
                                this.plugin.getConfiguration().getConfiguration().getInt("spawn.z")));
                    }

                    for (UUID uuid : one) {
                        if (this.plugin.getProfileManager().get(uuid).getArenaWins().isEmpty()
                            || !this.plugin.getProfileManager().get(uuid).getArenaWins().contains(arena.getName())) {
                            List<String> wins = new ArrayList<>();
                            wins.add(arena.getName() + ":1");
                            this.plugin.getProfileManager().get(uuid).setArenaWins(wins);
                        } else {
                            List<String> wins = this.plugin.getProfileManager().get(uuid).getArenaWins();
                            for (String s : wins) {
                                if (s.contains(arena.getName())) {
                                    String newS = arena.getName() + ":" + Integer.parseInt(s.split(":")[1]) + 1;
                                    wins.remove(s);
                                    wins.add(newS);
                                }
                            }
                        }
                    }

                    for (UUID uuid : two) {
                        if (this.plugin.getProfileManager().get(uuid).getArenaWins().isEmpty()
                            || !this.plugin.getProfileManager().get(uuid).getArenaLoses().contains(arena.getName())) {
                            List<String> loses = new ArrayList<>();
                            loses.add(arena.getName() + ":1");
                            this.plugin.getProfileManager().get(uuid).setArenaLoses(loses);
                        } else {
                            List<String> loses = this.plugin.getProfileManager().get(uuid).getArenaLoses();
                            for (String s : loses) {
                                if (s.contains(arena.getName())) {
                                    String newS = arena.getName() + ":" + Integer.parseInt(s.split(":")[1]) + 1;
                                    loses.remove(s);
                                    loses.add(newS);
                                }
                            }
                        }
                    }
                }

                if (!arena.getTeamTwo().isEmpty()) {
                    for (UUID uuid : arena.getTeamTwo()) {
                        player.setHealth(20);
                        player.setFoodLevel(20);
                        player.getInventory().clear();
                        Objects.requireNonNull(Bukkit.getPlayer(uuid)).teleport(
                            new Location(Bukkit.getWorld("world"),
                                this.plugin.getConfiguration().getConfiguration().getInt("spawn.x"),
                                this.plugin.getConfiguration().getConfiguration().getInt("spawn.y"),
                                this.plugin.getConfiguration().getConfiguration().getInt("spawn.z")));
                    }

                    for (UUID uuid : one) {
                        if (this.plugin.getProfileManager().get(uuid).getArenaLoses().isEmpty()
                            || !this.plugin.getProfileManager().get(uuid).getArenaLoses().contains(arena.getName())) {
                            List<String> loses = new ArrayList<>();
                            loses.add(arena.getName() + ":1");
                            this.plugin.getProfileManager().get(uuid).setArenaWins(loses);
                        } else {
                            List<String> loses = this.plugin.getProfileManager().get(uuid).getArenaLoses();
                            for (String s : loses) {
                                if (s.contains(arena.getName())) {
                                    String newS = arena.getName() + ":" + Integer.parseInt(s.split(":")[1]) + 1;
                                    loses.remove(s);
                                    loses.add(newS);
                                }
                            }
                        }
                    }

                    for (UUID uuid : two) {
                        if (this.plugin.getProfileManager().get(uuid).getArenaWins().isEmpty()
                            || !this.plugin.getProfileManager().get(uuid).getArenaWins().contains(arena.getName())) {
                            List<String> wins = new ArrayList<>();
                            wins.add(arena.getName() + ":1");
                            this.plugin.getProfileManager().get(uuid).setArenaWins(wins);
                        } else {
                            List<String> wins = this.plugin.getProfileManager().get(uuid).getArenaWins();
                            for (String s : wins) {
                                if (s.contains(arena.getName())) {
                                    String newS = arena.getName() + ":" + Integer.parseInt(s.split(":")[1]) + 1;
                                    wins.remove(s);
                                    wins.add(newS);
                                }
                            }
                        }
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
        if (this.plugin.getArenaManager().find(event.getEntity().getUniqueId()).getState() == ArenaState.STARTING) {
            event.setDamage(0);
            event.setCancelled(true);
        }
    }
    @EventHandler
    public void onPvP(EntityDamageByEntityEvent event) {
        if (event.getEntity() == null || !(event.getEntity() instanceof Player)) return;
        if (this.plugin.getArenaManager().find(event.getEntity().getUniqueId()).getState() == ArenaState.STARTING) {
            event.setDamage(0);
            event.setCancelled(true);
        }
    }
    @EventHandler
    public void onPvP(EntityDamageEvent event) {
        if (event.getEntity() == null || !(event.getEntity() instanceof Player)) return;
        if (this.plugin.getArenaManager().find(event.getEntity().getUniqueId()).getState() == ArenaState.STARTING) {
            event.setDamage(0);
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPvP(EntityDamageItemEvent event) {
        if (event.getEntity() == null || !(event.getEntity() instanceof Player)) return;
        if (this.plugin.getArenaManager().find(event.getEntity().getUniqueId()).getState() == ArenaState.STARTING) {
            event.setDamage(0);
            event.setCancelled(true);
        }
    }
}
