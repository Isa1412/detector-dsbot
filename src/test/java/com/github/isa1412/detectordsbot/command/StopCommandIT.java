package com.github.isa1412.detectordsbot.command;

import com.github.isa1412.detectordsbot.repository.entity.Member;
import com.github.isa1412.detectordsbot.repository.entity.id.MemberId;
import com.github.isa1412.detectordsbot.service.MemberService;
import com.github.isa1412.detectordsbot.service.ResponseGenerateService;
import com.github.isa1412.detectordsbot.service.ResponseGenerateServiceImpl;
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

import java.util.Optional;

import static com.github.isa1412.detectordsbot.command.CommandName.STOP;
import static com.github.isa1412.detectordsbot.command.CommandUtils.getMemberId;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

/**
 * Integration-level testing for {@link StopCommand}.
 */
@ActiveProfiles("test")
@TestPropertySource(properties = {"spring.autoconfigure.exclude=com.github.isa1412.jda.starter.JDAAutoConfig"})
@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureTestDatabase(replace = NONE)
public class StopCommandIT {

    @Autowired
    private MemberService memberService;
    @Autowired
    private CommandContainer commandContainer;
    private final ResponseGenerateService responseService = new ResponseGenerateServiceImpl(new ClassPathResource("responses.txt"));
    private final String commandName = STOP.getCommandName();
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

        Mockito.when(event.getChannel()).thenReturn(channel);
        Mockito.when(event.getMessage()).thenReturn(message);
        Mockito.when(message.getContentDisplay()).thenReturn(commandName);
        Mockito.when(channel.sendMessage(responseService.getNotMemberResponse())).thenReturn(action);
        Mockito.when(channel.sendMessage(responseService.getAlreadyOutResponse())).thenReturn(action);
        Mockito.when(channel.sendMessage(responseService.getOutGameResponse())).thenReturn(action);
        Mockito.when(event.getAuthor()).thenReturn(user);
        Mockito.when(event.getGuild()).thenReturn(guild);
        Mockito.when(user.getId()).thenReturn(userId);
        Mockito.when(guild.getId()).thenReturn(guildId);
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
    public void shouldProperlyAlreadyOutResponse() {
        //given
        MessageChannel channel = event.getChannel();
        MemberId id = getMemberId(event);
        Member member = new Member(id);
        member.setActive(false);
        memberService.save(member);

        //when
        commandContainer.findCommand(commandName).execute(event);
        Optional<Member> saved = memberService.findById(id);

        //then
        Mockito.verify(channel).sendMessage(responseService.getAlreadyOutResponse());
        assertTrue(saved.isPresent());
        assertEquals(saved.get().getId(), member.getId());
        assertEquals(saved.get().getCount(), member.getCount());
        assertEquals(saved.get().isActive(), member.isActive());
    }

    @Sql(scripts = {"/sql/clearDB.sql"})
    @Test
    public void shouldProperlyOutGameResponse() {
        //given
        MessageChannel channel = event.getChannel();
        MemberId id = getMemberId(event);
        Member member = new Member(id);
        memberService.save(member);

        //when
        commandContainer.findCommand(commandName).execute(event);
        Optional<Member> saved = memberService.findById(id);

        //then
        Mockito.verify(channel).sendMessage(responseService.getOutGameResponse());
        assertTrue(saved.isPresent());
        assertEquals(saved.get().getId(), member.getId());
        assertEquals(saved.get().getCount(), member.getCount());
        assertFalse(saved.get().isActive());
    }
}
