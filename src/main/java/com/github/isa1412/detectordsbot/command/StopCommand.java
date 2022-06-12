package com.github.isa1412.detectordsbot.command;

import com.github.isa1412.detectordsbot.repository.entity.id.MemberId;
import com.github.isa1412.detectordsbot.service.MemberService;
import com.github.isa1412.detectordsbot.service.ResponseGenerateService;
import com.github.isa1412.detectordsbot.service.SendBotMessageService;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import static com.github.isa1412.detectordsbot.command.CommandUtils.*;

/**
 * Stop {@link Command}.
 */
public class StopCommand implements Command {

    private final SendBotMessageService messageService;
    private final MemberService memberService;
    private final ResponseGenerateService responseService;

    public StopCommand(SendBotMessageService messageService, MemberService memberService, ResponseGenerateService responseService) {
        this.messageService = messageService;
        this.memberService = memberService;
        this.responseService = responseService;
    }

    @Override
    public void execute(MessageReceivedEvent event) {
        MemberId memberId = getMemberId(event);
        MessageChannel channel = getChannel(event);

        memberService.findById(memberId).ifPresentOrElse(
                member -> {
                    if (member.isActive()) {
                        member.setActive(false);
                        memberService.save(member);
                        messageService.sendMessage(channel, responseService.getOutGameResponse());
                    } else {
                        messageService.sendMessage(channel, responseService.getAlreadyOutResponse());
                    }
                },
                () -> messageService.sendMessage(channel, responseService.getNotMemberResponse())
        );
    }
}
