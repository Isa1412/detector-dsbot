package com.github.isa1412.detectordsbot.command;

import org.junit.jupiter.api.DisplayName;

import static com.github.isa1412.detectordsbot.command.CommandName.WINS;
import static com.github.isa1412.detectordsbot.command.WinsCommand.WINS_MESSAGE;

@DisplayName("Unit-level testing for WinsCommand")
class WinsCommandTest extends AbstractCommandTest {

    @Override
    String getCommandName() {
        return WINS.getCommandName();
    }

    @Override
    String getCommandMessage() {
        return WINS_MESSAGE;
    }

    @Override
    Command getCommand() {
        return new WinsCommand(messageService);
    }
}