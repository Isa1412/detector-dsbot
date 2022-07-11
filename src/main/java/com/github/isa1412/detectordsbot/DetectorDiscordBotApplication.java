package com.github.isa1412.detectordsbot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class DetectorDiscordBotApplication {

	public static void main(String[] args) {
		SpringApplication.run(DetectorDiscordBotApplication.class, args);
	}

}
