package io.github.f_jan.papermcplugin_guiapi.event;

import io.github.f_jan.papermcplugin_guiapi.gui.inventory.object.GuiObject;

/**
 * An Interface to define what happens when you Click the GuiCollection to which the event was added.
 *
 * @author F-Jan
 */
public interface GuiCollectionClickEvent {

    /**
     * The content that get executed when call the Event.
     */
    void run(GuiObject guiObject);

}
