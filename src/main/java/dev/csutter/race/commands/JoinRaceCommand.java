package dev.csutter.race.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import dev.csutter.race.models.ActiveRace;
import dev.csutter.race.models.Course;
import dev.csutter.race.models.Racer;
import dev.csutter.race.utils.CourseUtils;
import dev.csutter.race.utils.RaceUtils;

public class JoinRaceCommand implements CommandExecutor {

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

        race.addRacer(new Racer(race.getCourse().getCheckpoints(), (Player) sender));

        return true;
    }
}