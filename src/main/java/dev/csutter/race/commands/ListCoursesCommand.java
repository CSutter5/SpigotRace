package dev.csutter.race.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import dev.csutter.race.utils.CourseUtils;

public class ListCoursesCommand implements CommandExecutor {
    
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage("You must be a player to use this command");
            return true;
        }

        // Loop over every Course and print a list of courses by name
        sender.sendMessage(CourseUtils.getCourseNames());

        return true;
    }
}
