package com.github.papermcplugin_guiapi.gui.hologram;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;

import java.util.ArrayList;
import java.util.List;

/**
 * Create a Hologram with Armorstands by saying the Position the height and add the single lines
 *
 * @author F-Jan
 */
public class HologramGui {

    private final double x;
    private final double firstY;
    private final double z;

    private final List<String> lineList = new ArrayList<>();
    private final List<ArmorStand> armorStandList = new ArrayList<>();

    public final static List<HologramGui> HOLOGRAM_GUI_LIST = new ArrayList<>();

    public HologramGui(double x, double y, double z, int height) {
        this.x = x;
        this.firstY = (y - 2.2) + (height * 0.3);
        this.z = z;

        HOLOGRAM_GUI_LIST.add(this);
    }

    /**
     * Add a line to the Hologram
     */
    public void addLine(String message) {
        Location location = new Location(Bukkit.getWorld("world"), x, firstY - lineList.toArray().length * 0.3, z);

        ArmorStand armorStand = (ArmorStand) location.getWorld().spawnEntity(location, EntityType.ARMOR_STAND);
        armorStand.setCustomName(message);
        armorStand.setCustomNameVisible(true);
        armorStand.setInvisible(true);
        armorStand.setGravity(false);

        lineList.add(message);
        armorStandList.add(armorStand);
    }

    /**
     * Clear the Lines of the Hologram by deleting
     */
    public void clear() {
        for (ArmorStand armorStand: armorStandList) {
            armorStand.remove();
        }
    }

}
