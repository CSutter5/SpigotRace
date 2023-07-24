package dev.csutter.race.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import dev.csutter.race.models.Course;
import dev.csutter.race.models.Racer;
import dev.csutter.race.utils.CourseUtils;
import dev.csutter.race.utils.RaceUtils;

public class CreateRaceCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage("You must be a player to use this command");
            return true;
        }

        if (args.length != 1) return false;

        Course c = CourseUtils.getCourse(args[0]);

        RaceUtils.CreateRace(c, new Racer(c.getCheckpoints(), (Player)sender));

        sender.sendMessage("Created Race");

        return true;
    }
}