package dev.csutter.race.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import dev.csutter.race.utils.CourseUtils;

public class CreateCourseCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage("You must be a player to use this command");
            return true;
        }

        if (args.length != 1) return false;

        if (CourseUtils.createCourse(args[0], (Player) sender) == null) {
            sender.sendMessage("Course already created with that name");
            return false;
        }
        
        sender.sendMessage("Created Course");

        return true;
    }

}