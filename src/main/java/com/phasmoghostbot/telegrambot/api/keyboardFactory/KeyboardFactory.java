package com.phasmoghostbot.telegrambot.api.keyboardFactory;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;

public interface KeyboardFactory {
    public ReplyKeyboard generateKeyboard();
}
