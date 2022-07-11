package com.github.isa1412.detectordsbot.bot;

import com.github.isa1412.detectordsbot.command.CommandContainer;
import com.github.isa1412.detectordsbot.service.ResponseGenerateService;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.MemberCachePolicy;
import org.springframework.stereotype.Component;

import javax.security.auth.login.LoginException;
import java.util.Map;

@Component
public class DetectorDsBot {

    public DetectorDsBot(JdaProperties properties, CommandHandler listener, CommandContainer commandContainer,
                         ResponseGenerateService responseService) throws LoginException {
        JDA jda = JDABuilder
                .createLight(properties.getToken())
                .setEnabledIntents(GatewayIntent.GUILD_MEMBERS)
                .setMemberCachePolicy(MemberCachePolicy.ALL)
                .addEventListeners(listener)
                .build();

        Map<String, String> descriptions = responseService.getDescriptions();
        for (String commandName : commandContainer.getCommandMap().keySet()) {
            jda.upsertCommand(commandName, descriptions.get(commandName)).queue();
        }
    }
}
