package com.github.papermcplugin_guiapi.gui.inventory.object;

import com.github.papermcplugin_guiapi.event.GuiObjectClickEvent;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

/**
 * An Object that can displayed by an InventoryGui. But didn't use this class directly, but use a class which has extended GuiObject or make your own GuiObject.
 *
 * @author F-Jan
 */
public class GuiObject {

    private final List<GuiObjectClickEvent> clickEventList = new ArrayList<>();

    private final ItemStack itemStack;

    public GuiObject(ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    public GuiObject() {
        this.itemStack = new ItemStack(Material.BARRIER);
    }

    /**
     * Add an Event which gets triggered when the Gui is Clicked
     *
     * @param event The event to add
     */
    public void addClickEvent(GuiObjectClickEvent event) {
        clickEventList.add(event);
    }

    /**
     * Call to trigger a click Event
     *
     * @param player The Player who clicked the inventory
     */
    public void callClickEvents(Player player) {
        for (GuiObjectClickEvent event : clickEventList) {
            event.run(player);
        }
    }

    /**
     *
     * @return The ItemStack of the GuiObject
     */
    public ItemStack getItemStack() {
        return itemStack;
    }

}
