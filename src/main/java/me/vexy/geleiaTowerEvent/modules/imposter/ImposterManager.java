package me.vexy.geleiaTowerEvent.modules.imposter;

import me.vexy.geleiaTowerEvent.GeleiaTowerEvent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.*;

/**
 * @author Rok, Pedro Lucas nmm. Created on 27/10/2024
 * @project GeleiaTowerEvent
 */
public class ImposterManager {

    private Random random;

    private final ItemStack bow;
    private final ItemStack arrow;

    public ImposterManager(GeleiaTowerEvent main) {
        random = new Random();
        bow = new ItemStack(Material.BOW);
        arrow = new ItemStack(Material.ARROW);
        arrow.setAmount(5);
        main.getServer().getPluginManager().registerEvents(new ImposterEvents(), main);
    }

    public void selectImposters() {
        List<Player> imposters = selectRandomImposter();
        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            for (Player imposter : imposters) {
                imposter.setGlowing(true);
                try {
                    GeleiaTowerEvent.get().getGlowingEntities().setGlowing(imposter, onlinePlayer, ChatColor.RED);
                } catch (Exception e) {
                    GeleiaTowerEvent.LOGGER.error("Error while setting glowing to {} for {}", imposter.getName(), onlinePlayer.getName());
                }
                executeToPlayer(imposter);
            }
        }
    }

    private void executeToPlayer(Player player) {
        player.sendMessage("§cVocê é um impostor!");
        player.sendMessage("§cDerrube outros jogadores!");
        player.getInventory().addItem(bow, arrow);
    }


    private List<Player> selectRandomImposter() {
        List<Player> imposters = new ArrayList<>();
        List<Player> onlinePlayers = new ArrayList<>(Bukkit.getOnlinePlayers().stream().filter(player -> player.getGameMode().equals(GameMode.SURVIVAL)).toList());
        int tries = 0;
        while (imposters.size() < 5 && tries < 20) {
            tries++;
            Player player = onlinePlayers.get(random.nextInt(onlinePlayers.size()));
            if (imposters.contains(player)) continue;
            if (GeleiaTowerEvent.get().isOp(player)) continue;
            imposters.add(player);
        }
        return imposters;
    }
}
