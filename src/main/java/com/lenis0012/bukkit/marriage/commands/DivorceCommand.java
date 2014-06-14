package com.lenis0012.bukkit.marriage.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.lenis0012.bukkit.marriage.MPlayer;
import com.lenis0012.bukkit.marriage.lang.Messages;

public class DivorceCommand extends CommandBase {

    @Override
    public void perform(CommandSender sender, String[] args) {
        Player player = (Player) sender;
        MPlayer mp = plugin.getMPlayer(player);
        if (!mp.isMarried()) {
            error(player, Messages.NO_PARTNER);
            return;
        }

        String user = player.getName();
        String partner = mp.getPartner();
        Player op = Bukkit.getPlayer(partner);

        //Chat fix start
        if (mp.isChatting()) {
            mp.setChatting(false);
        }
        if (op != null && op.isOnline()) {
            MPlayer omp = plugin.getMPlayer(op);
            if (omp.isChatting()) {
                omp.setChatting(false);
            }
        }
		//chat fix end

        mp.divorce();
        String msg = Messages.DIVORCED.replace("{USER1}", user).replace("{USER2}", partner);
        Bukkit.getServer().broadcastMessage(ChatColor.RED + msg);
    }

    @Override
    public String getPermission() {
        return null;
    }

    @Override
    public boolean playersOnly() {
        return true;
    }
}
