package me.vexy.geleiaTowerEvent.modules.gravity;

import me.vexy.geleiaTowerEvent.GeleiaTowerEvent;
import me.vexy.geleiaTowerEvent.modules.imposter.ImposterManager;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

/**
 * @author Rok, Pedro Lucas nmm. Created on 27/10/2024
 * @project GeleiaTowerEvent
 */
public class GravityEvents implements Listener {

    private final GravityManager gravityManager;

    public GravityEvents(GravityManager gravityManager) {
        this.gravityManager = gravityManager;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        if (gravityManager.gravity) {
            event.getPlayer().getAttribute(Attribute.GENERIC_GRAVITY).setBaseValue(gravityManager.gravityOn);
            return;
        }
        event.getPlayer().getAttribute(Attribute.GENERIC_GRAVITY).setBaseValue(gravityManager.gravityOff);
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (!event.getAction().isRightClick()) return;
        ItemStack item = event.getItem();
        if (item == null) return;
        if (!item.getType().equals(Material.REDSTONE)) return;
        if (!item.hasItemMeta()) return;
        if (!item.getItemMeta().hasCustomModelData()) return;
        if (item.getItemMeta().getCustomModelData() != 1) return;
        gravityManager.switchGravity(event.getPlayer());
    }

}
