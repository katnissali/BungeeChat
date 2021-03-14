package me.katnissali.bungeechat.Util;

import me.katnissali.bungeechat.BungeeChat;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

public class Util {

    private static BungeeChat main;
    private static RankManager manager;

    public static void setup(BungeeChat bc){
        main = bc;
        manager = new RankManager();
    }

    public static String getConsolePrefix(){
        return ("" + ChatColor.DARK_AQUA + "BungeeChat >> " + ChatColor.AQUA);
    }
    public static RankManager getRankManager(){
        return manager;
    }
    public static void sendCommand(String str){
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), str);
    }
    public static void onlyPlayers(){
        print(ChatColor.RED + "Only players can do this.");
    }
    public static BungeeChat getMain(){
        return main;
    }
    public static void print(String str){
        Bukkit.getServer().getConsoleSender().sendMessage(str);
    }
    public static String coloredConfigString(String path){
        return ChatColor.translateAlternateColorCodes('&', main.getConfig().getString(path));
    }
    public static Player getRandomPlayer(){
        for(Player player : Bukkit.getOnlinePlayers()){
            return player;
        }
        return null;
    }
    public static void noPermission(Player player){
        player.sendMessage(ChatColor.RED + "You do not have permission to do this.");
    }
    public static void triggerEvent(Event e){
        Bukkit.getPluginManager().callEvent(e);
    }
}
