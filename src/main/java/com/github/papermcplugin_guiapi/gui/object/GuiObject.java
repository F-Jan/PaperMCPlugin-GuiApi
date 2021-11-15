package com.github.papermcplugin_guiapi.gui.object;

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

    /**
     * Add an Event which gets triggered when the Gui is Clicked
     *
     * @param event
     */
    public void addClickEvent(GuiObjectClickEvent event) {
        clickEventList.add(event);
    }

    /**
     * Call to trigger a click Event
     *
     * @param player
     */
    public void callClickEvents(Player player) {
        for (GuiObjectClickEvent event : clickEventList) {
            event.run(player);
        }
    }

    /**
     * Get the ItemStack in the InventoryGui
     *
     * @return
     */
    public ItemStack getItemStack() {
        return new ItemStack(Material.BARRIER);
    }
}
