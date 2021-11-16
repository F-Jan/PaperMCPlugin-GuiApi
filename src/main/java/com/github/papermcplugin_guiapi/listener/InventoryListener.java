package com.github.papermcplugin_guiapi.listener;

import com.github.papermcplugin_guiapi.gui.InventoryGui;
import com.github.papermcplugin_guiapi.gui.object.PlacementGuiObject;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

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
                InventoryGui inventoryGui = guiInventoryMap.get(event.getInventory());

                if (inventoryGui.getGuiObjects()[event.getRawSlot()] instanceof PlacementGuiObject guiObject) {
                    ItemStack itemStack = new ItemStack(Material.AIR);

                    if (event.getAction() == InventoryAction.PLACE_ONE) {
                        itemStack = event.getCursor().clone();
                        if (event.getCurrentItem() == null) {
                            itemStack.setAmount(1);
                        } else {
                            itemStack.setAmount(1 + event.getCurrentItem().getAmount());
                        }
                    } else if (event.getAction() == InventoryAction.PLACE_ALL || event.getAction() == InventoryAction.SWAP_WITH_CURSOR) {
                        itemStack = event.getCursor().clone();
                    }

                    guiObject.callPlaceEvents((Player) event.getWhoClicked(), itemStack.clone());
                } else {
                    event.setCancelled(true);
                }
                inventoryGui.callClickEvent(event.getRawSlot(), (Player) event.getWhoClicked());
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
