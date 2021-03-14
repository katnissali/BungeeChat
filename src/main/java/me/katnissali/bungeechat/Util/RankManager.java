package me.katnissali.bungeechat.Util;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class RankManager {

    private LinkedHashMap<String, Rank> ranks = new LinkedHashMap();
    private Rank lastRank;
    public RankManager(){
        loadRanks();
    }

    private void loadRanks(){
        for(String str : Util.getMain().getConfig().getConfigurationSection("format-list").getKeys(false)){
            String perm = getString("format-list." + str + ".permission");
            String format = getString("format-list." + str + ".chat-format");
            Rank rank = new Rank(str, perm, ChatColor.translateAlternateColorCodes('&', format));
            ranks.put(rank.getName(), rank);
            lastRank = rank;
        }
    }

    private String getString(String path){
        return Util.getMain().getConfig().getString(path);
    }
    public Rank getRank(String str){
        return ranks.get(str);
    }
    public Rank getRank(Player player){
        for(Rank rank : ranks.values()){
            if(player.hasPermission(rank.getPerission())){
                return rank;
            }
        }
        return lastRank;
    }
    public String formatMessage(Player player, String message){
        Rank rank = getRank(player);
        String format = rank.getFormat();
        format = format.replace("%playername%", player.getName());
        format = format.replace("%message%", message);
        return ChatColor.WHITE + format;
    }
}
