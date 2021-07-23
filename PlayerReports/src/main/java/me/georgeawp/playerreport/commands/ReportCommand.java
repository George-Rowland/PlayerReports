package me.georgeawp.playerreport.commands;

import me.georgeawp.playerreport.PlayerReport;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class ReportCommand implements CommandExecutor {

    Map<String, Long> cooldowns = new HashMap<String, Long>();

    public boolean onCommand(final CommandSender sender, final Command command, final String label, final String[] args) {
        final Player p = (Player) sender;

        // /report
        if (command.getName().equalsIgnoreCase("report")) {
            if (!p.hasPermission("playerreport.create")) {
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', PlayerReport.getInstance().config.getString("reportcreate.no-permission")));
                return true;
            }

            if (args.length < 2) {
                if(!p.hasPermission("playerreport.create")) {
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&' , PlayerReport.getInstance().config.getString("reportcreate.no-permission")));
                }
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&f[&aPlayerReport&f] &cCorrect usage: /report [player] [reason]"));
                return true;
            }

            // /report username reason
            if (args.length > 1) {
                if(!p.hasPermission("playerreport.create")) {
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&' , PlayerReport.getInstance().config.getString("reportcreate.no-permission")));
                }

                final int cooldownTime = PlayerReport.getInstance().config.getInt("reportcreate.cooldown");
                if (cooldowns.containsKey(p.getName())) {
                    // player is in hashmap
                    if (cooldowns.get(p.getName()) > System.currentTimeMillis()) {
                        // still have time in cooldown
                        long timeleft = ((cooldowns.get(p.getName())) - System.currentTimeMillis()) / 12000 / 4;
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&' , "&aYou will be able to report again in &6" + timeleft + "&a minute(s)" ));
                        return true;

                    }
                }

                if (p.getServer().getPlayer(args[0]) != null) {
                    // /player [name] where name = online player
                    Player reported = p.getServer().getPlayer(args[0]);

                    StringBuilder msgBuilder = new StringBuilder();
                    for (int i = 1; i < args.length; ++i) {
                        msgBuilder.append(args[i]).append(" ");
                    }
                    String reason = msgBuilder.toString().trim();
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&f[&c[REPORT]&f] &7You have reported &a" + args[0] + " &7for the reason: &a" + reason));
                    cooldowns.put(p.getName(), System.currentTimeMillis() + (cooldownTime * 1000 * 60));

                    String report = "&c[REPORT] &fFrom &a" + p.getDisplayName() + ": ";
                    for (String s : args) {
                        report = report + "&c" + s + "&7 ";
                    }


                    for (Player staff : Bukkit.getOnlinePlayers()) {
                        if (!staff.hasPermission("playerreport.view")) {
                            return true;
                        } else {
                            staff.sendMessage(ChatColor.translateAlternateColorCodes('&' , report));
                        }
                    }
                }

                if (p.getServer().getPlayer(args[0]) == null) {
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&' , "&6That player is not online! &cUsage:/report [player] [reason]"));
                    return true;
                }



                return true;
            }


        }
        return false;
    }
}
