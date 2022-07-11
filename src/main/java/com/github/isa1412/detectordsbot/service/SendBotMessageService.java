package com.github.isa1412.detectordsbot.service;

import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

/**
 * Service for sending messages via discord bot.
 */
public interface SendBotMessageService {

    /**
     * Send message via discord bot.
     *
     * @param channel provided {@link MessageChannel} in which messages would be sent.
     * @param message provided message to be sent.
     */
    void sendMessage(MessageChannel channel, String message);

    /**
     * Send message via discord bot with delay.
     *
     * @param channel provided {@link MessageChannel} in which messages would be sent.
     * @param message provided message to be sent.
     */
    void sendMessageWithDelay(MessageChannel channel, String message);

    /**
     * Send reply via discord bot.
     *
     * @param event provided a {@link SlashCommandInteractionEvent} to be responded to.
     * @param message provided a message to be sent.
     */
    void sendReply(SlashCommandInteractionEvent event, String message);
}
