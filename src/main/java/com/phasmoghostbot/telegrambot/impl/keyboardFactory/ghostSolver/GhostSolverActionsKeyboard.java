package com.phasmoghostbot.telegrambot.impl.keyboardFactory.ghostSolver;

import java.util.ArrayList;
import java.util.List;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import com.phasmoghostbot.telegrambot.api.keyboardFactory.KeyboardFactory;
import com.phasmoghostbot.telegrambot.constants.Constants;

public class GhostSolverActionsKeyboard implements KeyboardFactory {

    @Override
    public ReplyKeyboard generateKeyboard() {
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rows = new ArrayList<>();

        InlineKeyboardButton setSpeedButton = generateKeyboardButton(
                Constants.GHOST_SOLVER_SET_SPEED_MODE_MESSAGE,
                Constants.GHOST_SOLVER_SET_SPEED_MODE_CALLBACK);
        InlineKeyboardButton setBlinkFrequencyButton = generateKeyboardButton(
                Constants.GHOST_SOLVER_SET_BLINK_FREQUENCY_MODE_MESSAGE,
                Constants.GHOST_SOLVER_SET_BLINK_FREQUENCY_MODE_CALLBACK);
        InlineKeyboardButton setCurrentSanityButton = generateKeyboardButton(
                Constants.GHOST_SOLVER_SET_CURRENT_SANITY_MODE_MESSAGE,
                Constants.GHOST_SOLVER_SET_CURRENT_SANITY_MODE_CALLBACK);
        InlineKeyboardButton setEvidencesButton = generateKeyboardButton(
                Constants.GHOST_SOLVER_SET_EVIDENCES_MODE_MESSAGE,
                Constants.GHOST_SOLVER_SET_EVIDENCES_MODE_CALLBACK);
        InlineKeyboardButton backButton = generateKeyboardButton(Constants.BACK_BUTTON_TEXT,
                Constants.START_MODE);

        rows.add(List.of(setSpeedButton));
        rows.add(List.of(setCurrentSanityButton));
        rows.add(List.of(setBlinkFrequencyButton));
        rows.add(List.of(setEvidencesButton));
        rows.add(List.of(backButton));

        markup.setKeyboard(rows);

        return markup;
    }

}
