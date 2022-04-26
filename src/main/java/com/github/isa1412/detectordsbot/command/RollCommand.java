package com.github.isa1412.detectordsbot.command;

import com.github.isa1412.detectordsbot.service.SendBotMessageService;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

/**
 * Roll {@link Command}.
 */
public class RollCommand implements Command {

    private final SendBotMessageService messageService;

    public final static String ROLL_MESSAGE = "ROLL COMMAND";

    public RollCommand(SendBotMessageService messageService) {
        this.messageService = messageService;
    }

    @Override
    public void execute(MessageReceivedEvent event) {
        MessageChannel channel = CommandUtils.getChannel(event);
        messageService.sendMessage(channel, ROLL_MESSAGE);
    }
}
