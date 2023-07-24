package dev.csutter.race.models;

import java.util.ArrayList;

import org.bukkit.entity.Player;

/*
 * Active Race holds info about
 *  What course is being raced on
 *  Who is in the race
 *  Who started the race
 *  Has it start or on going
 * 
 * It also has methods to start the race
 */
public class ActiveRace {
    Course activeCourse;

    Racer starter;
    ArrayList<Racer> racers;

    public ActiveRace(Course course, Racer starter) {
        racers = new ArrayList<>();
        activeCourse = course;

        this.starter = starter;
        racers.add(starter);
    } 

    public void addRacer(Racer racer) {
        if (racers.contains(racer)) {
            racer.getPlayer().sendMessage("You have already joined this race");
            return;
        }

        racers.add(racer);
        racer.getPlayer().sendMessage("You have join the race");
    }

    public void removeRacer(Player racer) {
        for (Racer r : racers)
            if (r.getPlayer() == racer) {
                racers.remove(r);
                racer.sendMessage("You have left the Race");

                break;
            }
            
        racer.getPlayer().sendMessage("You are already not in the race");
        return;
    }

    public Course getCourse() { return activeCourse; }
    public ArrayList<Racer> getRacers() { return racers; }
}
