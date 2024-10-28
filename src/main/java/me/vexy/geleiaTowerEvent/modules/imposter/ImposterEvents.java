package me.vexy.geleiaTowerEvent.modules.imposter;

import me.vexy.geleiaTowerEvent.GeleiaTowerEvent;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

/**
 * @author Rok, Pedro Lucas nmm. Created on 27/10/2024
 * @project GeleiaTowerEvent
 */
public class ImposterEvents implements Listener {


    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        if (!event.getPlayer().isGlowing()) return;
        if (GeleiaTowerEvent.get().isOp(event.getPlayer())) return;
        event.setDeathMessage(null);
        Player killer = event.getEntity().getKiller();

        if (killer != null) {
            Bukkit.broadcast(Component.text("§7[§c!§7] §c"+ killer.getName() + "§7 matou o impostor §c"+event.getPlayer().getName()+"§7!"));
            return;
        }
        Bukkit.broadcast(Component.text("§7[§c!§7] §c"+ event.getPlayer().getName() + "§7 morreu!"));
    }


    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (!event.getAction().isRightClick()) return;
        ItemStack item = event.getItem();
        if (item == null) return;
        if (!item.getType().equals(Material.HEART_OF_THE_SEA)) return;
        if (!item.hasItemMeta()) return;
        if (!item.getItemMeta().hasCustomModelData()) return;
        if (item.getItemMeta().getCustomModelData() != 1) return;
        ImposterManager imposterManager = GeleiaTowerEvent.get().getImposterManager();
        imposterManager.selectImposters();
        event.getPlayer().sendMessage("§aImpostores selecionados!");
        event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_NOTE_BLOCK_BIT, 1, 1);
        event.setCancelled(true);
    }
}
