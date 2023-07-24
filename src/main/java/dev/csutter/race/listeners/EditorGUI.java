package dev.csutter.race.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import dev.csutter.race.models.Course;
import dev.csutter.race.utils.CourseUtils;

/*
 * Editor GUI
 * 
 * It makes the task bar have 3 buttons (Add checkpoint, Remove last checkpoint, exit)
 * 
 * When you hit E it makes the inventory a list of paged checkpoints that could be added or removed
 */
public class EditorGUI implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent e) {

        if (!e.getView().getTitle().contains("Editor")) return;

        if (e.isLeftClick()) {
            ItemStack item = e.getCurrentItem() != null ? e.getCurrentItem() : e.getCursor();
            
            int endingSlot = e.getSlot();
            int startingSlot = Integer.parseInt(item.getItemMeta().getDisplayName().split(": ")[1]);
            
            if (endingSlot == startingSlot) return;
            
            Course course = CourseUtils.getCourse(e.getView().getTitle().split(": ")[1]);
            
            CourseUtils.reorderCheckpoints(course, startingSlot, endingSlot);
        } else if (e.getClick() == ClickType.DROP) {
            // Cancel it
            e.setCancelled(true);

            // Get Item
            ItemStack item = e.getInventory().getItem(e.getSlot());

            // Remove it
            int slot = Integer.parseInt(item.getItemMeta().getDisplayName().split(": ")[1]);
            Course course = CourseUtils.getCourse(e.getView().getTitle().split(": ")[1]);
            
            CourseUtils.deleteCheckpoint(course, slot);
            e.getInventory().remove(item);
        }
    }
        
}
