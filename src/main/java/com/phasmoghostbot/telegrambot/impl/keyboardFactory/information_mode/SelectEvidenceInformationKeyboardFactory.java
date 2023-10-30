package com.phasmoghostbot.telegrambot.impl.keyboardFactory.information_mode;

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
        int evidenceCount = Constants.EVIDENCE_LIST.size();

        if (evidenceCount % 2 == 0) {
            isEvidenceCountEven = true;
            arrIterationCount = evidenceCount;
        } else
            arrIterationCount = evidenceCount - 1;

        for (int i = 0; i < arrIterationCount; i += 2) {
            Evidence currentEvidence = Constants.EVIDENCE_LIST.get(i);
            Evidence nextEvidence = Constants.EVIDENCE_LIST.get(i + 1);

            InlineKeyboardButton evidence1 = this.generateKeyboardButton(currentEvidence.getName(),
                    Constants.SELECTED_EVIDENCE + " " + currentEvidence.getId());

            InlineKeyboardButton evidence2 = this.generateKeyboardButton(nextEvidence.getName(),
                    Constants.SELECTED_EVIDENCE + " " + nextEvidence.getId());

            rows.add(List.of(evidence1, evidence2));
        }

        InlineKeyboardButton backButton = this.generateKeyboardButton(Constants.BACK_BUTTON_TEXT,
                Constants.SELECT_MODE_BUTTON_INFORMATION);

        if (isEvidenceCountEven == false) {
            Evidence lastEvidence = Constants.EVIDENCE_LIST.get(arrIterationCount);

            InlineKeyboardButton lastEvidenceButton = this.generateKeyboardButton(lastEvidence.getName(),
                    Constants.SELECTED_EVIDENCE + " " + lastEvidence.getId());

            rows.add(List.of(lastEvidenceButton, backButton));
        } else
            rows.add(List.of(backButton));

        markup.setKeyboard(rows);

        return markup;
    }

}
