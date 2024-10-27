package me.vexy.geleiaTowerEvent.modules.joker;

import me.vexy.geleiaTowerEvent.GeleiaTowerEvent;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.event.HoverEvent;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.List;

/**
 * @author Rok, Pedro Lucas nmm. Created on 27/10/2024
 * @project GeleiaTowerEvent
 */
public class JokerManager {

    public JokerManager(GeleiaTowerEvent plugin) {
        Bukkit.getScheduler().runTaskTimer(plugin, () -> {
            // ignora o formato dessa lista, o certo é List<Player> list = ...
            List<? extends Player> list = Bukkit.getOnlinePlayers().stream().filter(p -> p.getGameMode().equals(GameMode.SURVIVAL)).toList();
            if (list.isEmpty()) return;
            for (Player player : list) {
                verifyPlayer(player);
            }
        }, 0, 10); // a cada meio segundo vai repetir esse comando
    }


    private void verifyPlayer(Player player) {
        List<ItemStack> list1 = Arrays.stream(player.getInventory().getContents()).filter(item -> item != null && item.getType().equals(Material.RED_DYE)).toList();
        if (list1.isEmpty()) return;
        boolean hasJoker = false;
        for (ItemStack item : list1) {
            if (item.hasItemMeta()) continue;
            if (item.getItemMeta().hasCustomModelData()) continue;
            item.editMeta(meta -> {
                meta.setCustomModelData(1);
                meta.displayName(Component.text("§d§lCoringa"));
                meta.setLore(Arrays.asList("§r", "§cParabéns, você ganhou um coringa", "§co Geleia está vindo!")); // Usando assim só pq tô com preguiça de fazer com component
            });
            hasJoker = true;
        }
        if (!hasJoker) return;
        Player geleiaPlayer = Bukkit.getPlayer("GeleiaPlays");
        if (geleiaPlayer == null) return;
        if (!geleiaPlayer.isOnline()) return;
        TextComponent textComponent = Component.text("§d§l>> §n" + player.getName() + "§r§d recebeu um coringa.")
                .clickEvent(ClickEvent.clickEvent(ClickEvent.Action.RUN_COMMAND, "/tp " + player.getName()))
                .hoverEvent(HoverEvent.showText(Component.text("§7Clique para teleportar até o jogador.")));
        geleiaPlayer.sendMessage(textComponent);
    }
}
