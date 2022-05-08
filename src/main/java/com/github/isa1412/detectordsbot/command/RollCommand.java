package com.github.isa1412.detectordsbot.command;

import com.github.isa1412.detectordsbot.service.MemberService;
import com.github.isa1412.detectordsbot.service.SendBotMessageService;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

/**
 * Roll {@link Command}.
 */
public class RollCommand implements Command {

    private final SendBotMessageService messageService;
    private final MemberService memberService;

    public final static String ROLL_MESSAGE = "ROLL COMMAND";

    public RollCommand(SendBotMessageService messageService, MemberService memberService) {
        this.messageService = messageService;
        this.memberService = memberService;
    }

    @Override
    public void execute(MessageReceivedEvent event) {
        MessageChannel channel = CommandUtils.getChannel(event);
        messageService.sendMessage(channel, ROLL_MESSAGE);

        //        messageService.sendMessage(channel, event.getAuthor().getAvatarUrl());
    }
}
