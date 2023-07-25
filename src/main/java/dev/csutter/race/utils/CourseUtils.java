package dev.csutter.race.utils;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;

import org.bukkit.entity.Player;

import com.google.gson.Gson;

import dev.csutter.race.Plugin;
import dev.csutter.race.models.Checkpoint;
import dev.csutter.race.models.Course;

public class CourseUtils {

    // CRUD - Create, Read, Update, Delete

    private static ArrayList<Course> courses = new ArrayList<>();

    public static Course createCourse(String name, Player owner) {
        Course course = new Course(name, owner);

        for (Course c : courses) if (c.getName().equalsIgnoreCase(name)) return null;
        
        courses.add(course);
        try { saveCourses(); } catch (IOException e) { Plugin.getLog().info("Failed to save courses"); }

        return course;
    }

    public static void deleteCourse(String id) {
        for (Course c : courses)
            if (c.getID().equalsIgnoreCase(id))
            {
                courses.remove(c);

                try { saveCourses(); } catch (IOException e) { Plugin.getLog().info("Failed to save courses"); }

                return;
            }
    }

    public static Course getCourse(String name) {
        for (Course c : courses)
            if (c.getName().equalsIgnoreCase(name))
                return c;
        
        return null;
    }

    public static Course reorderCheckpoints(Course course, int startingPos, int endingPos) {

        ArrayList<Checkpoint> checkpoints = course.getCheckpoints();
        Checkpoint target = checkpoints.remove(startingPos);

        checkpoints.add(endingPos, target);
        course.setCheckpoints(checkpoints);

        try { saveCourses(); } catch (IOException e) { Plugin.getLog().info("Failed to save courses"); }

        return course;
    }

    public static Course deleteCheckpoint(Course course, int index) {
        
        ArrayList<Checkpoint> checkpoints = course.getCheckpoints();
        
        checkpoints.remove(index);
        course.setCheckpoints(checkpoints);

        try { saveCourses(); } catch (IOException e) { Plugin.getLog().info("Failed to save courses"); }

        return course;
    }

    public static boolean checkIfOwner(Course course, Player player) {
        return course.getOwner().equalsIgnoreCase(player.getName());
    }

    public static Course updateCourse(String id, Course newCourse) {
        for (Course c : courses)
            if (c.getID().equalsIgnoreCase(id)) {
                c.setCheckpoints(newCourse.getCheckpoints());
                c.setCourseName(newCourse.getName());

                try { saveCourses(); } catch (IOException e) { Plugin.getLog().info("Failed to save courses"); }

                return c;
            }

        return null;
    }

    public static Course renameCourse(Course course, String newName) {
        courses.remove(course);

        course.setCourseName(newName);
        courses.add(course);
        
        try { saveCourses(); } catch (IOException e) { Plugin.getLog().info("Failed to save courses"); }
        
        return course;
    }

    public static String getCourseNames() {
        String str = "";

        for (Course c : courses)
            str += c.getName() + ", ";

        if (str != null && str.length() > 0)
            str = str.substring(0, str.length() - 2);
        else
            str = "No courses found";

        return str;
    }

    public static void saveCourses() throws IOException {
        Gson gson = new Gson();
        File file = new File(Plugin.getPlugin().getDataFolder().getAbsoluteFile() + "/courses.json");
        file.getParentFile().mkdir();
        file.createNewFile();

        Writer writer = new FileWriter(file, false);
        gson.toJson(courses, writer);
        
        writer.flush();
        writer.close();

        Plugin.getLog().info("Saved courses");
    }

    public static void loadCourses() throws IOException {
        Gson gson = new Gson();
        File file = new File(Plugin.getPlugin().getDataFolder().getAbsoluteFile() + "/courses.json");
        
        if (!file.exists()) return;

        Reader reader = new FileReader(file);
        Course[] c = gson.fromJson(reader, Course[].class);

        if (c != null)
            courses = new ArrayList<>(Arrays.asList(c));

        Plugin.getLog().info("Courses Loaded");
    }
}
