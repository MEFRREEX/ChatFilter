package com.mefrreex.chatfilter.listener;

import cn.nukkit.Player;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerChatEvent;
import cn.nukkit.utils.Config;
import cn.nukkit.utils.TextFormat;

import java.util.Set;

import com.mefrreex.chatfilter.Main;

public class ChatListener implements Listener {

    private Main main;
    private Config config;

    public ChatListener(Main main) {
        this.main = main;
        this.config = main.getConfig();
    }

    @EventHandler
    public void onChat(PlayerChatEvent event) {

        Player player = event.getPlayer();
        String message = event.getMessage();
        String loweredMessage = message.toLowerCase();

        if (!main.containsWords(loweredMessage)) {
            return;
        }

        if (player.hasPermission("chatfilter.bypass")) {
            return;
        }

        switch(main.getFilterType()) {
            case REPLACE:
                event.setMessage(main.getFilteredMessage(message));
                if (config.getBoolean("messages.enable-warning-message")) {
                    player.sendMessage(TextFormat.colorize(config.getString("messages.warning-message")));
                }
                break;

            case BLOCK:
                event.setCancelled();
                if (config.getBoolean("messages.enable-block-message")) {
                    player.sendMessage(TextFormat.colorize(config.getString("messages.block-message")));
                }
                break;

            case SILENT:
                event.setRecipients(Set.of(player));
                break;
            default:
                break;
        }
    }
}
