package dev.csutter.race.commands;

import java.util.ArrayList;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import dev.csutter.race.Plugin;
import dev.csutter.race.models.ActiveRace;
import dev.csutter.race.models.Course;
import dev.csutter.race.models.Racer;
import dev.csutter.race.runables.Countdown;
import dev.csutter.race.runables.RaceLoop;
import dev.csutter.race.utils.CourseUtils;
import dev.csutter.race.utils.RaceUtils;

/*
 * Start Race Command will tp the players to the starting checkpoint
 * Make it so they can't move for the count down
 * After the count down they can move
 * Also starts a loop/thread to keep track of the checkpoints
 */
public class StartRaceCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage("You must be a player to use this command");
            return true;
        }

        if (args.length != 1) return false;
        
        Course course = CourseUtils.getCourse(args[0]);
        if (course == null) {
            sender.sendMessage("Couldn't find the course you were looking for");
            return false;
        }
        
        ActiveRace race = RaceUtils.getRaceByCourse(course);
        if (race == null) {
            sender.sendMessage("Couldn't find the race you were looking for");
            return false;
        }

        ArrayList<Racer> racers = race.getRacers();
        
        // freeze them
        // count down
        int countdown = 5;
        new Countdown(countdown, racers).runTaskTimer(Plugin.getPlugin(), 10, 20);
        
        // then unfreeze them
        // start some kind of loop to keep check to see if player are near a checkpoint
        
        new RaceLoop(course, racers).runTaskTimer(Plugin.getPlugin(), (20*countdown)+10, 2);


        return true;
    }
}