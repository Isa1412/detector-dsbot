package com.github.isa1412.detectordsbot.command;

import org.junit.jupiter.api.DisplayName;

import static com.github.isa1412.detectordsbot.command.CommandName.ROLL;
import static com.github.isa1412.detectordsbot.command.RollCommand.ROLL_MESSAGE;

@DisplayName("Unit-level testing for RollCommand")
class RollCommandTest extends AbstractCommandTest {

    @Override
    String getCommandName() {
        return ROLL.getCommandName();
    }

    @Override
    String getCommandMessage() {
        return ROLL_MESSAGE;
    }

    @Override
    Command getCommand() {
        return new RollCommand(messageService);
    }
}