package io.github.f_jan.papermcplugin_guiapi;

import io.github.f_jan.papermcplugin_guiapi.gui.hologram.HologramGui;
import io.github.f_jan.papermcplugin_guiapi.listener.InventoryListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * The Main class of the Api.
 * Don't use this for your own plugin!
 *
 * @author F-Jan
 */
public final class PaperMcPluginGuiApi extends JavaPlugin {

    /**
     * The startup logic of the plugin.
     *
     * The InventoryListener get registered here.
     */
    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(new InventoryListener(), this);
    }

    /**
     * The shutdown logic of the plugin
     *
     * Delete the Armorstands created by this Plugin
     */
    @Override
    public void onDisable() {
        for (HologramGui hologramGui : HologramGui.HOLOGRAM_GUI_LIST) {
            hologramGui.clear();
        }
    }

}
