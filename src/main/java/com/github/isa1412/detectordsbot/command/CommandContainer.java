package com.github.isa1412.detectordsbot.command;

import com.github.isa1412.detectordsbot.service.SendBotMessageService;
import com.google.common.collect.ImmutableMap;
import org.springframework.stereotype.Component;

import static com.github.isa1412.detectordsbot.command.CommandName.*;

/**
 * Container of the {@link Command}s, which are using for handling discord commands.
 */
@Component
public class CommandContainer {

    private final ImmutableMap<String, Command> commandMap;
    private final Command unknownCommand;

    public CommandContainer(SendBotMessageService messageService) {
        commandMap = ImmutableMap.<String, Command>builder()
                .put(GET_IN.getCommandName(), new GetInCommand(messageService))
                .put(GET_OUT.getCommandName(), new GetOutCommand(messageService))
                .put(ROLL.getCommandName(), new RollCommand(messageService))
                .put(TOP.getCommandName(), new TopCommand(messageService))
                .put(WINS.getCommandName(), new WinsCommand(messageService))
                .put(HELP.getCommandName(), new HelpCommand(messageService))
                .build();

        unknownCommand = new UnknownCommand();
    }

    public Command findCommand(String commandIdentifier) {
        return commandMap.getOrDefault(commandIdentifier, unknownCommand);
    }
}
