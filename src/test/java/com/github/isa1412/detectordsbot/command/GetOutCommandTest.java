package com.github.isa1412.detectordsbot.command;

import org.junit.jupiter.api.DisplayName;

import static com.github.isa1412.detectordsbot.command.CommandName.GET_OUT;
import static com.github.isa1412.detectordsbot.command.GetOutCommand.GET_OUT_MESSAGE;

@DisplayName("Unit-level testing for GetOutCommand")
class GetOutCommandTest extends AbstractCommandTest {

    @Override
    String getCommandName() {
        return GET_OUT.getCommandName();
    }

    @Override
    String getCommandMessage() {
        return GET_OUT_MESSAGE;
    }

    @Override
    Command getCommand() {
        return new GetOutCommand(messageService);
    }
}