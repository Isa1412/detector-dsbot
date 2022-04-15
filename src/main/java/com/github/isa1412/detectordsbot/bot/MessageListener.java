package com.github.isa1412.detectordsbot.bot;

import com.github.isa1412.jda.starter.JDAEventListener;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.EventListener;
import org.jetbrains.annotations.NotNull;

@JDAEventListener
public class MessageListener implements EventListener {

    @Override
    public void onEvent(@NotNull GenericEvent genericEvent) {
        if (genericEvent instanceof MessageReceivedEvent) {
            MessageReceivedEvent event = (MessageReceivedEvent) genericEvent;

            User user = event.getAuthor();
            boolean bot = user.isBot();

            Message message = event.getMessage();
            String content = message.getContentDisplay();

            if (!bot && event.isFromGuild()) {
                MessageChannel channel = event.getChannel();
                channel.sendMessage(content).queue();
            }
        }
    }
}
