package com.github.isa1412.detectordsbot.command;

import org.junit.jupiter.api.DisplayName;

import static com.github.isa1412.detectordsbot.command.CommandName.TOP;

@DisplayName("Unit-level testing for TopCommand")
class TopCommandTest extends AbstractCommandTest {

    @Override
    String getCommandName() {
        return TOP.getCommandName();
    }

    @Override
    String getCommandMessage() {
        return responseService.getNotMemberResponse();
    }

    @Override
    Command getCommand() {
        return new TopCommand(messageService, memberService, responseService);
    }
}