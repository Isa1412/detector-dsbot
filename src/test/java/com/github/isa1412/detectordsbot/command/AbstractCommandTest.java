package com.github.isa1412.detectordsbot.command;

import com.github.isa1412.detectordsbot.service.MemberService;
import com.github.isa1412.detectordsbot.service.ResponseGenerateService;
import com.github.isa1412.detectordsbot.service.SendBotMessageService;
import com.github.isa1412.detectordsbot.service.SendBotMessageServiceImpl;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.requests.restaction.MessageAction;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

/**
 * Abstract class for testing {@link Command}s.
 */
abstract class AbstractCommandTest {

    protected SendBotMessageService messageService = new SendBotMessageServiceImpl();
    protected MemberService memberService = Mockito.mock(MemberService.class);
    protected ResponseGenerateService responseService = Mockito.mock(ResponseGenerateService.class);

    abstract String getCommandName();

    abstract String getCommandMessage();

    abstract Command getCommand();

    @Test
    public void shouldProperlyExecuteCommand() {
        //given
        String userId = "123123123";
        String guildId = "678969697";

        MessageReceivedEvent event = Mockito.mock(MessageReceivedEvent.class);
        MessageChannel channel = Mockito.mock(MessageChannel.class);
        Message message = Mockito.mock(Message.class);
        MessageAction action = Mockito.mock(MessageAction.class);
        User user = Mockito.mock(User.class);
        Guild guild = Mockito.mock(Guild.class);

        Mockito.when(event.getChannel()).thenReturn(channel);
        Mockito.when(event.getMessage()).thenReturn(message);
        Mockito.when(message.getContentDisplay()).thenReturn(getCommandName());
        Mockito.when(channel.sendMessage(getCommandMessage())).thenReturn(action);
        Mockito.when(event.getAuthor()).thenReturn(user);
        Mockito.when(event.getGuild()).thenReturn(guild);
        Mockito.when(user.getId()).thenReturn(userId);
        Mockito.when(guild.getId()).thenReturn(guildId);

        //when
        getCommand().execute(event);

        //then
        Mockito.verify(channel).sendMessage(getCommandMessage());
    }
}
