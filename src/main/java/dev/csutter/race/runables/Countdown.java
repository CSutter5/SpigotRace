package dev.csutter.race.runables;

import java.util.ArrayList;

import org.bukkit.scheduler.BukkitRunnable;

import dev.csutter.race.models.Racer;

public class Countdown extends BukkitRunnable {

    int time;
    ArrayList<Racer> racers;

    public Countdown(int time, ArrayList<Racer> racers) {
        this.time = time;
        this.racers = racers;
    }

    @Override
    public void run() {
        for (Racer player : racers) {
            if (this.time != 0) {
                player.getPlayer().sendMessage("Race Starting in " + this.time);
            } else {
                player.getPlayer().sendMessage("Go Go Go!!!");
                this.cancel();
            }
        }
       
        this.time--;
    }
    
}
