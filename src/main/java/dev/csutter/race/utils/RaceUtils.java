package dev.csutter.race.utils;

import java.util.ArrayList;

import dev.csutter.race.models.ActiveRace;
import dev.csutter.race.models.Course;
import dev.csutter.race.models.Racer;

/*
 * Race Utils hold utilities to
 *  Create a race
 *  Join a race 
 *  Start/Stop and a race
 *  Display the checkpoints
 *  Record the time
 */
public class RaceUtils {
    private static ArrayList<ActiveRace> activeRaces = new ArrayList<>();

    public static ActiveRace CreateRace(Course course, Racer starter) {
        ActiveRace a = new ActiveRace(course, starter);

        activeRaces.add(a);

        return a;
    }

    public static void DeleteRace(ActiveRace course) {
        activeRaces.remove(course);
    }

    public static ActiveRace getRaceByCourse(Course course) {
        for (ActiveRace race : activeRaces)
            if (race.getCourse().equals(course))
               return race;

        return null;
    }

    public static String getActiveRaces() {
        String str = "";

        for (ActiveRace r : activeRaces)
            str += r.getCourse().getName() + ", ";

        if (str != null && str.length() > 0)
            str = "Races on courses: " + str.substring(0, str.length() - 2);
        else
            str = "No Races found";

        return str;
    }
}
