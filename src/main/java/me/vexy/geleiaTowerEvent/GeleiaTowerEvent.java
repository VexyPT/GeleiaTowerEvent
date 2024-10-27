package me.vexy.geleiaTowerEvent;

import me.vexy.geleiaTowerEvent.modules.joker.JokerManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class GeleiaTowerEvent extends JavaPlugin {

    private static GeleiaTowerEvent instance;

    @Override
    public void onEnable() {
        instance = this;
        // Plugin startup logic
        new JokerManager(this); // Não é mt bom fazer assim, mas é o que tem pra hoje
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static GeleiaTowerEvent get() {
        return instance;
    }
}
