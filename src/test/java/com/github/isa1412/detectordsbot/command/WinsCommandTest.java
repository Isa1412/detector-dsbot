package com.github.isa1412.detectordsbot.command;

import org.junit.jupiter.api.DisplayName;

import static com.github.isa1412.detectordsbot.command.CommandName.WINS;

@DisplayName("Unit-level testing for WinsCommand")
class WinsCommandTest extends AbstractCommandTest {

    @Override
    String getCommandName() {
        return WINS.getCommandName();
    }

    @Override
    String getCommandMessage() {
        return responseService.getNotMemberResponse();
    }

    @Override
    Command getCommand() {
        return new WinsCommand(messageService, memberService, responseService);
    }
}