package com.phasmoghostbot.telegrambot.impl.responseHandler;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.telegram.abilitybots.api.db.DBContext;
import org.telegram.abilitybots.api.sender.SilentSender;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import com.phasmoghostbot.telegrambot.constants.Constants;
import com.phasmoghostbot.telegrambot.impl.keyboardFactory.SelectKeyboardFactory;
import com.phasmoghostbot.telegrambot.impl.keyboardFactory.SelectWhichInformationKeyboardFactory;
import com.phasmoghostbot.telegrambot.models.GhostSearchParameters;

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

    public void replyToButtons(long chatId, String buttonId) {
        // TODO Auto-generated method stub
        switch (buttonId) {
            case Constants.SELECT_MODE_BUTTON_INFORMATION:
                replyToInformationMode(chatId);
                break;
            case Constants.SELECT_MODE_BUTTON_GHOST_SOLVER:
                break;
        }
    }

    private void replyToInformationMode(long chatId) {
        SendMessage message = new SendMessage();
        message.setText(Constants.SELECT_WHICH_MODE_INFORMATION_MESSAGE);
        message.setReplyMarkup(new SelectWhichInformationKeyboardFactory().generateKeyboard());
        message.setChatId(chatId);

        sender.execute(message);
    }
}
