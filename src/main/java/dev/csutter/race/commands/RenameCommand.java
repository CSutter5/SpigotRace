package dev.csutter.race.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import dev.csutter.race.models.Course;
import dev.csutter.race.utils.CourseUtils;

public class RenameCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage("You must be a player to use this command");
            return true;
        }

        if (args.length != 2) return false;

        Course course = CourseUtils.getCourse(args[0]);
        if (course == null) {
            sender.sendMessage("Couldn't find the course you were looking for");
            return false;
        }
        
        if (!CourseUtils.checkIfOwner(course, (Player) sender)) {
            sender.sendMessage("You must be the player who created the track");
            return false;
        }

        CourseUtils.renameCourse(course, args[1]);
        sender.sendMessage("Course has been successfully renamed");


        return true;
    }
}