package me.georgeawp.playerreport;

import me.georgeawp.playerreport.commands.PlayerReportCommands;
import me.georgeawp.playerreport.commands.ReportCommand;
import org.bukkit.command.CommandExecutor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;

public final class PlayerReport extends JavaPlugin {


    public static PlayerReport instance = null;
    public FileConfiguration config;

    public String getVersion() {
        return this.getDescription().getVersion();
    }

    public static PlayerReport getInstance() {
        return instance;
    }

    // /PlayerReport reload
    public void reloadConfiguration() {
        this.reloadConfig();
        config = this.getConfig();
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        PlayerReport.instance = this;
        config = this.getConfig();
        config.options().copyDefaults(true);
        this.saveConfig();
        getCommand("report").setExecutor((CommandExecutor) new ReportCommand());
        getCommand("playerreport").setExecutor((CommandExecutor) new PlayerReportCommands());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
