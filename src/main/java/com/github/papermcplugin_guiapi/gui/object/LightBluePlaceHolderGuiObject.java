package com.github.papermcplugin_guiapi.gui.object;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

/**
 * A Light-Blue-Stained-Glass-Pane as a Placeholder
 *
 * @author F-Jan
 */
public class LightBluePlaceHolderGuiObject extends GuiObject{

    /**
     * Get the ItemStack in the InventoryGui
     *
     * @return
     */
    @Override
    public ItemStack getItemStack() {
        ItemStack itemStack = new ItemStack(Material.LIGHT_BLUE_STAINED_GLASS_PANE);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.displayName(Component.empty());
        itemStack.setItemMeta(itemMeta);

        return  itemStack;
    }

}
