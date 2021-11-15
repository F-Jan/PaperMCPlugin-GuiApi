package com.github.papermcplugin_guiapi.listener;

import com.github.papermcplugin_guiapi.gui.InventoryGui;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.Inventory;

import java.util.HashMap;

/**
 * The Bukkit Listener for Inventories
 *
 * @author F-Jan
 */
public class InventoryListener implements Listener {

    public static HashMap<Inventory, InventoryGui> guiInventoryMap = new HashMap<>();

    /**
     * Code get triggered when an Inventory is Clicked
     *
     * @param event
     */
    @EventHandler
    public void onPlayerClickInventory(InventoryClickEvent event) {
        if (guiInventoryMap.containsKey(event.getInventory())) {
            if (event.getInventory().getSize() > event.getRawSlot()) {
                event.setCancelled(true);
                guiInventoryMap.get(event.getInventory()).callClickEvent(event.getRawSlot(), (Player) event.getWhoClicked());
            }
        }
    }

    /**
     * Code get triggered when an Item is dragged in an Inventory
     *
     * @param event
     */
    @EventHandler
    public void onPlayerDragInventory(InventoryDragEvent event) {
        if (guiInventoryMap.containsKey(event.getInventory())) {
            for (int slot: event.getRawSlots().toArray(new  Integer[event.getRawSlots().toArray().length])) {
                if (event.getInventory().getSize() > slot) {
                    event.setCancelled(true);
                    guiInventoryMap.get(event.getInventory()).callClickEvent(slot, (Player) event.getWhoClicked());
                }
            }
        }
    }

}
