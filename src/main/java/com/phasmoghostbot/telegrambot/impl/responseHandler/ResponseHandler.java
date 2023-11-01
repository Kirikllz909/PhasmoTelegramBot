package com.phasmoghostbot.telegrambot.impl.responseHandler;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.telegram.abilitybots.api.db.DBContext;
import org.telegram.abilitybots.api.sender.SilentSender;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import com.phasmoghostbot.telegrambot.constants.Constants;
import com.phasmoghostbot.telegrambot.impl.keyboardFactory.information_mode.SelectEvidenceInformationKeyboardFactory;
import com.phasmoghostbot.telegrambot.impl.keyboardFactory.information_mode.SelectGhostInformationKeyboardFactory;
import com.phasmoghostbot.telegrambot.impl.keyboardFactory.information_mode.SelectWhichInformationKeyboardFactory;
import com.phasmoghostbot.telegrambot.impl.keyboardFactory.selection.SelectKeyboardFactory;
import com.phasmoghostbot.telegrambot.models.Ability;
import com.phasmoghostbot.telegrambot.models.Evidence;
import com.phasmoghostbot.telegrambot.models.Ghost;
import com.phasmoghostbot.telegrambot.models.GhostSearchParameters;
import com.phasmoghostbot.telegrambot.models.HuntStartingCondition;

public class ResponseHandler {

    private final Logger logger = LogManager.getLogger(ResponseHandler.class);

    private final SilentSender sender;
    private final Map<Long, GhostSearchParameters> ghostSearchParameters;

    public ResponseHandler(SilentSender sender, DBContext db) {
        this.sender = sender;
        ghostSearchParameters = db.getMap(Constants.CHAT_SEARCH_PARAMS);
    }

    public void replyToStart(long chatId) {
        SendMessage message = new SendMessage();
        message.setText(Constants.SELECT_MODE_MESSAGE);
        message.setChatId(chatId);
        message.setReplyMarkup(new SelectKeyboardFactory().generateKeyboard());

        sender.execute(message);
    }

    public void replyToButtons(long chatId, String buttonCallbackData) {
        // TODO Add ghost solver functionality
        switch (buttonCallbackData.split(" ")[0]) {
            case Constants.START_MODE:
                replyToStart(chatId);
                break;

            case Constants.SELECT_MODE_BUTTON_INFORMATION:
                InformationModeHandler.replyToInformationMode(sender, chatId);
                break;
            case Constants.SELECTED_MODE_BUTTON_INFORMATION_EVIDENCE:
                InformationModeHandler.replyToInformationEvidenceMode(sender, chatId);
                break;
            case Constants.SELECTED_MODE_BUTTON_INFORMATION_GHOST:
                InformationModeHandler.replyToInformationGhostMode(sender, chatId);
                break;
            case Constants.SELECTED_EVIDENCE:
                InformationModeHandler.replyToSelectedEvidence(sender, chatId, buttonCallbackData.split(" ")[1]);
                break;
            case Constants.SELECTED_GHOST:
                InformationModeHandler.replyToSelectedGhost(sender, chatId, buttonCallbackData.split(" ")[1]);
                break;

            case Constants.SELECT_MODE_BUTTON_GHOST_SOLVER:
                break;
        }
    }
}
