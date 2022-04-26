package com.github.isa1412.detectordsbot.service;

import net.dv8tion.jda.api.entities.MessageChannel;
import org.springframework.stereotype.Service;

/**
 * Implementation of {@link SendBotMessageService} interface.
 */
@Service
public class SendBotMessageServiceImpl implements SendBotMessageService {

    @Override
    public void sendMessage(MessageChannel channel, String message) {
        channel.sendMessage(message).queue();
    }
}
