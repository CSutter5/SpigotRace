package dev.csutter.race.models;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.entity.Player;

/*
 * Holds list of checkpoints for the course
 * It can be imported, exported
 */
public class Course {
    String courseName;
    String id;
    String owner;
    String world;

    ArrayList<Checkpoint> checkpoints;

    public Course(String name, Player owner) {
        courseName = name;
        this.owner = owner.getName();
        this.world = owner.getLocation().getWorld().getName();
        
        id = UUID.randomUUID().toString(); 

        checkpoints = new ArrayList<>();
    }

    public void addCheckpoint(Location loc) {
        checkpoints.add(new Checkpoint(
                (int)loc.getX(), 
                (int)loc.getY(),
                (int)loc.getZ(),
                checkpoints.size(),
                world
        ));
    }

    public ArrayList<Checkpoint> getCheckpoints() { return checkpoints; }
    public String getName() { return courseName; }
    public String getID() { return id; }
    public String getOwner() { return owner; }
    public String getWorld() { return world; }

    public void setCheckpoints(ArrayList<Checkpoint> checkpoints) { this.checkpoints = checkpoints; }
    public void setCourseName(String courseName) { this.courseName = courseName; }
}