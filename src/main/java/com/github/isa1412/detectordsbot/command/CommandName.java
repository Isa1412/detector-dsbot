package com.github.isa1412.detectordsbot.command;

/**
 * Enumeration for {@link Command}'s.
 */
public enum CommandName {

    GET_IN("/dgetin"),
    GET_OUT("/dgetout"),
    ROLL("/droll"),
    TOP("/dtop"),
    WINS("/dwins"),
    HELP("/dhelp");

    private final String commandName;

    CommandName(String commandName) {
        this.commandName = commandName;
    }

    public String getCommandName() {
        return commandName;
    }
}
