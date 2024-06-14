package dev.csutter.race.runables;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.scheduler.BukkitRunnable;

import dev.csutter.race.Plugin;
import dev.csutter.race.models.Checkpoint;
import dev.csutter.race.models.Course;
import dev.csutter.race.models.Racer;
import dev.csutter.race.utils.RaceUtils;

public class RaceLoop extends BukkitRunnable {

    Course course;
    ArrayList<Racer> racers;
    ArrayList<Racer> racersDone;
    int numberOfCheckPoints;
    Racer winner = null;

    boolean hasStarted = false;

    public RaceLoop(Course course, ArrayList<Racer> racers) {
        this.course = course;
        this.racers = racers;

        numberOfCheckPoints = course.getCheckpoints().size();

        racersDone = new ArrayList<Racer>();
    }
    
    @Override
    public void run() {
        if (course.getCheckpoints().size() == 0) {
            Plugin.getLog().info("Tried to race on track with no checkpoints");
            this.cancel();
            return;
        }

        if (!hasStarted) {
            // tp them to the starting checkpoint
            Location firstCheckpoint = course.getCheckpoints().get(0).lookAt(course.getCheckpoints().get(1).getLocation());

            for (Racer p : racers)
                p.getPlayer().teleport(firstCheckpoint);

            hasStarted = true;
        }

        // When no one is left in the list the race is over
        if (racers.size() == 0) {
            // Print the winner
            Bukkit.broadcastMessage(winner.getPlayer().getDisplayName() + " Won the race");

            RaceUtils.DeleteRace(RaceUtils.getRaceByCourse(course));

            this.cancel();
        }

        // For every player check to see if its near a checkpoint
        for (Racer p : racers) {
            // Increment player's checkpoint
            if (p.getDistanceToNextCheckpoint() < 1.5)
                p.nextCheckpoint();

            // If player is on the last checkpoint, remove them from list
            if (p.getCheckpoint() == numberOfCheckPoints) {
                racersDone.add(p);
                
                // if they are the first one done store them in the first place var
                if (winner == null)
                    winner = p; 
            }
        }

        for (Racer r : racersDone) 
            if (racers.contains(r))
                racers.remove(r);

        for (Checkpoint c : course.getCheckpoints())
            c.display();
    }
}
