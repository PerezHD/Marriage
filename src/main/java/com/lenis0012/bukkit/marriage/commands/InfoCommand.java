package com.lenis0012.bukkit.marriage.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.lenis0012.bukkit.marriage.MPlayer;

public class InfoCommand extends CommandBase {

    @Override
    public void perform(CommandSender sender, String[] args) {
        boolean isAdmin = sender.hasPermission("marry.admin");
        ChatColor g = ChatColor.GRAY;
        ChatColor l = ChatColor.GREEN;
        ChatColor r = ChatColor.RED;
        inform(sender, g + "==========-{" + l + " Marriage " + g + "}-==========");
        inform(sender, g + "Version: " + l + plugin.getDescription().getVersion());
        inform(sender, g + "Authors: " + l + plugin.getDescription().getAuthors().toString().replace("[", "").replace("]", ""));
        inform(sender, l + "/marry <player> " + g + "- Marry a player");
        inform(sender, l + "/marry accept " + g + "- Accept a marriage request");
        inform(sender, l + "/marry divorce " + g + "- Divorce your partner");
        inform(sender, l + "/marry chat " + g + "- Private chat with your partner");
        inform(sender, l + "/marry seen - Check your partners last login");
        if (sender.hasPermission("marry.reload") || isAdmin) {
            inform(sender, l + "/marry reload" + g + " - Reload all config files");
        }
        if (plugin.getConfig().getBoolean("settings.enable-chatspy") && (sender.hasPermission("marry.chatspy") || isAdmin)) {
            inform(sender, l + "/marry chatspy - View marry chat");
        }

        inform(sender, l + "Crouch + Right click" + g + " - Kiss your partner");

        if (this.isPlayer()) {
            Player player = (Player) sender;
            MPlayer mp = plugin.getMPlayer(player);
            if (mp.isMarried()) {
                inform(sender, "Married: " + l + mp.getPartner());
            } else {
                inform(sender, "Married: " + r + "No");
            }
        }
    }

    @Override
    public String getPermission() {
        return null;
    }

    @Override
    public boolean playersOnly() {
        return false;
    }
}
