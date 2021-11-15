package com.github.papermcplugin_guiapi.gui.object;

import org.bukkit.inventory.ItemStack;

/**
 * A GuiObject wich have an own ItemStack
 *
 * @author
 */
public class CustomGuiObject extends GuiObject {

    private final ItemStack itemStack;

    public CustomGuiObject(ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    /**
     * Get the ItemStack in the InventoryGui
     *
     * @return
     */
    @Override
    public ItemStack getItemStack() {
        return  this.itemStack;
    }

}
