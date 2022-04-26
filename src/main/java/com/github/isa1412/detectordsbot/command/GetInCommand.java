package com.github.isa1412.detectordsbot.command;

import com.github.isa1412.detectordsbot.service.SendBotMessageService;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

/**
 * GetIn {@link Command}.
 */
public class GetInCommand implements Command {

    private final SendBotMessageService messageService;

    public final static String GET_IN_MESSAGE = "GET IN COMMAND";

    public GetInCommand(SendBotMessageService messageService) {
        this.messageService = messageService;
    }

    @Override
    public void execute(MessageReceivedEvent event) {
        MessageChannel channel = CommandUtils.getChannel(event);
        messageService.sendMessage(channel, GET_IN_MESSAGE);
    }
}
