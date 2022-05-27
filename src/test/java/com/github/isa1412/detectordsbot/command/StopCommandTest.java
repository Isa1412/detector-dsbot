package com.github.isa1412.detectordsbot.command;

import org.junit.jupiter.api.DisplayName;

import static com.github.isa1412.detectordsbot.command.CommandName.STOP;

@DisplayName("Unit-level testing for StopCommand")
class StopCommandTest extends AbstractCommandTest {

    @Override
    String getCommandName() {
        return STOP.getCommandName();
    }

    @Override
    String getCommandMessage() {
        return responseService.getNotMemberResponse();
    }

    @Override
    Command getCommand() {
        return new StopCommand(messageService, memberService, responseService);
    }
}