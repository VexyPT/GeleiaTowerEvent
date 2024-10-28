package me.vexy.geleiaTowerEvent;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

/**
 * @author Rok, Pedro Lucas nmm. Created on 27/10/2024
 * @project GeleiaTowerEvent
 */
public class GeleiaEvents implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        if (e.getItem() == null) return;
        if (!e.getItem().hasItemMeta()) return;
        if (!e.getItem().getItemMeta().hasCustomModelData()) return;
        e.setUseItemInHand(PlayerInteractEvent.Result.DENY);
    }

}
