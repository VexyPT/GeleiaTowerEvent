package me.vexy.geleiaTowerEvent.modules.imposter;

import me.vexy.geleiaTowerEvent.GeleiaTowerEvent;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * @author Rok, Pedro Lucas nmm. Created on 27/10/2024
 * @project GeleiaTowerEvent
 */
public class ImposterCommand implements TabExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player p)) {
            sender.sendMessage("Apenas jogadores podem usar esse comando.");
            return true;
        }

        if (!GeleiaTowerEvent.get().isOp(p)) {
            p.sendMessage("§cVocê não tem permissão para usar esse comando.");
            return true;
        }

        ItemStack itemStack = new ItemStack(Material.HEART_OF_THE_SEA);
        itemStack.editMeta(meta -> {
            meta.setDisplayName("§cSeletor de Impostor");
            meta.setCustomModelData(1);
            meta.setLore(List.of("","§7Clique para selecionar 5 jogadores diferentes."));
        });

        p.getInventory().addItem(itemStack);
        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        return List.of();
    }
}
