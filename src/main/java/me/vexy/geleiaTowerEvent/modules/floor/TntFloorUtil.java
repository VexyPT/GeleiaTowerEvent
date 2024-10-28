package me.vexy.geleiaTowerEvent.modules.floor;

import me.vexy.geleiaTowerEvent.GeleiaTowerEvent;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;

/**
 * @author Rok, Pedro Lucas nmm. Created on 27/10/2024
 * @project GeleiaTowerEvent
 */
public class TntFloorUtil {

    public static void setFloor(Player player) {
        World world = player.getWorld();

        int xMin = -125;
        int xMax = 125;

        int zMin = -125;
        int zMax = 125;

        int high = 99;

        Bukkit.getScheduler().runTask(GeleiaTowerEvent.get(), () -> {
            for (int x = xMin; x <= xMax; x++) {
                for (int z = zMin; z <= zMax; z++) {
                    world.getBlockAt(x, high, z).setType(Material.TNT);
                }
            }
        });
    }
}
