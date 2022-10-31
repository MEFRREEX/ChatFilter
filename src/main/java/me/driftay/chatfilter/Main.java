package me.driftay.chatfilter;

import cn.nukkit.event.Listener;
import cn.nukkit.plugin.PluginBase;

public class Main extends PluginBase {

    public void onEnable() {
        this.getLogger().info("ChatFilter Enabled");
        this.getLogger().info("Author: Driftay, ported to Nukkit by MEFRREEX");
        this.saveDefaultConfig();
        
        this.getServer().getPluginManager().registerEvents((Listener) new WordsFilter(this), this);
    }
}
