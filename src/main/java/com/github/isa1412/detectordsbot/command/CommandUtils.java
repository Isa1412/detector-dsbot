package com.github.isa1412.detectordsbot.command;

import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

/**
 * Utils class for Commands.
 */
public class CommandUtils {

    /**
     * Retrieve {@link MessageChannel} from {@link MessageReceivedEvent} object.
     *
     * @param event provided {@link MessageReceivedEvent}
     * @return {@link MessageChannel} from the provided {@link MessageReceivedEvent} object.
     */
    public static MessageChannel getChannel(MessageReceivedEvent event) {
        return event.getChannel();
    }

    /**
     * Retrieve text of the message from {@link MessageReceivedEvent} object.
     *
     * @param event provided {@link MessageReceivedEvent}
     * @return the text of the message from the provided {@link MessageReceivedEvent} object.
     */
    public static String getMessage(MessageReceivedEvent event) {
        return event.getMessage().getContentDisplay();
    }
}
