package com.github.isa1412.detectordsbot.command;

import com.github.isa1412.detectordsbot.repository.entity.Member;
import com.github.isa1412.detectordsbot.repository.entity.id.MemberId;
import com.github.isa1412.detectordsbot.service.MemberService;
import com.github.isa1412.detectordsbot.service.ResponseGenerateService;
import com.github.isa1412.detectordsbot.service.ResponseGenerateServiceImpl;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.requests.restaction.MessageAction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

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
@TestPropertySource(properties = {"spring.autoconfigure.exclude=com.github.isa1412.jda.starter.JDAAutoConfig"})
@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureTestDatabase(replace = NONE)
public class RollCommandIT {

    @Autowired
    private MemberService memberService;
    @Autowired
    private CommandContainer commandContainer;
    private final ResponseGenerateService responseService = new ResponseGenerateServiceImpl(new ClassPathResource("responses.txt"));
    private final String commandName = ROLL.getCommandName();
    private MessageReceivedEvent event;

    @BeforeEach
    public void init() {
        String userId = "123123123";
        String guildId = "678969697";

        event = Mockito.mock(MessageReceivedEvent.class);
        MessageChannel channel = Mockito.mock(MessageChannel.class);
        Message message = Mockito.mock(Message.class);
        MessageAction action = Mockito.mock(MessageAction.class);
        User user = Mockito.mock(User.class);
        Guild guild = Mockito.mock(Guild.class);
        JDA jda = Mockito.mock(JDA.class);

        Mockito.when(event.getChannel()).thenReturn(channel);
        Mockito.when(event.getMessage()).thenReturn(message);
        Mockito.when(message.getContentDisplay()).thenReturn(commandName);
        Mockito.when(message.getTimeCreated()).thenReturn(OffsetDateTime.now());
        Mockito.when(channel.sendMessage(responseService.getNotMemberResponse())).thenReturn(action);
        Mockito.when(channel.sendMessage(responseService.getCDResponse(getTimestamp(event)))).thenReturn(action);
        Mockito.when(channel.sendMessage(responseService.getRollResponse(userId).get(0))).thenReturn(action);
        Mockito.when(event.getAuthor()).thenReturn(user);
        Mockito.when(event.getGuild()).thenReturn(guild);
        Mockito.when(user.getId()).thenReturn(userId);
        Mockito.when(guild.getId()).thenReturn(guildId);
        Mockito.when(event.getJDA()).thenReturn(jda);
        Mockito.when(jda.getUserById(userId)).thenReturn(user);
        Mockito.when(user.getAvatarUrl()).thenReturn(null);
    }

    @Sql(scripts = {"/sql/clearDB.sql"})
    @Test
    public void shouldProperlyNotMemberResponse() {
        //given
        MessageChannel channel = event.getChannel();
        MemberId id = getMemberId(event);

        //when
        commandContainer.findCommand(commandName).execute(event);
        Optional<Member> saved = memberService.findById(id);

        //then
        Mockito.verify(channel).sendMessage(responseService.getNotMemberResponse());
        assertTrue(saved.isEmpty());
    }

    @Sql(scripts = {"/sql/clearDB.sql"})
    @Test
    public void shouldProperlyCDResponse() {
        //given
        MessageChannel channel = event.getChannel();
        MemberId id = getMemberId(event);
        Member member = new Member(id);
        member.getGuild().setTimestamp(getTimestamp(event));
        memberService.save(member);

        //when
        commandContainer.findCommand(commandName).execute(event);
        Optional<Member> saved = memberService.findById(id);

        //then
        Mockito.verify(channel).sendMessage(responseService.getCDResponse(getTimestamp(event)));
        assertTrue(saved.isPresent());
        assertEquals(saved.get().getCount(), 0);
    }

    @Sql(scripts = {"/sql/clearDB.sql"})
    @Test
    public void shouldProperlyRollResponse() {
        //given
        MessageChannel channel = event.getChannel();
        MemberId id = getMemberId(event);
        Member member = new Member(id);
        memberService.save(member);

        //when
        commandContainer.findCommand(commandName).execute(event);
        Optional<Member> saved = memberService.findById(id);

        //then
        Mockito.verify(channel).sendMessage(responseService.getRollResponse(id.getUserId()).get(0));
        assertTrue(saved.isPresent());
        assertEquals(saved.get().getCount(), 1);
        assertEquals(saved.get().getGuild().getTimestamp(), getTimestamp(event));
    }
}
