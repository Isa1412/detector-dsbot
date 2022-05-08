package com.github.isa1412.detectordsbot.service;

import net.dv8tion.jda.api.entities.MessageChannel;

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
}
