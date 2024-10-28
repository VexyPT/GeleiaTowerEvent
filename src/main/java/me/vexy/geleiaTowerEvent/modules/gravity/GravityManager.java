package me.vexy.geleiaTowerEvent.modules.gravity;

import me.vexy.geleiaTowerEvent.GeleiaTowerEvent;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.title.Title;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;

/**
 * @author Rok, Pedro Lucas nmm. Created on 27/10/2024
 * @project GeleiaTowerEvent
 */

public class GravityManager {

    protected final float gravityOff = 0.01f;
    protected final float gravityOn = 0.08f;

    protected boolean gravity = true;

    public GravityManager(GeleiaTowerEvent main) {
        main.getServer().getPluginManager().registerEvents(new GravityEvents(this), main);
    }


    public void switchGravity(Player player) {
        gravity = !gravity;
        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            onlinePlayer.getAttribute(Attribute.GENERIC_GRAVITY).setBaseValue(gravity ? gravityOn : gravityOff);
            onlinePlayer.showTitle(Title.title(Component.text("§fGravidade " + (gravity ? "§aativada" : "§cdesativada")), Component.empty()));
            onlinePlayer.playSound(onlinePlayer.getLocation(), gravity ? Sound.BLOCK_BEACON_ACTIVATE : Sound.BLOCK_BEACON_DEACTIVATE, 1, 1);
        }
        player.sendMessage("§7[§e!§7] §fVocê " + (gravity ? "§aativou" : "§cdesativou") + " §fa gravidade!");
    }
}
