package com.github.isa1412.detectordsbot.command;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.requests.restaction.MessageAction;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

@DisplayName("Unit-level testing for UnknownCommand")
class UnknownCommandTest {

    private final Command command = new UnknownCommand();

    @Test
    public void shouldProperlyExecuteCommand() {
        //given
        MessageReceivedEvent event = Mockito.mock(MessageReceivedEvent.class);
        MessageChannel channel = Mockito.mock(MessageChannel.class);
        Message message = Mockito.mock(Message.class);
        MessageAction action = Mockito.mock(MessageAction.class);

        Mockito.when(event.getChannel()).thenReturn(channel);
        Mockito.when(event.getMessage()).thenReturn(message);
        Mockito.when(message.getContentDisplay()).thenReturn("/tenor");
        Mockito.when(channel.sendMessage((CharSequence) Mockito.any())).thenReturn(action);

        //when
        command.execute(event);

        //then
        Mockito.verify(channel, Mockito.never()).sendMessage((CharSequence) Mockito.any());
        Mockito.verifyNoInteractions(channel);
    }


}