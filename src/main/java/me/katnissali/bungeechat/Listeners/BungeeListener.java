package me.katnissali.bungeechat.Listeners;

import me.katnissali.bungeechat.Util.BungeeUtil;
import me.katnissali.bungeechat.Util.Util;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;

public class BungeeListener implements PluginMessageListener {

    @Override
    public void onPluginMessageReceived(String channel, Player player, byte[] byteMsg) {
        if (!channel.equals("BungeeCord")) {
            return;
        }
        String subChannel = BungeeUtil.getChannel(byteMsg);
        String message = BungeeUtil.getMessage(byteMsg);

        if(subChannel.equalsIgnoreCase("BungeeChat")){
            Util.print(message);
            if(!Util.getMain().isDisabled()) Bukkit.broadcast(message, "BungeeChat.see-message");
        }

    }

}
