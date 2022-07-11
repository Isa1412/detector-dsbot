package com.github.isa1412.detectordsbot.command;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

/**
 * Command interface for handling discord bot commands.
 */
public interface Command {

    /**
     * Main method, which is executing command logic.
     *
     * @param event provided {@link SlashCommandInteractionEvent} object with all the needed data for command.
     */
    void execute(SlashCommandInteractionEvent event);
}
