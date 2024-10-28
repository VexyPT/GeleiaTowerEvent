package me.vexy.geleiaTowerEvent.modules.sneakexplode;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.inventory.ItemStack;

/**
 * @author Rok, Pedro Lucas nmm. Created on 27/10/2024
 * @project GeleiaTowerEvent
 */
public class SneakExplodeEvents implements Listener {

    private final SneakExplodeManager manager;

    public SneakExplodeEvents(SneakExplodeManager manager) {
        this.manager = manager;
    }

    @EventHandler
    public void onPlayerCrouch(PlayerToggleSneakEvent event) {
        Player player = event.getPlayer();
        if (!manager.enabled) return;

        if (event.isSneaking() && player.getGameMode().equals(GameMode.SURVIVAL)) {
            Location location = player.getLocation();
            player.getWorld().createExplosion(location, 2.0F);
            player.sendMessage("Você explodiu por agachar!");
        }
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (!event.getAction().isRightClick()) return;
        ItemStack item = event.getItem();
        if (item == null) return;
        if (!item.getType().equals(Material.SLIME_BALL)) return;
        if (!item.hasItemMeta()) return;
        if (!item.getItemMeta().hasCustomModelData()) return;
        if (item.getItemMeta().getCustomModelData() != 1) return;
        manager.alterEnabled();
        event.getPlayer().sendMessage("§eExplosão por agachar " + (manager.enabled ? "§aativada" : "§cdesativada") + "§e!");
        event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_NOTE_BLOCK_BIT, 1, 1);
        event.setCancelled(true);
    }
}
