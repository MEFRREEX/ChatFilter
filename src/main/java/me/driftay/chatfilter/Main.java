package me.driftay.chatfilter;

import cn.nukkit.event.Listener;
import cn.nukkit.plugin.PluginBase;

public class Main extends PluginBase {

    public void onEnable() {
        this.getLogger().info("§aChatFilter for §2Nukkit§a Enabled§r");
        this.getLogger().info("§fAuthor: §7Driftay§f, §7MEFRREEXX§f"); 
        this.getLogger().info("§ePorted to Nukkit by §6The Oni.§f https://discord.gg/nahvass4TU");
        this.saveDefaultConfig();
        
        this.getServer().getPluginManager().registerEvents((Listener) new WordsFilter(this), this);
    }
}
