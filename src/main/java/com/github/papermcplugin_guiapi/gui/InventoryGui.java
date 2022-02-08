package com.github.papermcplugin_guiapi.gui;

import com.github.papermcplugin_guiapi.gui.collection.GuiCollection;
import com.github.papermcplugin_guiapi.gui.object.GuiObject;
import com.github.papermcplugin_guiapi.listener.InventoryListener;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

/**
 * Create a Gui in the Player Inventory
 *
 * @author F-Jan
 */
public class InventoryGui {

    private final int height;
    private final Component title;

    private final Inventory inventory;
    private final GuiObject[] guiObjects;
    private final GuiCollection[] guiCollections;

    public InventoryGui(int height, Component title) {
        this.height = height;
        this.title = title;

        inventory = Bukkit.createInventory(null, height * 9, title);

        InventoryListener.guiInventoryMap.put(inventory, this);

        guiObjects = new GuiObject[9 * height];
        guiCollections = new GuiCollection[9 * height];
    }

    /**
     * Shows the InventoryGui to a Player
     *
     * @param player
     */
    public void showPlayer(Player player) {
        player.openInventory(inventory);
    }

    /**
     * Add a Collection to the Gui
     *
     * @param guiCollection
     * @param page
     */
    public void addGuiCollection(GuiCollection guiCollection, int page) {
        guiCollection.print(this, page);
    }

    /**
     * Add an Object to the Gui
     *
     * @param slot
     * @param guiObject
     */
    public void addGuiObject(int slot, GuiObject guiObject) {
        guiObjects[slot] = guiObject;
        inventory.setItem(slot, guiObject.getItemStack());
    }

    /**
     * Call to trigger a click Event
     *
     * @param slot
     * @param player
     */
    public void callClickEvent(int slot, Player player) {
        GuiObject guiObject = guiObjects[slot];
        GuiCollection guiCollection = guiCollections[slot];

        if (guiObject != null) {
            guiObject.callClickEvents(player);
        }
        if (guiCollection != null) {
            guiCollection.callClickEvents(slot, this);
        }
    }

    /**
     * Get the Invenotry of the InventoryGui
     *
     * @return
     */
    public Inventory getInventory() {
        return inventory;
    }

    /**
     * Get the Title of the InventoryGui
     *
     * @return
     */
    public Component getTitle() {
        return title;
    }

    /**
     * Get the height (always 9 Slots)
     *
     * @return
     */
    public int getHeight() {
        return height;
    }

    /**
     * Get a List of all GuiObjects of the InventoryGui
     *
     * @return
     */
    public GuiObject[] getGuiObjects() {
        return guiObjects;
    }
}
