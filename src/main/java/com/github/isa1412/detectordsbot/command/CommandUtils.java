package com.github.isa1412.detectordsbot.command;

import com.github.isa1412.detectordsbot.repository.entity.id.MemberId;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import static java.util.Objects.requireNonNull;

/**
 * Utils class for Commands.
 */
public class CommandUtils {

    /**
     * Retrieve users id of the message from {@link MessageReceivedEvent} object.
     *
     * @param event provided {@link MessageReceivedEvent}
     * @return the users id of the message from the provided {@link MessageReceivedEvent} object.
     */
    public static String getUserId(SlashCommandInteractionEvent event) {
        return event.getUser().getId();
    }

    /**
     * Retrieve guilds id of the message from {@link MessageReceivedEvent} object.
     *
     * @param event provided {@link MessageReceivedEvent}
     * @return the guilds id of the message from the provided {@link MessageReceivedEvent} object.
     */
    public static String getGuildId(SlashCommandInteractionEvent event) {
        return requireNonNull(event.getGuild()).getId();
    }

    /**
     * Retrieve members id of the message from {@link MessageReceivedEvent} object.
     *
     * @param event provided {@link MessageReceivedEvent}
     * @return the members id of the message from the provided {@link MessageReceivedEvent} object.
     */
    public static MemberId getMemberId(SlashCommandInteractionEvent event) {
        return new MemberId(getUserId(event), getGuildId(event));
    }

    /**
     * Retrieve unix timestamp of the message plus recharge time(1 day = 86400 seconds) from {@link MessageReceivedEvent} object.
     *
     * @param event provided {@link MessageReceivedEvent}
     * @return the unix timestamp of the message from the provided {@link MessageReceivedEvent} object.
     */
    public static long getTimestamp(SlashCommandInteractionEvent event) {
        long rechargeTime = 86400;
        return event.getTimeCreated().toInstant().getEpochSecond() + rechargeTime;
    }
}
