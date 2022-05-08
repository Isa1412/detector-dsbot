package com.github.isa1412.detectordsbot.command;

import com.github.isa1412.detectordsbot.service.DiscordGuildService;
import com.github.isa1412.detectordsbot.service.MemberService;
import com.github.isa1412.detectordsbot.service.SendBotMessageService;
import com.github.isa1412.detectordsbot.service.SendBotMessageServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;

@DisplayName("Unit-level testing for CommandContainer")
class CommandContainerTest {

    private CommandContainer commandContainer;

    @BeforeEach
    public void init() {
        SendBotMessageService messageService = new SendBotMessageServiceImpl();
        DiscordGuildService guildService = Mockito.mock(DiscordGuildService.class);
        MemberService memberService = Mockito.mock(MemberService.class);
        commandContainer = new CommandContainer(messageService, guildService, memberService);
    }

    @Test
    public void shouldGetAllExistingCommands() {
        //when-then
        Arrays.stream(CommandName.values())
                .forEach(commandName -> {
                    Command command = commandContainer.findCommand(commandName.getCommandName());
                    Assertions.assertNotEquals(UnknownCommand.class, command.getClass());
                });
    }

    @Test
    public void shouldReturnUnknownCommand() {
        //given
        String unknownCommand = "/tenor";

        //when
        Command command = commandContainer.findCommand(unknownCommand);

        //then
        Assertions.assertEquals(UnknownCommand.class, command.getClass());
    }
}