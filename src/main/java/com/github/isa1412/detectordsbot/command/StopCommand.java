package com.github.isa1412.detectordsbot.command;

import com.github.isa1412.detectordsbot.repository.entity.id.MemberId;
import com.github.isa1412.detectordsbot.service.MemberService;
import com.github.isa1412.detectordsbot.service.ResponseGenerateService;
import com.github.isa1412.detectordsbot.service.SendBotMessageService;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

import static com.github.isa1412.detectordsbot.command.CommandUtils.getMemberId;

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
    public void execute(SlashCommandInteractionEvent event) {
        MemberId memberId = getMemberId(event);

        memberService.findById(memberId).ifPresentOrElse(
                member -> {
                    if (member.isActive()) {
                        member.setActive(false);
                        memberService.save(member);
                        messageService.sendReply(event, responseService.getOutGameResponse());
                    } else {
                        messageService.sendReply(event, responseService.getAlreadyOutResponse());
                    }
                },
                () -> messageService.sendReply(event, responseService.getNotMemberResponse())
        );
    }
}
