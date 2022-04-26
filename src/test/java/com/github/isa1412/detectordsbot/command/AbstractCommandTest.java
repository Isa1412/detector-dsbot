package com.github.isa1412.detectordsbot.command;

import com.github.isa1412.detectordsbot.service.SendBotMessageService;
import com.github.isa1412.detectordsbot.service.SendBotMessageServiceImpl;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.requests.restaction.MessageAction;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

/**
 * Abstract class for testing {@link Command}s.
 */
abstract class AbstractCommandTest {

    protected SendBotMessageService messageService = new SendBotMessageServiceImpl();

    abstract String getCommandName();

    abstract String getCommandMessage();

    abstract Command getCommand();

    @Test
    public void shouldProperlyExecuteCommand() {
        //given
        MessageReceivedEvent event = Mockito.mock(MessageReceivedEvent.class);
        MessageChannel channel = Mockito.mock(MessageChannel.class);
        Message message = Mockito.mock(Message.class);
        MessageAction action = Mockito.mock(MessageAction.class);

        Mockito.when(event.getChannel()).thenReturn(channel);
        Mockito.when(event.getMessage()).thenReturn(message);
        Mockito.when(message.getContentDisplay()).thenReturn(getCommandName());
        Mockito.when(channel.sendMessage(getCommandMessage())).thenReturn(action);

        //when
        getCommand().execute(event);

        //then
        Mockito.verify(channel).sendMessage(getCommandMessage());
    }
}
