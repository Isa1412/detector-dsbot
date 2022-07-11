package com.github.isa1412.detectordsbot.command;

import com.github.isa1412.detectordsbot.repository.entity.Guild;
import com.github.isa1412.detectordsbot.repository.entity.Member;
import com.github.isa1412.detectordsbot.repository.entity.id.MemberId;
import com.github.isa1412.detectordsbot.service.MemberService;
import com.github.isa1412.detectordsbot.service.ResponseGenerateService;
import com.github.isa1412.detectordsbot.service.SendBotMessageService;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

import java.util.List;
import java.util.Random;

import static com.github.isa1412.detectordsbot.command.CommandUtils.getMemberId;
import static com.github.isa1412.detectordsbot.command.CommandUtils.getTimestamp;
import static java.util.Objects.nonNull;
import static org.apache.logging.log4j.util.Strings.isNotBlank;

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
    public void execute(SlashCommandInteractionEvent event) {
        MemberId memberId = getMemberId(event);

        memberService.findActiveById(memberId).ifPresentOrElse(
                member -> {
                    Guild guild = member.getGuild();
                    long timestamp = guild.getTimestamp();

                    if (timestamp > getTimestamp(event) || timestamp == 0) {
                        List<Member> members = memberService.findActiveByGuildId(guild.getId());
                        Member winner = members.get(new Random().nextInt(members.size()));
                        guild.setTimestamp(getTimestamp(event));
                        winner.setGuild(guild);
                        winner.incCount();
                        memberService.save(winner);

                        List<String> response = responseService.getRollResponse(winner.getId().getUserId());
                        messageService.sendReply(event, response.get(0));
                        response.stream()
                                .skip(1)
                                .forEach(r -> messageService.sendMessageWithDelay(event.getChannel(), r));

                        User user = event.getJDA().getUserById(winner.getId().getUserId());
                        if (nonNull(user) && isNotBlank(user.getAvatarUrl())) {
                            messageService.sendMessage(event.getChannel(), user.getAvatarUrl());
                        }
                    } else {
                        messageService.sendReply(event, responseService.getCDResponse(timestamp));
                    }
                },
                () -> messageService.sendReply(event, responseService.getNotMemberResponse())
        );
    }
}
