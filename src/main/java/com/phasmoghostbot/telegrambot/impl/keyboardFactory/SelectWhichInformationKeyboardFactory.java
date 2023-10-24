package com.phasmoghostbot.telegrambot.impl.keyboardFactory;

import java.util.ArrayList;
import java.util.List;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import com.phasmoghostbot.telegrambot.api.keyboardFactory.KeyboardFactory;
import com.phasmoghostbot.telegrambot.constants.Constants;

public class SelectWhichInformationKeyboardFactory implements KeyboardFactory {

    @Override
    public ReplyKeyboard generateKeyboard() {
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rows = new ArrayList<>();
        List<InlineKeyboardButton> buttonsInRows = new ArrayList<>();

        InlineKeyboardButton buttonEvidence = new InlineKeyboardButton();
        buttonEvidence.setText(Constants.SELECTED_MODE_BUTTON_INFORMATION_EVIDENCE);
        buttonEvidence.setCallbackData(Constants.SELECTED_MODE_BUTTON_INFORMATION_EVIDENCE);

        InlineKeyboardButton buttonGhost = new InlineKeyboardButton();
        buttonGhost.setText(Constants.SELECTED_MODE_BUTTON_INFORMATION_GHOST);
        buttonGhost.setCallbackData(Constants.SELECTED_MODE_BUTTON_INFORMATION_GHOST);

        InlineKeyboardButton buttonBackToSelectMode = new InlineKeyboardButton();
        buttonBackToSelectMode.setText(Constants.BACK_BUTTON_TEXT);
        buttonBackToSelectMode.setCallbackData(Constants.START_MODE);

        buttonsInRows.add(buttonEvidence);
        buttonsInRows.add(buttonGhost);
        rows.add(buttonsInRows);
        rows.add(List.of(buttonBackToSelectMode));

        markup.setKeyboard(rows);

        return markup;
    }

}
