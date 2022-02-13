package com.github.papermcplugin_guiapi.gui.collection;

import com.github.papermcplugin_guiapi.event.GuiCollectionClickEvent;
import com.github.papermcplugin_guiapi.gui.InventoryGui;
import com.github.papermcplugin_guiapi.gui.location.GuiLocation;
import com.github.papermcplugin_guiapi.gui.object.GuiObject;
import com.github.papermcplugin_guiapi.gui.object.LightBluePlaceHolderGuiObject;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.inventory.ItemStack;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class GuiCollection {

    private final int slot;
    private final int height;
    private final int width;

    private GuiObject placeHolderGuiObject;

    private final List<GuiCollectionClickEvent> collectionClickEventList = new ArrayList<>();

    private final Map<GuiLocation, GuiObject> guiObjectMap = new HashMap<>();

    public GuiCollection(int slot, int height, int width, ArrayList<GuiObject> guiObjects) {
        this.slot = slot;
        this.height = height;
        this.width = width;
        this.placeHolderGuiObject = new LightBluePlaceHolderGuiObject();

        int maxSizePerPage = height * width;
        int pages = maxSizePerPage / guiObjects.size();

        List<GuiLocation> guiLocations = new ArrayList<>();

        for (int page = 1; page <= pages; page++) {
            for (int temp_height = 1; temp_height <= height; temp_height++) {
                for (int temp_width = 1; temp_width <= width; temp_width++) {
                    guiLocations.add(new GuiLocation(temp_width, temp_height, page));
                }
            }
        }

        int i = 0;

        for (GuiObject guiObject : guiObjects) {
            guiObjectMap.put(guiLocations.get(i), guiObject);
            i++;
        }
    }

    public GuiCollection(int slot, int height, int width) {
        this.slot = slot;
        this.height = height;
        this.width = width;
        this.placeHolderGuiObject = new LightBluePlaceHolderGuiObject();
    }

    public GuiCollection(int slot, int height, int width, GuiObject placeHolderGuiObject) {
        this.slot = slot;
        this.height = height;
        this.width = width;
        this.placeHolderGuiObject = placeHolderGuiObject;
    }

    /**
     * Print the GuiCollection into a InventoryGui
     *
     * @param inventoryGui
     * @param page
     */
    public void print(InventoryGui inventoryGui, int page) {

        int temp_slot = slot;

        for (int temp_height = 1; temp_height <= height; temp_height++) {
            for (int temp_width = 1; temp_width <= width; temp_width++) {

                int finalTemp_width = temp_width;
                int finalTemp_height = temp_height;
                int finalTemp_slot = temp_slot;
                AtomicBoolean found = new AtomicBoolean(false);

                guiObjectMap.forEach((guiLocation, guiObject) -> {
                    if (guiLocation.getX() == finalTemp_width && guiLocation.getY() == finalTemp_height && guiLocation.getPage() == page) {
                        found.set(true);
                        inventoryGui.addGuiObject(finalTemp_slot, guiObject);
                    }
                });

                if (!found.get()) {
                    inventoryGui.addGuiObject(temp_slot, placeHolderGuiObject);
                }

                temp_slot++;
            }

            temp_slot = ((height - 1) * 9) + slot;
        }
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
        guiObjectMap.forEach((guiLocation, guiObject) -> {
            if (guiObject == guiObjectToRemove) {
                guiObjectMap.remove(guiLocation);
            }
        });
    }

    /**
     * Set the PlaceHolderGuiObject for the GuiCollection
     *
     * @param placeHolderGuiObject
     */
    public void setPlaceHolderGuiObject(GuiObject placeHolderGuiObject) {
        this.placeHolderGuiObject = placeHolderGuiObject;
    }

    /**
     * Sort the GuiObjects after Material
     */
    public void sortByMaterial() {
        ArrayList<GuiObject> guiObjects = new ArrayList<>(getGuiObjects());
        guiObjects.sort(Comparator.comparing(guiObject -> guiObject.getItemStack().getType().toString()));

        int maxSizePerPage = height * width;
        int pages = maxSizePerPage / guiObjects.size();

        List<GuiLocation> guiLocations = new ArrayList<>();

        for (int page = 1; page <= pages; page++) {
            for (int temp_height = 1; temp_height <= height; temp_height++) {
                for (int temp_width = 1; temp_width <= width; temp_width++) {
                    guiLocations.add(new GuiLocation(temp_width, temp_height, page));
                }
            }
        }

        int i = 0;

        guiObjectMap.clear();

        for (GuiObject guiObject : guiObjects) {
            guiObjectMap.put(guiLocations.get(i), guiObject);
            i++;
        }
    }

    /**
     * Add a GuiObject by a Location to the GuiCollection
     *
     * @param guiLocation
     * @param guiObject
     */
    public void addGuiObject(GuiLocation guiLocation, GuiObject guiObject) {
        guiObjectMap.put(guiLocation, guiObject);
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
        return guiObjectMap.values().stream().toList();
    }

    /**
     * Get a List of GuiObjects from a Single page of the Collection
     *
     * @param page
     * @return
     */
    public List<GuiObject> getGuiObjectsByPage(int page) {

        List<GuiObject> guiObjects = new ArrayList<>();

        guiObjectMap.forEach((guiLocation, guiObject) -> {
            if (guiLocation.getPage() == page) {
                guiObjects.add(guiObject);
            }
        });

        return guiObjects;
    }
}
