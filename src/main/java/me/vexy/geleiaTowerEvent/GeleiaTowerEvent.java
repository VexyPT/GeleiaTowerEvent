package me.vexy.geleiaTowerEvent;

import lombok.Getter;
import me.vexy.geleiaTowerEvent.commands.GeleiaTowerCommand;
import me.vexy.geleiaTowerEvent.modules.extraheart.ExtraHeartManager;
import me.vexy.geleiaTowerEvent.modules.gravity.GravityManager;
import me.vexy.geleiaTowerEvent.modules.imposter.ImposterManager;
import me.vexy.geleiaTowerEvent.modules.joker.JokerManager;
import me.vexy.geleiaTowerEvent.modules.sneakexplode.SneakExplodeManager;
import me.vexy.geleiaTowerEvent.utils.GlowingEntities;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

@Getter
public final class GeleiaTowerEvent extends JavaPlugin {

    private static GeleiaTowerEvent instance;
    public static final Logger LOGGER = LogManager.getLogger("GeleiaTowerEvent");

    private GlowingEntities glowingEntities;
    private ImposterManager imposterManager;
    private SneakExplodeManager sneakExplodeManager;


    private final List<String> opPlayers = List.of("GeleiaPlays", "LauraBridger");


    @Override
    public void onEnable() {
        instance = this;

        new JokerManager(this); // Não é mt bom fazer assim, mas é o que tem pra hoje
        new GravityManager(this);
        new ExtraHeartManager(this);

        glowingEntities = new GlowingEntities(this);
        imposterManager = new ImposterManager(this);
        sneakExplodeManager = new SneakExplodeManager(this);


        getServer().getPluginCommand("geleiatower").setExecutor(new GeleiaTowerCommand());

        getServer().getPluginManager().registerEvents(new GeleiaEvents(), this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }


    public boolean isOp(Player player) {
        return opPlayers.contains(player.getName());
    }

    public boolean isOp(String playerName) {
        return opPlayers.contains(playerName);
    }

    public static GeleiaTowerEvent get() {
        return instance;
    }
}
