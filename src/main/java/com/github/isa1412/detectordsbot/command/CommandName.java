package com.github.isa1412.detectordsbot.command;

/**
 * Enumeration for {@link Command}'s.
 */
public enum CommandName {

    START("start"),
    STOP("stop"),
    ROLL("roll"),
    TOP("top"),
    WINS("wins");

    private final String commandName;

    CommandName(String commandName) {
        this.commandName = commandName;
    }

    public String getCommandName() {
        return commandName;
    }
}
