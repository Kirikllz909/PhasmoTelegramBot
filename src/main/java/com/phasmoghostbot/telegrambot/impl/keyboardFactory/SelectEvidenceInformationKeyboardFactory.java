package com.phasmoghostbot.telegrambot.impl.keyboardFactory;

import java.util.ArrayList;
import java.util.List;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import com.phasmoghostbot.telegrambot.api.keyboardFactory.KeyboardFactory;
import com.phasmoghostbot.telegrambot.constants.Constants;
import com.phasmoghostbot.telegrambot.models.Evidence;

public class SelectEvidenceInformationKeyboardFactory implements KeyboardFactory {

    @Override
    public ReplyKeyboard generateKeyboard() {
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rows = new ArrayList<>();

        boolean isEvidenceCountEven = false;
        int arrIterationCount;

        if (Constants.EVIDENCE_LIST.size() % 2 == 0) {
            isEvidenceCountEven = true;
            arrIterationCount = Constants.EVIDENCE_LIST.size();
        } else
            arrIterationCount = Constants.EVIDENCE_LIST.size() - 1;

        for (int i = 0; i < arrIterationCount; i += 2) {
            Evidence currentEvidence = Constants.EVIDENCE_LIST.get(i);
            Evidence nextEvidence = Constants.EVIDENCE_LIST.get(i + 1);

            InlineKeyboardButton evidence1 = new InlineKeyboardButton();
            evidence1.setText(currentEvidence.getName());
            evidence1.setCallbackData(Constants.SELECTED_EVIDENCE + " " + currentEvidence.getId());

            InlineKeyboardButton evidence2 = new InlineKeyboardButton();
            evidence2.setText(nextEvidence.getName());
            evidence2.setCallbackData(Constants.SELECTED_EVIDENCE + " " + nextEvidence.getId());

            rows.add(List.of(evidence1, evidence2));
        }

        InlineKeyboardButton backButton = new InlineKeyboardButton();
        backButton.setText(Constants.BACK_BUTTON_TEXT);
        backButton.setCallbackData(Constants.SELECT_MODE_BUTTON_INFORMATION);

        if (isEvidenceCountEven == false) {
            Evidence lastEvidence = Constants.EVIDENCE_LIST.get(Constants.EVIDENCE_LIST.size() - 1);

            InlineKeyboardButton lastEvidenceButton = new InlineKeyboardButton();
            lastEvidenceButton.setText(lastEvidence.getName());
            lastEvidenceButton.setCallbackData(Constants.SELECTED_EVIDENCE + " " + lastEvidence.getId());

            rows.add(List.of(lastEvidenceButton, backButton));
        } else
            rows.add(List.of(backButton));

        markup.setKeyboard(rows);

        return markup;
    }

}
