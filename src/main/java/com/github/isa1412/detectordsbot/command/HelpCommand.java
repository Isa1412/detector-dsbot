package com.github.isa1412.detectordsbot.command;

import com.github.isa1412.detectordsbot.service.ResponseGenerateService;
import com.github.isa1412.detectordsbot.service.SendBotMessageService;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import static com.github.isa1412.detectordsbot.command.CommandUtils.getChannel;

/**
 * Help {@link Command}.
 */
public class HelpCommand implements Command {

    private final SendBotMessageService messageService;
    private final ResponseGenerateService responseService;

    public HelpCommand(SendBotMessageService messageService, ResponseGenerateService responseService) {
        this.messageService = messageService;
        this.responseService = responseService;
    }

    @Override
    public void execute(MessageReceivedEvent event) {
        messageService.sendMessage(getChannel(event), responseService.getHelpResponse());
    }
}
