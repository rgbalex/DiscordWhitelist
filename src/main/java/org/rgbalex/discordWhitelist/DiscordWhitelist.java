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

        // read the "secret" property from bot_config.yml
        saveDefaultConfig();
        String secret = getConfig().getString("secret");
        if (secret == null || secret.isEmpty()) {
            getLogger().severe("Bot secret is not set in bot_config.yml! Please set it and restart the server.");
            getServer().getPluginManager().disablePlugin(this);
        } else {
            getLogger().info("Bot secret loaded from bot_config.yml");
        }

        // Initialize the Discord bot with the secret
//        DiscordBot bot = new DiscordBot(secret, this);
//        bot.start();
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
