package com.github.isa1412.detectordsbot.service;

import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.requests.restaction.MessageAction;
import net.dv8tion.jda.api.requests.restaction.interactions.ReplyCallbackAction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

@DisplayName("Unit-level testing for SendBotMessageService")
class SendBotMessageServiceTest {

    private SendBotMessageService messageService;

    @BeforeEach
    public void init() {
        messageService = new SendBotMessageServiceImpl();
    }

    @Test
    public void shouldProperlySendMessage() {
        //given
        String message = "test_message";
        MessageChannel channel = Mockito.mock(MessageChannel.class);
        MessageAction action = Mockito.mock(MessageAction.class);
        Mockito.when(channel.sendMessage(message)).thenReturn(action);

        //when
        messageService.sendMessage(channel, message);

        //then
        Mockito.verify(channel).sendMessage(message);
    }

    @Test
    public void shouldProperlySendReply() {
        //given
        String message = "test_message";
        SlashCommandInteractionEvent event = Mockito.mock(SlashCommandInteractionEvent.class);
        ReplyCallbackAction action = Mockito.mock(ReplyCallbackAction.class);
        Mockito.when(event.reply(message)).thenReturn(action);

        //when
        messageService.sendReply(event, message);

        //then
        Mockito.verify(event).reply(message);
    }
}