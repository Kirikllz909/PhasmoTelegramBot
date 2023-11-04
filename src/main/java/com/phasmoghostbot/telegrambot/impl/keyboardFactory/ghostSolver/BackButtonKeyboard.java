package com.phasmoghostbot.telegrambot.impl.keyboardFactory.ghostSolver;

import java.util.ArrayList;
import java.util.List;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import com.phasmoghostbot.telegrambot.api.keyboardFactory.KeyboardFactory;
import com.phasmoghostbot.telegrambot.constants.Constants;

public class BackButtonKeyboard implements KeyboardFactory {

    @Override
    public ReplyKeyboard generateKeyboard() {
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rows = new ArrayList<>();

        InlineKeyboardButton button = generateKeyboardButton(Constants.BACK_BUTTON_TEXT,
                Constants.GHOST_SOLVER_CALLBACK);
        rows.add(List.of(button));

        markup.setKeyboard(rows);
        return markup;
    }

}
