package com.github.isa1412.detectordsbot.command;

import org.junit.jupiter.api.DisplayName;

import static com.github.isa1412.detectordsbot.command.CommandName.GET_IN;
import static com.github.isa1412.detectordsbot.command.GetInCommand.GET_IN_MESSAGE;

@DisplayName("Unit-level testing for GetInCommand")
class GetInCommandTest extends AbstractCommandTest {

    @Override
    String getCommandName() {
        return GET_IN.getCommandName();
    }

    @Override
    String getCommandMessage() {
        return GET_IN_MESSAGE;
    }

    @Override
    Command getCommand() {
        return new GetInCommand(messageService);
    }
}