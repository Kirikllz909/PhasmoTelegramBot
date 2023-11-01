package com.phasmoghostbot.telegrambot.impl.keyboardFactory.ghostSolver;

import java.util.ArrayList;
import java.util.List;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import com.phasmoghostbot.telegrambot.api.keyboardFactory.KeyboardFactory;
import com.phasmoghostbot.telegrambot.constants.Constants;

public class CurrentSanityEditKeyboard implements KeyboardFactory {

    @Override
    public ReplyKeyboard generateKeyboard() {
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rows = new ArrayList<>();

        for (int i = 0; i < 90; i += 20) {
            InlineKeyboardButton sanityLevelButton1 = generateKeyboardButton(String.valueOf(i),
                    Constants.GHOST_SOLVER_SET_CURRENT_SANITY_ACTION_CALLBACK + " " + i);
            InlineKeyboardButton sanityLevelButton2 = generateKeyboardButton(String.valueOf(i + 10),
                    Constants.GHOST_SOLVER_SET_CURRENT_SANITY_ACTION_CALLBACK + " " + (i + 10));

            rows.add(List.of(sanityLevelButton1, sanityLevelButton2));
        }

        InlineKeyboardButton sanityLevel100 = generateKeyboardButton(String.valueOf(100),
                Constants.GHOST_SOLVER_SET_CURRENT_SANITY_ACTION_CALLBACK + " " + 100);

        InlineKeyboardButton backButton = generateKeyboardButton(Constants.BACK_BUTTON_TEXT,
                Constants.GHOST_SOLVER_CALLBACK);
        rows.add(List.of(sanityLevel100, backButton));

        markup.setKeyboard(rows);

        return markup;
    }

}
