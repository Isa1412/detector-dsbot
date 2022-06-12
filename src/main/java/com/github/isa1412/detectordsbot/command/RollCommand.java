package com.github.isa1412.detectordsbot.command;

import com.github.isa1412.detectordsbot.repository.entity.Guild;
import com.github.isa1412.detectordsbot.repository.entity.Member;
import com.github.isa1412.detectordsbot.repository.entity.id.MemberId;
import com.github.isa1412.detectordsbot.service.MemberService;
import com.github.isa1412.detectordsbot.service.ResponseGenerateService;
import com.github.isa1412.detectordsbot.service.SendBotMessageService;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.List;
import java.util.Random;

import static com.github.isa1412.detectordsbot.command.CommandUtils.*;

/**
 * Roll {@link Command}.
 */
public class RollCommand implements Command {

    private final SendBotMessageService messageService;
    private final MemberService memberService;
    private final ResponseGenerateService responseService;

    public RollCommand(SendBotMessageService messageService, MemberService memberService, ResponseGenerateService responseService) {
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
                    long timestamp = member.getGuild().getTimestamp();

                    if (timestamp > getTimestamp(event) || timestamp == 0) {
                        List<Member> members = memberService.findActiveByGuildId(memberId.getGuildId());
                        Member winner = members.get(new Random().nextInt(members.size()));
                        Guild guild = winner.getGuild();
                        guild.setTimestamp(getTimestamp(event));
                        winner.setGuild(guild);
                        winner.incCount();
                        memberService.save(winner);

                        List<String> response = responseService.getRollResponse(winner.getId().getUserId());
                        for (String r : response) {
                            messageService.sendMessageWithDelay(channel, r);
                        }

                        String avatarUrl = event.getJDA().getUserById(winner.getId().getUserId()).getAvatarUrl();
                        if (avatarUrl != null) {
                            messageService.sendMessage(channel, avatarUrl);
                        }
                    } else {
                        messageService.sendMessage(channel, responseService.getCDResponse(timestamp));
                    }
                },
                () -> messageService.sendMessage(channel, responseService.getNotMemberResponse())
        );
    }
}
