package com.neos.tusa;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class TusaBot extends TelegramLongPollingBot {

    private final String botToken;
    private final String username;

    public TusaBot(@Value("${bot.token}") String botToken,
                   @Value("${bot.username") String username) {
        this.botToken = botToken;
        this.username = username;
    }

    @Override
    public void onUpdateReceived(Update update) {
        System.out.println(update);
    }

    @Override
    public String getBotUsername() {
        return username;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }
}
