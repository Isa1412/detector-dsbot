package com.github.isa1412.detectordsbot.command;

import com.github.isa1412.detectordsbot.repository.entity.Member;
import com.github.isa1412.detectordsbot.repository.entity.id.MemberId;
import com.github.isa1412.detectordsbot.service.MemberService;
import com.github.isa1412.detectordsbot.service.ResponseGenerateService;
import com.github.isa1412.detectordsbot.service.SendBotMessageService;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static com.github.isa1412.detectordsbot.command.CommandUtils.getChannel;
import static com.github.isa1412.detectordsbot.command.CommandUtils.getMemberId;

/**
 * Top {@link Command}.
 */
public class TopCommand implements Command {

    private final SendBotMessageService messageService;
    private final MemberService memberService;
    private final ResponseGenerateService responseService;

    public TopCommand(SendBotMessageService messageService, MemberService memberService, ResponseGenerateService responseService) {
        this.messageService = messageService;
        this.memberService = memberService;
        this.responseService = responseService;
    }

    @Override
    public void execute(MessageReceivedEvent event) {
        MemberId memberId = getMemberId(event);
        MessageChannel channel = getChannel(event);

        memberService.findActiveById(memberId).ifPresentOrElse(
                member -> {
                    List<Member> top = memberService.findActiveByGuildId(memberId.getGuildId()).stream()
                            .sorted(Comparator.comparingInt(Member::getCount).reversed())
                            .limit(10)
                            .collect(Collectors.toList());

                    messageService.sendMessage(channel, responseService.getTopResponse(top));
                },
                () -> messageService.sendMessage(channel, responseService.getNotMemberResponse())
        );
    }
}
