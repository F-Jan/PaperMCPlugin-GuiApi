package com.github.papermcplugin_guiapi.gui.inventory.object;

import com.github.papermcplugin_guiapi.event.GuiObjectPlaceEvent;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

/**
 * A GuiObject to wich ItemStacks can be added
 *
 * @author F-Jan
 */
public class PlacementGuiObject extends GuiObject {

    private final List<GuiObjectPlaceEvent> guiObjectPlaceEventList = new ArrayList<>();

    /**
     * Add an empty Slot as ItemStack in the InventoryGui
     *
     * @return
     */
    @Override
    public ItemStack getItemStack() {
        return new ItemStack(Material.AIR);
    }

    /**
     * Trigger the PlaceEvent of the Object
     *
     * @param player
     * @param itemStack
     */
    public void callPlaceEvents(Player player, ItemStack itemStack) {
        for (GuiObjectPlaceEvent event : guiObjectPlaceEventList) {
            event.run(player, itemStack);
        }
    }

    /**
     * Add an Event which get triggered when the Player place an ItemStack Into this GuiObject
     *
     * @param event
     */
    public void addGuiObjectPlaceEvent(GuiObjectPlaceEvent event) {
        guiObjectPlaceEventList.add(event);
    }

}
