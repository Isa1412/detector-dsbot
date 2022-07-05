package com.github.isa1412.detectordsbot.command;

import org.junit.jupiter.api.DisplayName;

import static com.github.isa1412.detectordsbot.command.CommandName.HELP;

@DisplayName("Unit-level testing for HelpCommand")
class HelpCommandTest extends AbstractCommandTest {

    @Override
    String getCommandName() {
        return HELP.getCommandName();
    }

    @Override
    String getCommandMessage() {
        return responseService.getHelpResponse();
    }

    @Override
    Command getCommand() {
        return new HelpCommand(messageService, responseService);
    }
}