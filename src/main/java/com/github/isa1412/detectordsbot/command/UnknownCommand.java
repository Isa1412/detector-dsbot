package com.github.isa1412.detectordsbot.command;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

/**
 * Unknown {@link Command}.
 */
public class UnknownCommand implements Command {

    @Override
    public void execute(MessageReceivedEvent event) {
        //do nothing if unknown command
    }
}
