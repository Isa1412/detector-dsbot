package com.github.isa1412.detectordsbot.command;

import com.github.isa1412.detectordsbot.bot.DetectorDsBot;
import com.github.isa1412.detectordsbot.repository.entity.Member;
import com.github.isa1412.detectordsbot.repository.entity.id.MemberId;
import com.github.isa1412.detectordsbot.service.MemberService;
import com.github.isa1412.detectordsbot.service.ResponseGenerateService;
import com.github.isa1412.detectordsbot.service.ResponseGenerateServiceImpl;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.requests.restaction.interactions.ReplyCallbackAction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.time.OffsetDateTime;
import java.util.Optional;

import static com.github.isa1412.detectordsbot.command.CommandName.ROLL;
import static com.github.isa1412.detectordsbot.command.CommandUtils.getMemberId;
import static com.github.isa1412.detectordsbot.command.CommandUtils.getTimestamp;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

/**
 * Integration-level testing for {@link RollCommand}.
 */
@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureTestDatabase(replace = NONE)
public class RollCommandIT {

    @MockBean
    DetectorDsBot detectorDsBot;
    @Autowired
    private MemberService memberService;
    @Autowired
    private CommandContainer commandContainer;
    private final ResponseGenerateService responseService = new ResponseGenerateServiceImpl(new ClassPathResource("responses.txt"));
    private final String commandName = ROLL.getCommandName();
    private SlashCommandInteractionEvent event;

    @BeforeEach
    public void init() {
        String userId = "123123123";
        String guildId = "678969697";

        User user = Mockito.mock(User.class);
        Guild guild = Mockito.mock(Guild.class);
        event = Mockito.mock(SlashCommandInteractionEvent.class);
        ReplyCallbackAction action = Mockito.mock(ReplyCallbackAction.class);
        JDA jda = Mockito.mock(JDA.class);
        Mockito.when(user.getId()).thenReturn(userId);
        Mockito.when(guild.getId()).thenReturn(guildId);
        Mockito.when(event.getUser()).thenReturn(user);
        Mockito.when(event.getGuild()).thenReturn(guild);
        Mockito.when(event.getName()).thenReturn(commandName);
        Mockito.when(event.getTimeCreated()).thenReturn(OffsetDateTime.now());
        Mockito.when(event.getJDA()).thenReturn(jda);
        Mockito.when(jda.getUserById(userId)).thenReturn(user);
        Mockito.when(user.getAvatarUrl()).thenReturn("");
        Mockito.when(event.reply(responseService.getNotMemberResponse())).thenReturn(action);
        Mockito.when(event.reply(responseService.getCDResponse(getTimestamp(event)))).thenReturn(action);
        Mockito.when(event.reply(responseService.getRollResponse(userId).get(0))).thenReturn(action);
    }

    @Sql(scripts = {"/sql/clearDB.sql"})
    @Test
    public void shouldProperlyNotMemberResponse() {
        //given
        MemberId id = getMemberId(event);

        //when
        commandContainer.findCommand(commandName).execute(event);
        Optional<Member> saved = memberService.findById(id);

        //then
        Mockito.verify(event).reply(responseService.getNotMemberResponse());
        assertTrue(saved.isEmpty());
    }

    @Sql(scripts = {"/sql/clearDB.sql"})
    @Test
    public void shouldProperlyCDResponse() {
        //given
        MemberId id = getMemberId(event);
        Member member = new Member(id);
        long timestamp = getTimestamp(event);
        member.getGuild().setTimestamp(timestamp);
        memberService.save(member);

        //when
        commandContainer.findCommand(commandName).execute(event);
        Optional<Member> saved = memberService.findById(id);

        //then
        Mockito.verify(event).reply(responseService.getCDResponse(timestamp));
        assertTrue(saved.isPresent());
        assertEquals(saved.get().getCount(), 0);
    }

    @Sql(scripts = {"/sql/clearDB.sql"})
    @Test
    public void shouldProperlyRollResponse() {
        //given
        MemberId id = getMemberId(event);
        Member member = new Member(id);
        long timestamp = getTimestamp(event);
        memberService.save(member);

        //when
        commandContainer.findCommand(commandName).execute(event);
        Optional<Member> saved = memberService.findById(id);

        //then
        Mockito.verify(event).reply(responseService.getRollResponse(id.getUserId()).get(0));
        assertTrue(saved.isPresent());
        assertEquals(saved.get().getCount(), 1);
        assertEquals(saved.get().getGuild().getTimestamp(), timestamp);
    }
}
