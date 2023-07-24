package dev.csutter.race.models;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;

public class Checkpoint {
    double x;
    double y;
    double z;
    int number;
    boolean displaing = false;
    String world;


    public Checkpoint(double x, double y, double z, int number, String world) {
        this.x = x;
        this.y = y;
        this.z = z;

        this.number = number;
        this.world = world;
    }

    public double getX() { return x; }
    public double getY() { return y; }
    public double getZ() { return z; }
    public int getNumber() { return number; }

    public void display() {
        // spawn a Shrieker Particle at on the checkpoint
        Bukkit.getWorld(world).spawnParticle(Particle.SHRIEK, new Location(Bukkit.getWorld(world), x, y, z), 1, 0);
    }

    public Location getLocation() {
        return new Location(Bukkit.getWorld(world), x, y, z);
    }
}
