package com.github.isa1412.detectordsbot.bot;

import com.github.isa1412.detectordsbot.command.CommandContainer;
import com.github.isa1412.jda.starter.JDAEventListener;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.EventListener;
import org.jetbrains.annotations.NotNull;

import static com.github.isa1412.detectordsbot.command.CommandUtils.getMessage;

@JDAEventListener
public class MessageListener implements EventListener {

    public static String COMMAND_PREFIX = "/";

    private final CommandContainer commandContainer;

    public MessageListener(CommandContainer commandContainer) {
        this.commandContainer = commandContainer;
    }

    @Override
    public void onEvent(@NotNull GenericEvent genericEvent) {
        if (genericEvent instanceof MessageReceivedEvent) {
            MessageReceivedEvent event = (MessageReceivedEvent) genericEvent;

            boolean bot = event.getAuthor().isBot();
            String message = getMessage(event);

            if (!bot && event.isFromGuild() && message.startsWith(COMMAND_PREFIX)) {
                String commandIdentifier = message.split(" ")[0].toLowerCase();

                commandContainer.findCommand(commandIdentifier).execute(event);
            }
        }
    }
}
