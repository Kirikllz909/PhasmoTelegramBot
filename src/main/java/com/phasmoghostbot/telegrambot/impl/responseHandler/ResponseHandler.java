package com.phasmoghostbot.telegrambot.impl.responseHandler;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.telegram.abilitybots.api.db.DBContext;
import org.telegram.abilitybots.api.sender.SilentSender;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import com.phasmoghostbot.telegrambot.constants.Constants;
import com.phasmoghostbot.telegrambot.impl.keyboardFactory.selection.SelectKeyboardFactory;
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
        message.setText(Constants.SELECT_MESSAGE);
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

            case Constants.INFORMATION_MODE_CALLBACK:
                InformationModeHandler.replyToInformationMode(sender, chatId);
                break;
            case Constants.INFORMATION_MODE_EVIDENCE_CALLBACK:
                InformationModeHandler.replyToInformationEvidenceMode(sender, chatId);
                break;
            case Constants.INFORMATION_MODE_GHOST_CALLBACK:
                InformationModeHandler.replyToInformationGhostMode(sender, chatId);
                break;
            case Constants.INFORMATION_MODE_SELECTED_EVIDENCE_CALLBACK:
                InformationModeHandler.replyToSelectedEvidence(sender, chatId, buttonCallbackData.split(" ")[1]);
                break;
            case Constants.INFORMATION_MODE_SELECTED_GHOST_CALLBACK:
                InformationModeHandler.replyToSelectedGhost(sender, chatId, buttonCallbackData.split(" ")[1]);
                break;

            case Constants.GHOST_SOLVER_CALLBACK:
                GhostSolverModeHandler.replyToGhostSolverSelected(sender, chatId, ghostSearchParameters);
                break;
            case Constants.GHOST_SOLVER_SET_SPEED_MODE_CALLBACK:
                GhostSolverModeHandler.replyToSetSpeedMode(sender, chatId, ghostSearchParameters);
                break;
            case Constants.GHOST_SOLVER_SET_SPEED_ACTION_CALLBACK:
                GhostSolverModeHandler.changeSpeed(chatId, buttonCallbackData.split(" ")[1], ghostSearchParameters);
                GhostSolverModeHandler.replyToSetSpeedMode(sender, chatId, ghostSearchParameters);
                break;
            case Constants.GHOST_SOLVER_SET_BLINK_FREQUENCY_MODE_CALLBACK:
                GhostSolverModeHandler.replyToSetBlinkFrequencyMode(sender, chatId, ghostSearchParameters);
                break;
            case Constants.GHOST_SOLVER_SET_BLINK_FREQUENCY_ACTION_CALLBACK:
                GhostSolverModeHandler.changeBlinkFrequency(chatId, buttonCallbackData.split(" ")[1],
                        ghostSearchParameters);
                GhostSolverModeHandler.replyToSetBlinkFrequencyMode(sender, chatId, ghostSearchParameters);
                break;
            case Constants.GHOST_SOLVER_SET_CURRENT_SANITY_MODE_CALLBACK:
                break;
            case Constants.GHOST_SOLVER_SET_CURRENT_SANITY_ACTION_CALLBACK:
                break;
            case Constants.GHOST_SOLVER_SET_EVIDENCES_MODE_CALLBACK:
                break;
            case Constants.GHOST_SOLVER_SET_EVIDENCES_ACTION_CALLBACK:
                break;
            case Constants.GHOST_SOLVER_GET_POSSIBLE_GHOSTS_CALLBACK:
                break;
        }
    }
}
