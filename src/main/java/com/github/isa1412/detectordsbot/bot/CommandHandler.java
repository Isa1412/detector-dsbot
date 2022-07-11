package com.github.isa1412.detectordsbot.bot;

import com.github.isa1412.detectordsbot.command.CommandContainer;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Component
public class CommandHandler extends ListenerAdapter {

    private final CommandContainer commandContainer;

    public CommandHandler(CommandContainer commandContainer) {
        this.commandContainer = commandContainer;
    }

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        boolean bot = event.getUser().isBot();
        String commandName = event.getName();

        if (!bot && event.isFromGuild()) {
            commandContainer.findCommand(commandName).execute(event);
        }
    }
}
