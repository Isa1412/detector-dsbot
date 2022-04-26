package com.github.isa1412.detectordsbot.command;

import com.github.isa1412.detectordsbot.service.SendBotMessageService;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

/**
 * Wins {@link Command}.
 */
public class WinsCommand implements Command {

    private final SendBotMessageService messageService;

    public final static String WINS_MESSAGE = "WINS COMMAND";

    public WinsCommand(SendBotMessageService messageService) {
        this.messageService = messageService;
    }

    @Override
    public void execute(MessageReceivedEvent event) {
        MessageChannel channel = CommandUtils.getChannel(event);
        messageService.sendMessage(channel, WINS_MESSAGE);
    }
}
