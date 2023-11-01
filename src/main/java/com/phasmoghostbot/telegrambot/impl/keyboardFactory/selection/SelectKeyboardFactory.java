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
        buttonEvidence.setText(Constants.INFORMATION_MODE_CALLBACK);
        buttonEvidence.setCallbackData(Constants.INFORMATION_MODE_CALLBACK);

        InlineKeyboardButton buttonGhostSolver = new InlineKeyboardButton();
        buttonGhostSolver.setText("Ghost solver");
        buttonGhostSolver.setCallbackData(Constants.GHOST_SOLVER_CALLBACK);

        buttonsInRow.add(buttonEvidence);
        buttonsInRow.add(buttonGhostSolver);
        rows.add(buttonsInRow);

        markup.setKeyboard(rows);
        return markup;
    }

}
