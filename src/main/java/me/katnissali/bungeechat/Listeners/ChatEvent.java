package me.katnissali.bungeechat.Listeners;

import me.katnissali.bungeechat.Util.BungeeUtil;
import me.katnissali.bungeechat.Util.Util;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatEvent implements Listener {

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e){
        if(!Util.getMain().isDisabled()) {
            Player player = e.getPlayer();
            if (player.hasPermission("BungeeChat.send-message") && !e.isCancelled()) {
                String formatted = Util.getRankManager().formatMessage(player, e.getMessage());
                BungeeUtil.sendPluginMessage("BungeeChat", formatted);
            }
        }
    }

}
