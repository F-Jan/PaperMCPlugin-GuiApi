package io.github.f_jan.papermcplugin_guiapi.event;

import org.bukkit.entity.Player;

/**
 * An Interface to define what happens when you Click the GuiObject to which the event was added.
 *
 * @author F-Jan
 */
public interface GuiObjectClickEvent {

    /**
     * The content that get executed when call the Event.
     */
    void run(Player clickedPlayer);

}
