package dev.csutter.race.models;

import java.util.ArrayList;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Vibration;
import org.bukkit.entity.Player;

public class Racer {

    ArrayList<Checkpoint> checkpoints;
    Player player;
    int currentCheckpoint = 0;

    public Racer(ArrayList<Checkpoint> checkpoints, Player player) {
        this.checkpoints = checkpoints;
        this.player = player;
    }

    public Checkpoint getNextCheckpoint() { return checkpoints.get(currentCheckpoint); }
    public Player getPlayer() { return player; }
    public int getCheckpoint() { return currentCheckpoint; }
    
    public void nextCheckpoint() { 
        currentCheckpoint++;

        if (currentCheckpoint >= checkpoints.size()) return;

        Location loc = checkpoints.get(currentCheckpoint).getLocation();

        player.sendMessage("Next Checkpoint at " + 
            loc.getX() + " " + 
            loc.getY() + " " + 
            loc.getZ());

        Vibration vibration = new Vibration(player.getLocation(), new Vibration.Destination.BlockDestination(loc), 50);
        player.getWorld().spawnParticle(Particle.VIBRATION, player.getLocation(), 1, vibration);
    }

    public double getDistanceToNextCheckpoint() {
        if (currentCheckpoint >= checkpoints.size()) return 100000;

        return player.getLocation().distance(checkpoints.get(currentCheckpoint).getLocation());
    }
}
