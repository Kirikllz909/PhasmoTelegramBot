package com.phasmoghostbot.telegrambot.impl.keyboardFactory.information_mode;

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

                InlineKeyboardButton buttonEvidence = this.generateKeyboardButton(
                                Constants.INFORMATION_MODE_EVIDENCE_CALLBACK,
                                Constants.INFORMATION_MODE_EVIDENCE_CALLBACK);

                InlineKeyboardButton buttonGhost = this.generateKeyboardButton(
                                Constants.INFORMATION_MODE_GHOST_CALLBACK,
                                Constants.INFORMATION_MODE_GHOST_CALLBACK);

                InlineKeyboardButton buttonBackToSelectMode = this.generateKeyboardButton(Constants.BACK_BUTTON_TEXT,
                                Constants.START_MODE);

                buttonsInRows.add(buttonEvidence);
                buttonsInRows.add(buttonGhost);
                rows.add(buttonsInRows);
                rows.add(List.of(buttonBackToSelectMode));

                markup.setKeyboard(rows);

                return markup;
        }

}
