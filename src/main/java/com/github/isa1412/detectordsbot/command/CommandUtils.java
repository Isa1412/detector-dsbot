package com.github.isa1412.detectordsbot.command;

import com.github.isa1412.detectordsbot.repository.entity.id.MemberId;
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

    /**
     * Retrieve users id of the message from {@link MessageReceivedEvent} object.
     *
     * @param event provided {@link MessageReceivedEvent}
     * @return the users id of the message from the provided {@link MessageReceivedEvent} object.
     */
    public static String getUserId(MessageReceivedEvent event) {
        return event.getAuthor().getId();
    }

    /**
     * Retrieve guilds id of the message from {@link MessageReceivedEvent} object.
     *
     * @param event provided {@link MessageReceivedEvent}
     * @return the guilds id of the message from the provided {@link MessageReceivedEvent} object.
     */
    public static String getGuildId(MessageReceivedEvent event) {
        return event.getGuild().getId();
    }

    /**
     * Retrieve members id of the message from {@link MessageReceivedEvent} object.
     *
     * @param event provided {@link MessageReceivedEvent}
     * @return the members id of the message from the provided {@link MessageReceivedEvent} object.
     */
    public static MemberId getMemberId(MessageReceivedEvent event) {
        return new MemberId(getUserId(event), getGuildId(event));
    }

    public static Long getTime(MessageReceivedEvent event) {
        return event.getMessage().getTimeCreated().toInstant().getEpochSecond() + 86400;
    }
}
