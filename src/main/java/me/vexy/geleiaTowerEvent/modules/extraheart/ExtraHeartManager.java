package me.vexy.geleiaTowerEvent.modules.extraheart;

import me.vexy.geleiaTowerEvent.GeleiaTowerEvent;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Arrays;
import java.util.List;

/**
 * @author Rok, Pedro Lucas nmm. Created on 27/10/2024
 * @project GeleiaTowerEvent
 */
public class ExtraHeartManager {

    private final PotionEffect potionEffect;

    public ExtraHeartManager(GeleiaTowerEvent plugin) {

        potionEffect = new PotionEffect(PotionEffectType.ABSORPTION, 300 * 20, 3, false, false, false);
        Bukkit.getScheduler().runTaskTimer(plugin, () -> {
            // ignora o formato dessa lista, o certo é List<Player> list = ...
            List<? extends Player> list = Bukkit.getOnlinePlayers().stream().filter(p -> p.getGameMode().equals(GameMode.SURVIVAL)).toList();
            if (list.isEmpty()) return;
            for (Player player : list) {
                verifyPlayer(player);
            }
        }, 0, 60); // a cada meio segundo vai repetir esse comando
    }


    private void verifyPlayer(Player player) {
        List<ItemStack> list1 = Arrays.stream(player.getInventory().getContents()).filter(item -> item != null && item.getType().equals(Material.FERMENTED_SPIDER_EYE)).toList();
        if (list1.isEmpty()) return;

        for (ItemStack item : list1) {
            if (!item.hasItemMeta()) continue;
            if (!item.getItemMeta().hasCustomModelData()) continue;
            if (item.getItemMeta().getCustomModelData() != 1) continue;
            player.addPotionEffect(potionEffect);
        }
    }
}
