package me.katnissali.bungeechat;

import me.katnissali.bungeechat.Commands.CommandClass;
import me.katnissali.bungeechat.Listeners.BungeeListener;
import me.katnissali.bungeechat.Listeners.ChatEvent;
import me.katnissali.bungeechat.Listeners.TabCompletion;
import me.katnissali.bungeechat.Util.Util;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class BungeeChat extends JavaPlugin {

    private boolean disabled = false;

    @Override
    public void onEnable() {
        Util.print(Util.getConsolePrefix() + "Enabling BungeeChat.");

        loadConfig();
        Util.setup(this);
        registerCommands();
        registerEvents();
        reload();
        Util.print(Util.getConsolePrefix() + "BungeeChat enabled.");
    }
    public void onDisable(){
        Util.print(ChatColor.RED + "Disabling BungeeChat");
    }

    private void loadConfig(){
        this.getConfig().options().copyDefaults();
        saveDefaultConfig();
    }
    private void registerEvents(){
        Bukkit.getPluginManager().registerEvents(new ChatEvent(), this);

        getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        getServer().getMessenger().registerIncomingPluginChannel(this, "BungeeCord", new BungeeListener());
    }
    private void registerCommands(){
        getCommand("BungeeChat").setExecutor(new CommandClass());
        getCommand("BungeeChat").setTabCompleter(new TabCompletion());
    }
    public void reload(){
        Util.setup(this);
    }

    public boolean isDisabled(){
        return disabled;
    }
    public void setDisabled(boolean bool){
        disabled = bool;
    }
}
