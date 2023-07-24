package dev.csutter.race.commands;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import dev.csutter.race.models.Checkpoint;
import dev.csutter.race.models.Course;
import dev.csutter.race.utils.CourseUtils;

/*
 * Editor Command creates an (gui)inventory that makes it easy to add, remove, and reorder checkpoints
 * 
 * Make it simple like xClaim
 */
public class EditorCommand implements CommandExecutor {

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
        
        if (!CourseUtils.checkIfOwner(course, (Player) sender)) {
            sender.sendMessage("You must be the player who created the track");
            return false;
        }

        Inventory editor = makeInventory((Player) sender, course);

        ((Player) sender).openInventory(editor);


        return true;
    }

    public Inventory makeInventory(Player p, Course c) {

        ArrayList<Checkpoint> checkpoints = c.getCheckpoints();
        
        int numberOfColumns = (int)(checkpoints.size() / 9)+1;

        if (numberOfColumns >= 6) {
            numberOfColumns  = 6;
        }

        Inventory i = Bukkit.createInventory((InventoryHolder) p, numberOfColumns*9, "Editor for course: " + c.getName());

        ItemStack item;
        ItemMeta itemMeta;
        ArrayList<String> itemLore;
        for (Checkpoint checkpoint : checkpoints) {
            item = new ItemStack(Material.BLUE_BANNER, 1);
            itemMeta = item.getItemMeta();

            itemLore = new ArrayList<>();
            itemLore.add("X: " + checkpoint.getX());
            itemLore.add("Y: " + checkpoint.getY());
            itemLore.add("Z: " + checkpoint.getZ());

            itemMeta.setDisplayName("Checkpoint: " + checkpoint.getNumber());
            itemMeta.setLore(itemLore);
            item.setItemMeta(itemMeta);

            i.addItem(item);
        }
        
        return i;
    }
    
}
