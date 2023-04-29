package com.mefrreex.chatfilter;

import java.util.Iterator;
import java.util.List;

import com.mefrreex.chatfilter.listener.ChatListener;

import cn.nukkit.plugin.PluginBase;
import lombok.Getter;

@Getter
public class Main extends PluginBase {

    private FilterType filterType;

    public void onEnable() {
        this.saveDefaultConfig();
        this.getServer().getPluginManager().registerEvents(new ChatListener(this), this);
        this.filterType = FilterType.valueOf(this.getConfig().getString("chatfilter.filter-type").toUpperCase());
    }

    public boolean containsWords(String message) {
        List<String> list = this.getConfig().getStringList("muted-words");
        boolean contains = false;
        
        for (String word : list) {
            if (message.contains(word)) {
                contains = true;
                break;
            }
        }

        return contains;
    }

    public String getFilteredMessage(String message) {
        String filteredMessage = message;
        char symbol = this.getConfig().getString("chatfilter.replace-symbol").charAt(0);
        Iterator<String> iterator = this.getConfig().getStringList("muted-words").iterator();
        while (iterator.hasNext()) {
            String blockedWord = iterator.next().toLowerCase();
            if (filteredMessage.toLowerCase().contains(blockedWord)) {
                filteredMessage = filteredMessage.replaceAll("(?i)" + blockedWord, new String(new char[blockedWord.length()]).replace('\0', symbol));
            }
        }
        return filteredMessage;
    }
    

    public enum FilterType {
        REPLACE,
        BLOCK,
        SILENT;
    }
}
