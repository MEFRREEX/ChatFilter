package com.mefrreex.chatfilter;

import cn.nukkit.plugin.PluginBase;
import com.mefrreex.chatfilter.listener.ChatListener;
import lombok.Getter;

import java.util.Iterator;
import java.util.List;

@Getter
public class Main extends PluginBase {

    private FilterType filterType;
    
    @Override
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
        String filtered = message;
        
        char symbol = this.getConfig().getString("chatfilter.replace-symbol").charAt(0);
        Iterator<String> iterator = this.getConfig().getStringList("muted-words").iterator();
        
        while (iterator.hasNext()) {
            String blockedWord = iterator.next().toLowerCase();
            if (filtered.toLowerCase().contains(blockedWord)) {
                filtered = filtered.replaceAll("(?i)" + blockedWord, new String(new char[blockedWord.length()]).replace('\0', symbol));
            }
        }

        return filtered;
    }


    public enum FilterType {
        REPLACE,
        BLOCK,
        SILENT;
    }
}
