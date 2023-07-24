package dev.csutter.race;

import java.io.IOException;
import java.util.logging.Logger;

import org.bukkit.plugin.java.JavaPlugin;

import dev.csutter.race.commands.*;
import dev.csutter.race.listeners.EditorGUI;
import dev.csutter.race.utils.CourseUtils;

/*
 * Race Minecraft Spigot Plugin
 *
 * This plugin allows users to create race maps by placing checkpoints
 * It also allows people to race them either by horse, foot, boat, or elytras
 * Checks will be visable by seeing particles
 * 
 * It stores the races in a MySQL database
 * 
 * Commands
 *  Create Course 
 *  Place Checkpoint
 *  List Courses
 *  Create Race
 *  Start Race
 *  Join Race
 *  Stop Race
 */
public class Plugin extends JavaPlugin
{
    private static final Logger LOGGER=Logger.getLogger("Race");

    private static Plugin plugin;

    public void onEnable()
    {
        getCommand("CreateCourse").setExecutor(new CreateCourseCommand());
        getCommand("DeleteCourse").setExecutor(new DeleteCourseCommand());
        getCommand("AddCheckpoint").setExecutor(new AddCheckpointCommand());
        getCommand("ListCourses").setExecutor(new ListCoursesCommand());
        getCommand("CreateRace").setExecutor(new CreateRaceCommand());
        getCommand("StartRace").setExecutor(new StartRaceCommand());
        getCommand("JoinRace").setExecutor(new JoinRaceCommand());
        getCommand("LeaveRace").setExecutor(new LeaveRaceCommand());
        getCommand("StopRace").setExecutor(new StopRaceCommand());
        getCommand("ActiveRaces").setExecutor(new ActiveRaceCommand());
        getCommand("Editor").setExecutor(new EditorCommand());
        getCommand("Rename").setExecutor(new RenameCommand());

        getServer().getPluginManager().registerEvents(new EditorGUI(), this);

        plugin = this;
        
        try { CourseUtils.loadCourses(); } catch (IOException e) { LOGGER.info("Failed to load courses"); }

        LOGGER.info("enabled");
    }

    public void onDisable()
    {
        LOGGER.info("disabled");
    }

    public static Plugin getPlugin() { return plugin; }
    public static Logger getLog() { return LOGGER; }
}