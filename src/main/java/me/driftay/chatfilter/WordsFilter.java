package me.driftay.chatfilter;

import cn.nukkit.utils.Config;
import cn.nukkit.Player;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerChatEvent;

import java.util.Iterator;

public class WordsFilter implements Listener {

    private Main main;

    public WordsFilter(Main main) {
        this.main = main;
    }


    @EventHandler
    public void onChat(PlayerChatEvent event) {

        Player player = event.getPlayer();
        String message = event.getMessage();
        Config config = main.getConfig();

        boolean bypassPerm = player.hasPermission("chatfilter.bypass");

        char star = config.getString("chatfilter.replace-symbol").charAt(0);
        Iterator<String> stuff = config.getStringList("muted-words").iterator();
        while (stuff.hasNext()) {
            String str = stuff.next();
            if (message.toLowerCase().contains(str.toLowerCase()) && !bypassPerm) {
                message = message.toLowerCase().replace(str, new String(new char[str.length()]).replace('\0', star));
            }
        }

        if (config.getString("chatfilter.filter-type").equals("replace")) {
            event.setMessage(message);
            if (config.getBoolean("messages.enable-warning-message")) {
                player.sendMessage(config.getString("messages.warning-message").replace("&", "§"));
            }
        }
        else if (config.getString("chatfilter.filter-type").equals("block")) {
            if (!event.getMessage().equals(message) && !bypassPerm) {
                event.setCancelled();
                if (config.getBoolean("messages.enable-block-message")) {
                    player.sendMessage(config.getString("messages.block-message").replace("&", "§"));
                }
            }
        } else {
            player.sendMessage("§cConfigure error! Chech console.");
            main.getServer().getLogger().error("§cConfigure error! Unknown parameter in chatfilter.filter-type: \"" + 
                config.getString("chatfilter.filter-type"));
            main.getServer().getLogger().error("Use the available options: \"replace\" or \"block\".");
        }
    }
}
