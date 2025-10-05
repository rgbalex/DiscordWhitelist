package org.rgbalex.discordWhitelist;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class SayCommand extends ListenerAdapter {
    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if (event.getName().equals("say")) {
            // does not work
//            event.reply(event.getOption("content").getAsString()).queue(); // reply immediately
            event.deferReply().queue(); // acknowledge the command and give more time to respond
            event.getHook().sendMessage(event.getInteraction().getContext().toString()).queue(); // send the actual response
            event.getHook().sendMessage("This is a deferred response!").queue();
            event.getHook().sendMessage("You said: " + event.getOption("content").getAsString()).queue();
        }
    }
}