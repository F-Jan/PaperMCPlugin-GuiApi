package io.github.f_jan.papermcplugin_guiapi.event;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 * An Interface to define what happens when you Drop an Item into the GuiObject to which the event was added.
 *
 * @author F-Jan
 */
public interface GuiObjectPlaceEvent {

    /**
     * The content that get executed when call the Event.
     */
    void run(Player clickedPlayer, ItemStack itemStack);

}
