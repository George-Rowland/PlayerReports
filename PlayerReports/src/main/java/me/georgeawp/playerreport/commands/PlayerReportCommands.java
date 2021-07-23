package me.georgeawp.playerreport.commands;

import me.georgeawp.playerreport.PlayerReport;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PlayerReportCommands implements CommandExecutor {

    @Override
    public boolean onCommand(final CommandSender sender, final Command command, final String label, final String[] args) {
        final Player p = (Player) sender;

        if(label.equalsIgnoreCase("playerreport")) {
            if(args.length == 0) {
                if(!p.hasPermission("playerreport.create")) {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&' , PlayerReport.getInstance().config.getString("reportcreate.no-permission")));
                } else {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aThis server is currently running &f[&cPlayerReport Version " + PlayerReport.getInstance().getVersion() + "&f]"));
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&' , "&f&l Player"));
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&' , "&7/report (playerreport.create)"));
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&' , " "));
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&' , "&f&l Admin"));
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&' , "&7/reports (playerreport.view)"));
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&' , "&7/reportmanage (playerreport.manage"));
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&' , "&7/playerreport reload (playerreport.admin)"));
                    return true;
                }

            }
        }





        return false;
    }
}
