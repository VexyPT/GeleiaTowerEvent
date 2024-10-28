package me.vexy.geleiaTowerEvent.commands;

import me.vexy.geleiaTowerEvent.GeleiaTowerEvent;
import me.vexy.geleiaTowerEvent.modules.floor.TntFloorUtil;
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
public class GeleiaTowerCommand implements TabExecutor {
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
        if (args.length == 0) {
            p.sendMessage("§cUso: /geleiatower <imposter-item/gravity-item>");
            return true;
        }

        String action = args[0];

        switch (action) {
            case "imposter-item" -> {
                ItemStack itemStack = new ItemStack(Material.HEART_OF_THE_SEA);
                itemStack.editMeta(meta -> {
                    meta.setDisplayName("§cSeletor de Impostor");
                    meta.setCustomModelData(1);
                    meta.setLore(List.of("","§7Clique para selecionar 5 jogadores diferentes."));
                });

                p.getInventory().addItem(itemStack);
                p.sendMessage("§aItem de impostor adicionado ao seu inventário.");
                break;
            }
            case "gravity-item" -> {
                ItemStack itemStack = new ItemStack(Material.REDSTONE);
                itemStack.editMeta(meta -> {
                    meta.setDisplayName("§cAlternador de Gravidade");
                    meta.setCustomModelData(1);
                    meta.setLore(List.of("","§7Alternar a gravidade de todos os jogadores."));
                });

                p.getInventory().addItem(itemStack);
                p.sendMessage("§aItem de gravidade adicionado ao seu inventário.");
                break;
            }
            case "chao-de-tnt" -> {
                TntFloorUtil.setFloor(p);
                p.sendMessage("§aChão de TNT colocado.");
                break;
            }
            case "sneak-item" -> {
                ItemStack itemStack = new ItemStack(Material.SLIME_BALL);
                itemStack.editMeta(meta -> {
                    meta.setDisplayName("§cAlternador do Shift explosivo");
                    meta.setCustomModelData(1);
                    meta.setLore(List.of("","§7Clique para ativar alternar."));
                });

                p.getInventory().addItem(itemStack);
                p.sendMessage("§aItem de sneak adicionado ao seu inventário.");
                break;
            }
            default -> {
                p.sendMessage("§cUso: /geleiatower <imposter-item/gravity-item>");
                return true;
            }
        }

        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length == 1) {
            return List.of("imposter-item", "gravity-item", "chao-de-tnt");
        }
        return List.of();
    }
}
