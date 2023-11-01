package com.phasmoghostbot.telegrambot.impl.keyboardFactory.information_mode;

import java.util.ArrayList;
import java.util.List;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import com.phasmoghostbot.telegrambot.api.keyboardFactory.KeyboardFactory;
import com.phasmoghostbot.telegrambot.constants.Constants;
import com.phasmoghostbot.telegrambot.models.Ghost;

public class SelectGhostInformationKeyboardFactory implements KeyboardFactory {

    @Override
    public ReplyKeyboard generateKeyboard() {
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rows = new ArrayList<>();

        int maxArrIterations;

        boolean isGhostCountEven = false;
        int ghostCount = Constants.GHOST_LIST.size();
        if (ghostCount % 2 == 0) {
            maxArrIterations = ghostCount;
            isGhostCountEven = true;
        } else
            maxArrIterations = ghostCount - 1;

        for (int i = 0; i < maxArrIterations; i += 2) {

            Ghost firstGhost = Constants.GHOST_LIST.get(i);
            Ghost secondGhost = Constants.GHOST_LIST.get(i + 1);

            InlineKeyboardButton ghostButton1 = this.generateKeyboardButton(firstGhost.getName(),
                    Constants.INFORMATION_MODE_SELECTED_GHOST_CALLBACK + " " + firstGhost.getName());
            InlineKeyboardButton ghostButton2 = this.generateKeyboardButton(secondGhost.getName(),
                    Constants.INFORMATION_MODE_SELECTED_GHOST_CALLBACK + " " + secondGhost.getName());

            rows.add(List.of(ghostButton1, ghostButton2));
        }

        InlineKeyboardButton backButton = this.generateKeyboardButton(
                Constants.BACK_BUTTON_TEXT,
                Constants.INFORMATION_MODE_CALLBACK);

        if (!isGhostCountEven) {
            Ghost lastGhost = Constants.GHOST_LIST.get(maxArrIterations);

            InlineKeyboardButton lastGhostButton = this.generateKeyboardButton(lastGhost.getName(),
                    Constants.INFORMATION_MODE_SELECTED_GHOST_CALLBACK + " " + lastGhost.getName());
            rows.add(List.of(lastGhostButton, backButton));
        } else
            rows.add(List.of(backButton));

        markup.setKeyboard(rows);

        return markup;
    }

}
