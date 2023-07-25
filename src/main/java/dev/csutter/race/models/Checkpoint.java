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

    // https://www.spigotmc.org/threads/make-a-player-look-at-specific-block.492925/
    public Location lookAt(Location lookat) {
        //Clone the loc to prevent applied changes to the input loc
        Location loc = getLocation();

        // Values of change in distance (make it relative)
        double dx = lookat.getX() - loc.getX();
        double dy = lookat.getY() - loc.getY();
        double dz = lookat.getZ() - loc.getZ();

        // Set yaw
        if (dx != 0) {
            // Set yaw start value based on dx
            if (dx < 0) {
                loc.setYaw((float) (1.5 * Math.PI));
            } else {
                loc.setYaw((float) (0.5 * Math.PI));
            }
            loc.setYaw((float) loc.getYaw() - (float) Math.atan(dz / dx));
        } else if (dz < 0) {
            loc.setYaw((float) Math.PI);
        }

        // Get the distance from dx/dz
        double dxz = Math.sqrt(Math.pow(dx, 2) + Math.pow(dz, 2));

        // Set pitch
        loc.setPitch((float) -Math.atan(dy / dxz));
        // Set values, convert to degrees (invert the yaw since Bukkit uses a different yaw dimension format)
        loc.setYaw(-loc.getYaw() * 180f / (float) Math.PI);
        loc.setPitch(loc.getPitch() * 180f / (float) Math.PI);

        loc.setX(x);
        loc.setY(y);
        loc.setZ(z);
        
        return loc;
    }
}
