package com.github.papermcplugin_guiapi.gui.collection;

import com.github.papermcplugin_guiapi.event.GuiCollectionClickEvent;
import com.github.papermcplugin_guiapi.gui.InventoryGui;
import com.github.papermcplugin_guiapi.gui.object.GuiObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GuiCollection {

    private final int slot;
    private final int height;
    private final int width;

    private final int maxSizePerPage;

    private final List<GuiCollectionClickEvent> collectionClickEventList = new ArrayList<>();
    private final List<GuiObject> guiObjects;

    public GuiCollection(int slot, int height, int width, ArrayList<GuiObject> guiObjects) {
        this.slot = slot;
        this.height = height;
        this.width = width;
        this.guiObjects = guiObjects;

        maxSizePerPage = height * width;
    }

    /**
     * Get the height (y)
     *
     * @return
     */
    public int getHeight() {
        return height;
    }

    /**
     * Get the width (x)
     *
     * @return
     */
    public int getWidth() {
        return width;
    }

    /**
     * Call to trigger a click Event
     *
     * @param slot
     * @param inventoryGui
     */
    public void callClickEvents(int slot, InventoryGui inventoryGui) {
        for (GuiCollectionClickEvent event : collectionClickEventList) {
            event.run(inventoryGui.getGuiObjects()[slot]);
        }
    }

    /**
     * Add a Click Event
     *
     * @param event
     */
    public void addClickEvent(GuiCollectionClickEvent event) {
        collectionClickEventList.add(event);
    }

    /**
     * Remove a GuiObject form the GuiCollection
     *
     * @param guiObjectToRemove
     */
    public void removeGuiObject(GuiObject guiObjectToRemove) {
        guiObjects.remove(guiObjectToRemove);
    }

    /**
     * Calculate a slot of the collection to an actual slot of an Inventory
     *
     * @param collectionSlot
     * @return
     */
    public int collectionSlotToActualSlot(int collectionSlot) {
        int frontX = slot;

        if (frontX > 8) {
            while (frontX > 8) {
                frontX -= 9;
            }
        }

        int backX = 9 - (frontX + width);

        int currentSlot = slot + 1;

        while (collectionSlot > width - 1) {
            currentSlot += width + frontX + backX;
            collectionSlot -= width;
        }
        return (currentSlot + collectionSlot) - 1;
    }

    /**
     * Get a List of all GuiObjects of the Collection
     *
     * @return
     */
    public List<GuiObject> getGuiObjects() {
        return guiObjects;
    }

    /**
     * Get a List of GuiObjects from a Single page of the Collection
     *
     * @param page
     * @return
     */
    public List<GuiObject> getGuiObjectsByPage(int page) {

        List<GuiObject> guiObjects = this.guiObjects;
        GuiObject[] guiObjectsArray = guiObjects.toArray(new GuiObject[maxSizePerPage]);

        guiObjectsArray = Arrays.copyOfRange(guiObjectsArray, ((page - 1) * maxSizePerPage), page * maxSizePerPage);

        guiObjects = new ArrayList<>();

        for (GuiObject guiObject : guiObjectsArray) {
            if (guiObject != null) {
                guiObjects.add(guiObject);
            }
        }

        return guiObjects;
    }
}
