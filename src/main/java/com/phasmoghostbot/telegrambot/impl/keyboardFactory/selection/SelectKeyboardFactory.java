package com.phasmoghostbot.telegrambot.impl.keyboardFactory.selection;

import java.util.ArrayList;
import java.util.List;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import com.phasmoghostbot.telegrambot.api.keyboardFactory.KeyboardFactory;
import com.phasmoghostbot.telegrambot.constants.Constants;

public class SelectKeyboardFactory implements KeyboardFactory {

    @Override
    public ReplyKeyboard generateKeyboard() {
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rows = new ArrayList<>();
        List<InlineKeyboardButton> buttonsInRow = new ArrayList<>();

        InlineKeyboardButton buttonEvidence = new InlineKeyboardButton();
        buttonEvidence.setText(Constants.SELECT_MODE_BUTTON_INFORMATION);
        buttonEvidence.setCallbackData(Constants.SELECT_MODE_BUTTON_INFORMATION);

        InlineKeyboardButton buttonGhostSolver = new InlineKeyboardButton();
        buttonGhostSolver.setText(Constants.SELECT_MODE_BUTTON_GHOST_SOLVER);
        buttonGhostSolver.setCallbackData(Constants.SELECT_MODE_BUTTON_GHOST_SOLVER);

        buttonsInRow.add(buttonEvidence);
        buttonsInRow.add(buttonGhostSolver);
        rows.add(buttonsInRow);

        markup.setKeyboard(rows);
        return markup;
    }

}
