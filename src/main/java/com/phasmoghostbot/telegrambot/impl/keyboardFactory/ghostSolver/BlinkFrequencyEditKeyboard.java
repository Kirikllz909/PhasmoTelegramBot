package com.phasmoghostbot.telegrambot.impl.keyboardFactory.ghostSolver;

import java.util.ArrayList;
import java.util.List;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import com.phasmoghostbot.telegrambot.api.keyboardFactory.KeyboardFactory;
import com.phasmoghostbot.telegrambot.constants.Constants;

public class BlinkFrequencyEditKeyboard implements KeyboardFactory {

    @Override
    public ReplyKeyboard generateKeyboard() {
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rows = new ArrayList<>();

        InlineKeyboardButton defaultSpeedButton = generateKeyboardButton("Default",
                Constants.GHOST_SOLVER_SET_BLINK_FREQUENCY_ACTION_CALLBACK + " " + "Default");
        InlineKeyboardButton shiftingSpeedButton = generateKeyboardButton("Unusual",
                Constants.GHOST_SOLVER_SET_BLINK_FREQUENCY_ACTION_CALLBACK + " " + "Unusual");
        InlineKeyboardButton backButton = generateKeyboardButton(Constants.BACK_BUTTON_TEXT,
                Constants.GHOST_SOLVER_CALLBACK);

        rows.add(List.of(defaultSpeedButton, shiftingSpeedButton));
        rows.add(List.of(backButton));

        markup.setKeyboard(rows);

        return markup;
    }

}
