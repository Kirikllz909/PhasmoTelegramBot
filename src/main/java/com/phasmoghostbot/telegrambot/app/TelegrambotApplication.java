package com.phasmoghostbot.telegrambot.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import com.phasmoghostbot.telegrambot.bot.PhasmoGhostBot;

@SpringBootApplication
@ComponentScan("com.phasmoghostbot.telegrambot.bot")
public class TelegrambotApplication {
	private static final Logger logger = LoggerFactory.getLogger(TelegrambotApplication.class);

	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = SpringApplication.run(TelegrambotApplication.class, args);
		try {
			TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
			botsApi.registerBot(ctx.getBean("phasmoGhostBot", PhasmoGhostBot.class));
		} catch (TelegramApiException e) {
			logger.error(e.getMessage());
		}
	}

}
