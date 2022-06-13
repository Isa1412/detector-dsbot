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

import java.util.ArrayList;
import java.util.Optional;

import static com.github.isa1412.detectordsbot.command.CommandName.TOP;
import static com.github.isa1412.detectordsbot.command.CommandUtils.getMemberId;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

/**
 * Integration-level testing for {@link TopCommand}.
 */
@ActiveProfiles("test")
@TestPropertySource(properties = {"spring.autoconfigure.exclude=com.github.isa1412.jda.starter.JDAAutoConfig"})
@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureTestDatabase(replace = NONE)
public class TopCommandIT {

    @Autowired
    private MemberService memberService;
    @Autowired
    private CommandContainer commandContainer;
    private final ResponseGenerateService responseService = new ResponseGenerateServiceImpl(new ClassPathResource("responses.txt"));
    private final String commandName = TOP.getCommandName();
    private MessageReceivedEvent event;
    private final String response = responseService.getTopResponse(new ArrayList<>()) +
            "<@123456785> - 99\n" +
            "<@123456781> - 54\n" +
            "<@123456784> - 34\n" +
            "<@123123121> - 34\n" +
            "<@123456789> - 21\n" +
            "<@123456788> - 13\n" +
            "<@123456787> - 12\n" +
            "<@123123124> - 12\n" +
            "<@123123125> - 9\n" +
            "<@123123122> - 4";

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
        Mockito.when(channel.sendMessage(response)).thenReturn(action);
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

    @Sql(scripts = {"/sql/clearDB.sql", "/sql/top.sql"})
    @Test
    public void shouldProperlyTopResponse() {
        //given
        MessageChannel channel = event.getChannel();

        //when
        commandContainer.findCommand(commandName).execute(event);

        //then
        Mockito.verify(channel).sendMessage(response);
    }
}
