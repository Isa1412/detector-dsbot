package com.github.isa1412.detectordsbot.bot;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "spring.jda")
public class JdaProperties {

    private String token;
}
