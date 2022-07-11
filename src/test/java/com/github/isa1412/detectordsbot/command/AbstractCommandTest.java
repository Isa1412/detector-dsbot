package com.github.isa1412.detectordsbot.command;

import com.github.isa1412.detectordsbot.service.*;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.requests.restaction.interactions.ReplyCallbackAction;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.core.io.ClassPathResource;

/**
 * Abstract class for testing {@link Command}s.
 */
abstract class AbstractCommandTest {

    protected SendBotMessageService messageService = new SendBotMessageServiceImpl();
    protected MemberService memberService = Mockito.mock(MemberService.class);
    protected ResponseGenerateService responseService = new ResponseGenerateServiceImpl(new ClassPathResource("responses.txt"));

    abstract String getCommandName();

    abstract String getCommandMessage();

    abstract Command getCommand();

    @Test
    public void shouldProperlyExecuteCommand() {
        //given
        String userId = "123123123";
        String guildId = "678969697";

        User user = Mockito.mock(User.class);
        Guild guild = Mockito.mock(Guild.class);
        SlashCommandInteractionEvent event = Mockito.mock(SlashCommandInteractionEvent.class);
        ReplyCallbackAction action = Mockito.mock(ReplyCallbackAction.class);
        Mockito.when(user.getId()).thenReturn(userId);
        Mockito.when(guild.getId()).thenReturn(guildId);
        Mockito.when(event.getUser()).thenReturn(user);
        Mockito.when(event.getGuild()).thenReturn(guild);
        Mockito.when(event.getName()).thenReturn(getCommandName());
        Mockito.when(event.reply(getCommandMessage())).thenReturn(action);

        //when
        getCommand().execute(event);

        //then
        Mockito.verify(event).reply(getCommandMessage());
    }
}
