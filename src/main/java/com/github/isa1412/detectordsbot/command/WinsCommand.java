package com.github.isa1412.detectordsbot.command;

import com.github.isa1412.detectordsbot.repository.entity.id.MemberId;
import com.github.isa1412.detectordsbot.service.MemberService;
import com.github.isa1412.detectordsbot.service.ResponseGenerateService;
import com.github.isa1412.detectordsbot.service.SendBotMessageService;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

import static com.github.isa1412.detectordsbot.command.CommandUtils.getMemberId;

/**
 * Wins {@link Command}.
 */
public class WinsCommand implements Command {

    private final SendBotMessageService messageService;
    private final MemberService memberService;
    private final ResponseGenerateService responseService;

    public WinsCommand(SendBotMessageService messageService, MemberService memberService, ResponseGenerateService responseService) {
        this.messageService = messageService;
        this.memberService = memberService;
        this.responseService = responseService;
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        MemberId memberId = getMemberId(event);

        memberService.findActiveById(memberId).ifPresentOrElse(
                member -> messageService.sendReply(event, responseService.getWinsResponse(memberId.getUserId(), member.getCount())),
                () -> messageService.sendReply(event, responseService.getNotMemberResponse())
        );
    }
}
