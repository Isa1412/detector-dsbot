package com.github.isa1412.detectordsbot.service;

import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * Implementation of {@link SendBotMessageService} interface.
 */
@Service
public class SendBotMessageServiceImpl implements SendBotMessageService {

    @Override
    public void sendMessage(MessageChannel channel, String message) {
        channel.sendMessage(message).queue();
    }

    @Override
    public void sendMessageWithDelay(MessageChannel channel, String message) {
        channel.sendMessage(message).completeAfter(1, TimeUnit.SECONDS);
    }

    @Override
    public void sendReply(SlashCommandInteractionEvent event, String message) {
        event.reply(message).queue();
    }
}
