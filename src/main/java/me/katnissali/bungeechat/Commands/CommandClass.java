package me.katnissali.bungeechat.Commands;

import me.katnissali.bungeechat.Util.Util;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandClass implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){

        if(sender instanceof Player){
            Player player = (Player) sender;

            if(args.length == 0){
                if(player.hasPermission("BungeeChat.help")){
                    sendHelpMessage(player);
                } else {
                    Util.noPermission(player);
                }
            } else if(args.length == 1) {
                if (args[0].equalsIgnoreCase("help")) {
                    if (player.hasPermission("BungeeChat.help")) {
                        sendHelpMessage(player);
                    } else {
                        Util.noPermission(player);
                    }
                } else if (args[0].equalsIgnoreCase("enable")) {
                    if (player.hasPermission("BungeeChat.manage.enable")) {
                        if (Util.getMain().isDisabled()) {
                            player.sendMessage(ChatColor.GREEN + "Enabled BungeeChat");
                            Util.getMain().setDisabled(false);
                        } else {
                            player.sendMessage(ChatColor.RED + "BungeeChat is already enabled.");
                        }
                    } else {
                        Util.noPermission(player);
                    }
                } else if (args[0].equalsIgnoreCase("disable")) {
                    if (player.hasPermission("BungeeChat.manage.disable")) {
                        if (!Util.getMain().isDisabled()) {
                            player.sendMessage(ChatColor.RED + "Disabled BungeeChat");
                            Util.getMain().setDisabled(true);
                        } else {
                            player.sendMessage(ChatColor.RED + "BungeeChat is already enabled.");
                        }
                    } else {
                        Util.noPermission(player);
                    }
                } else if(args[0].equalsIgnoreCase("reload")){
                    if(player.hasPermission("BungeeChat.reload")){
                        player.sendMessage(ChatColor.DARK_AQUA + "BungeeChat >> " + ChatColor.AQUA + "Reloading config...");
                        Util.getMain().reload();
                        player.sendMessage(ChatColor.DARK_AQUA + "BungeeChat >> " + ChatColor.AQUA + "Config reloaded");
                    } else {
                        Util.noPermission(player);
                    }
                } else {
                    wrongUsage(player);
                }
            } else {
                wrongUsage(player);
            }

        } else {


            if(args.length == 0){
                sendHelpMessage();
            } else if(args.length == 1) {
                if (args[0].equalsIgnoreCase("help")) {
                    sendHelpMessage();
                } else if (args[0].equalsIgnoreCase("enable")) {
                    if (Util.getMain().isDisabled()) {
                        Util.print(ChatColor.GREEN + "Enabled BungeeChat");
                        Util.getMain().setDisabled(false);
                    } else {
                        Util.print(ChatColor.RED + "BungeeChat is already enabled.");
                    }
                } else if (args[0].equalsIgnoreCase("disable")) {
                    if (!Util.getMain().isDisabled()) {
                        Util.print(ChatColor.RED + "Disabled BungeeChat");
                        Util.getMain().setDisabled(true);
                    } else {
                        Util.print(ChatColor.RED + "BungeeChat is already enabled.");
                    }

                } else if(args[0].equalsIgnoreCase("reload")){
                    Util.print(Util.getConsolePrefix() + "Reloading config...");
                    Util.getMain().reload();
                    Util.print(Util.getConsolePrefix() + "Config reloaded");

                } else {
                    wrongUsage();
                }
            } else {
                wrongUsage();
            }






        }

        return false;
    }


    private void wrongUsage(Player player){
        player.sendMessage(ChatColor.RED + "Invalid command. Use /bungeechat for more info.");
    }
    private void sendHelpMessage(Player player){
        player.sendMessage("");
        player.sendMessage("" + ChatColor.AQUA + ChatColor.UNDERLINE + "___________________ [BungeeChat] ___________________");
        player.sendMessage("");

        sendCommandMsg(player, "bungeechat help", "This page.", "BungeeChat.help");
        sendCommandMsg(player, "bungeechat enable", "Enables bungeechat", "BungeeChat.manage.enable");
        sendCommandMsg(player, "bungeechat disable", "Disables bungeechat", "BungeeChat.manage.disable");

        player.sendMessage("" + ChatColor.GOLD + "     - " + ChatColor.GRAY + "See messages");
        player.sendMessage("" + ChatColor.GRAY + "       Permission: " + "BungeeChat.see-message");
        player.sendMessage("" + ChatColor.GOLD + "     - " + ChatColor.GRAY + "Send message");
        player.sendMessage("" + ChatColor.GRAY + "       Permission: " + "BungeeChat.send-message");

        player.sendMessage("" + ChatColor.AQUA + ChatColor.UNDERLINE + "___________________________________________________");
    }

    private void sendHelpMessage(){
        Util.print("");
        Util.print("" + ChatColor.AQUA + ChatColor.UNDERLINE + "___________________ [BungeeChat] ___________________");
        Util.print("");

        sendCommandMsg("bungeechat help", "This page.", "BungeeChat.help");
        sendCommandMsg("bungeechat enable", "Enables bungeechat", "BungeeChat.manage.enable");
        sendCommandMsg("bungeechat disable", "Disables bungeechat", "BungeeChat.manage.disable");

        Util.print("" + ChatColor.GOLD + "     - " + ChatColor.GRAY + "See messages");
        Util.print("" + ChatColor.GRAY + "       Permission: " + "BungeeChat.see-message");
        Util.print("" + ChatColor.GOLD + "     - " + ChatColor.GRAY + "Send message");
        Util.print("" + ChatColor.GRAY + "       Permission: " + "BungeeChat.send-message");

        Util.print("" + ChatColor.AQUA + ChatColor.UNDERLINE + "___________________________________________________");

    }
    private void wrongUsage(){
        Util.print(ChatColor.RED + "Invalid command. Use /bungeechat for more info.");
    }

    private void sendCommandMsg(Player player, String command, String description, String permission){
        player.sendMessage("" + ChatColor.GOLD + "     - " + ChatColor.GRAY + "/" + command);
        player.sendMessage("" + ChatColor.GRAY + "       " + description);
        player.sendMessage("" + ChatColor.GRAY + "       Permission: " + permission);
    }
    private void sendCommandMsg(String command, String description, String permission){
        Util.print("" + ChatColor.GOLD + "     - " + ChatColor.GRAY + "/" + command);
        Util.print("" + ChatColor.GRAY + "       " + description);
        Util.print("" + ChatColor.GRAY + "       Permission: " + permission);
    }

}
