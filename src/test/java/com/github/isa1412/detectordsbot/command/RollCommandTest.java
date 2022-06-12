package com.github.isa1412.detectordsbot.command;

import org.junit.jupiter.api.DisplayName;

import static com.github.isa1412.detectordsbot.command.CommandName.ROLL;

@DisplayName("Unit-level testing for RollCommand")
class RollCommandTest extends AbstractCommandTest {

    @Override
    String getCommandName() {
        return ROLL.getCommandName();
    }

    @Override
    String getCommandMessage() {
        return responseService.getNotMemberResponse();
    }

    @Override
    Command getCommand() {
        return new RollCommand(messageService, memberService, responseService);
    }
}