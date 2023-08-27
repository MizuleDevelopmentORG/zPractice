package com.mizuledevelopment.zpractice.util;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

import java.util.Objects;
import java.util.UUID;

/**
 * Represents a Location but doesn't parse the location until it is requested via {@link LazyLocation#location()}.
 */
public class LazyLocation {
    private final double x;
    private final double y;
    private final double z;
    private final float yaw;
    private final float pitch;
    private String world;
    private String worldName;

    public LazyLocation(
        final String worldId, final String worldName, final double x, final double y, final double z, final float yaw,
        final float pitch
    ) {
        this.world = worldId;
        this.worldName = worldName;
        this.x = x;
        this.y = y;
        this.z = z;
        this.yaw = yaw;
        this.pitch = pitch;
    }

    public static LazyLocation fromLocation(final Location location) {
        //noinspection ConstantConditions
        return new LazyLocation(location.getWorld().getUID().toString(), location.getWorld().getName(), location.getX(), location.getY(), location.getZ(), location.getYaw(), location.getPitch());
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof final LazyLocation that)) return false;
        return Double.compare(this.x, that.x) == 0 && Double.compare(this.y, that.y) == 0 && Double.compare(this.z, that.z) == 0 && Float.compare(this.yaw, that.yaw) == 0 && Float.compare(this.pitch, that.pitch) == 0 && Objects.equals(this.world, that.world) && Objects.equals(this.worldName, that.worldName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.x, this.y, this.z, this.yaw, this.pitch, this.world, this.worldName);
    }

    public String world() {
        return this.world;
    }

    public String worldName() {
        return this.worldName;
    }

    public double x() {
        return this.x;
    }

    public double y() {
        return this.y;
    }

    public double z() {
        return this.z;
    }

    public float yaw() {
        return this.yaw;
    }

    public float pitch() {
        return this.pitch;
    }

    public Location location() {
        if (this.world == null || this.world.isEmpty()) {
            return null;
        }

        World world = null;

        try {
            final UUID worldId = UUID.fromString(this.world);
            world = Bukkit.getWorld(worldId);
        } catch (final IllegalArgumentException ignored) {
        }

        if (world == null) {
            world = Bukkit.getWorld(this.world);
        }

        if (world == null && this.worldName != null && !this.worldName.isEmpty()) {
            world = Bukkit.getWorld(this.worldName);
        }

        if (world == null) {
            return null;
        }

        this.world = world.getUID().toString();
        this.worldName = world.getName();

        return new Location(world, this.x, this.y, this.z, this.yaw, this.pitch);
    }
}
