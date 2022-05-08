package com.github.isa1412.detectordsbot.command;

import com.github.isa1412.detectordsbot.repository.entity.Member;
import com.github.isa1412.detectordsbot.repository.entity.id.MemberId;
import com.github.isa1412.detectordsbot.service.MemberService;
import com.github.isa1412.detectordsbot.service.SendBotMessageService;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

/**
 * GetIn {@link Command}.
 */
public class StartCommand implements Command {

    private final SendBotMessageService messageService;
    private final MemberService memberService;

    public final static String START_MESSAGE = "START COMMAND";

    public StartCommand(SendBotMessageService messageService, MemberService memberService) {
        this.messageService = messageService;
        this.memberService = memberService;
    }

    @Override
    public void execute(MessageReceivedEvent event) {
        MemberId memberId = CommandUtils.getMemberId(event);
        MessageChannel channel = CommandUtils.getChannel(event);

        memberService.findById(memberId).ifPresentOrElse(
                member -> {
                    member.setActive(true);
                    memberService.save(member);
                    //todo response message
                },
                () -> {
                    Member member = new Member(memberId);
                    memberService.save(member);
                    //todo response message
                }
        );

        messageService.sendMessage(channel, START_MESSAGE);
    }
}
