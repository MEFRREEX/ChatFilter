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
    public void onChat(PlayerChatEvent e) {

        Player p = e.getPlayer();
        String message = e.getMessage();
        Config config = this.main.getConfig();

        boolean sendWarning = false;
        boolean bypassPerm = p.hasPermission(config.getString("chatfilter.bypass-permission"));

        char star = config.getString("replace-symbol").charAt(0);
        Iterator<String> stuff = config.getStringList("muted-words").iterator();
        while (stuff.hasNext()) {
            String str = stuff.next();
            if (message.toLowerCase().contains(str.toLowerCase()) && !bypassPerm) {
                message = message.replace(str, new String(new char[str.length()]).replace('\0', star));
                if (!sendWarning) {
                    sendWarning = true;
                }
            }
        }

        if (sendWarning)
            p.sendMessage(config.getString("chatFilter.warning-message").replace("&", "§"));
        e.setMessage(message);

    }
}
