package org.rgbalex.discordWhitelist;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;

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

        startDiscordBot();

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic

    }


    private void startDiscordBot() {
        String token = getConfig().getString("token");
        if (token == null || token.isEmpty()) {
            getLogger().severe("Discord bot token is not set in config.yml");
            return;
        }
        if (token.equals("YOUR_BOT_TOKEN_HERE")) {
            getLogger().warning("Token is set to the default value in config.yml");
        }

        JDA jda = JDABuilder.create(token, Collections.emptyList())
                .addEventListeners(new SayCommand())
                .build();
        updateGuildCommands(jda);
    }

    private void updateGuildCommands(JDA jda) {
        jda.updateCommands().addCommands(
                Commands.slash("say", "Repeats messages back to you.")
                        .addOption(OptionType.STRING, "content", "The content of the message", true)
        ).queue();
    }

    /**
     * Executes a Minecraft command programmatically.
     *
     * @param sender       The sender of the command (e.g., a player or console).
     * @param commandToRun The command to execute (e.g., "say Hello").
     * @return true if the command was executed successfully, false otherwise.
     */
    private boolean executeCommand(CommandSender sender, String commandToRun) {
        return Bukkit.dispatchCommand(sender, commandToRun);
    }

    // command to print "Hello, World!" as the server in the chat when a user types /hello
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, String label, @NotNull String[] args) {
        if (label.equalsIgnoreCase("hello")) {
            sender.sendMessage("Hello, World!");
            return true;
        }
        if (label.equalsIgnoreCase("dwadd")) {
            if (args.length != 1) {
                sender.sendMessage("Usage: /dwadd <username>");
                return false;
            }
            String username = args[0];
            return executeCommand(sender, "whitelist add " + username);
        }
        return false;
    }
}
