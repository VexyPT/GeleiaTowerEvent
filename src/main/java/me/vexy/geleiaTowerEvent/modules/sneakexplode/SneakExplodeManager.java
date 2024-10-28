package me.vexy.geleiaTowerEvent.modules.sneakexplode;

import me.vexy.geleiaTowerEvent.GeleiaTowerEvent;

/**
 * @author Rok, Pedro Lucas nmm. Created on 27/10/2024
 * @project GeleiaTowerEvent
 */
public class SneakExplodeManager {

    protected boolean enabled = false;

    public SneakExplodeManager(GeleiaTowerEvent main) {
        main.getServer().getPluginManager().registerEvents(new SneakExplodeEvents(this), main);
    }

    public boolean alterEnabled() {
        enabled = !enabled;
        return enabled;
    }
}
