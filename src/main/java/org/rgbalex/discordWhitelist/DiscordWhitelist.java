package org.rgbalex.discordWhitelist;

import org.jetbrains.annotations.NotNull;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import org.bukkit.plugin.java.JavaPlugin;

public final class DiscordWhitelist extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        // print to the console that the plugin has been enabled
        getLogger().info("DiscordWhitelist has been enabled!");

        // save the default config file if it doesn't exist
        saveDefaultConfig();

        // print the config values to the console
        getLogger().info("Config values:");
        getLogger().info("token: " + getConfig().getString("token"));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic

    }

    // command to print "Hello, World!" as the server in the chat when a user types /hello
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, String label, @NotNull String[] args) {
        if (label.equalsIgnoreCase("hello")) {
            sender.sendMessage("Hello, World!");
            return true;
        }
        return false;
    }

}
