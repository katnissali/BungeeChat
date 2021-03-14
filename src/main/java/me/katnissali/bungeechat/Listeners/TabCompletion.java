package me.katnissali.bungeechat.Listeners;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class TabCompletion implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {

        if(args.length == 2){
            List<String> list = new ArrayList();
            if(sender.hasPermission("BungeeChat.help")) list.add("help");
            if(sender.hasPermission("BungeeChat.manage.disable")) list.add("disable");
            if(sender.hasPermission("BungeeChat.manage.enable")) list.add("enable");
            return list;
        }

        return null;
    }
}
