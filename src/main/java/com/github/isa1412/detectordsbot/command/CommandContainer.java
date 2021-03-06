package com.github.isa1412.detectordsbot.command;

import com.github.isa1412.detectordsbot.service.MemberService;
import com.github.isa1412.detectordsbot.service.ResponseGenerateService;
import com.github.isa1412.detectordsbot.service.SendBotMessageService;
import com.google.common.collect.ImmutableMap;
import lombok.Getter;
import org.springframework.stereotype.Component;

import static com.github.isa1412.detectordsbot.command.CommandName.*;

/**
 * Container of the {@link Command}s, which are using for handling discord commands.
 */
@Getter
@Component
public class CommandContainer {

    private final ImmutableMap<String, Command> commandMap;

    public CommandContainer(SendBotMessageService messageService, MemberService memberService, ResponseGenerateService responseService) {
        commandMap = ImmutableMap.<String, Command>builder()
                .put(START.getCommandName(), new StartCommand(messageService, memberService, responseService))
                .put(STOP.getCommandName(), new StopCommand(messageService, memberService, responseService))
                .put(ROLL.getCommandName(), new RollCommand(messageService, memberService, responseService))
                .put(TOP.getCommandName(), new TopCommand(messageService, memberService, responseService))
                .put(WINS.getCommandName(), new WinsCommand(messageService, memberService, responseService))
                .build();
    }

    public Command findCommand(String commandIdentifier) {
        return commandMap.get(commandIdentifier);
    }
}
