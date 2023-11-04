package com.phasmoghostbot.telegrambot.impl.responseHandler;

import java.util.Map;

import org.telegram.abilitybots.api.db.DBContext;
import org.telegram.abilitybots.api.sender.SilentSender;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import com.phasmoghostbot.telegrambot.constants.Constants;
import com.phasmoghostbot.telegrambot.impl.keyboardFactory.selection.SelectKeyboardFactory;
import com.phasmoghostbot.telegrambot.models.GhostSearchParameters;

public class ResponseHandler {

    private final SilentSender sender;
    private final Map<Long, GhostSearchParameters> ghostSearchParameters;

    private InformationModeHandler informationModeHandler;
    private GhostSolverModeHandler ghostSolverModeHandler;

    public ResponseHandler(SilentSender sender, DBContext db) {
        this.sender = sender;
        ghostSearchParameters = db.getMap(Constants.CHAT_SEARCH_PARAMS);
        ghostSolverModeHandler = new GhostSolverModeHandler(sender, ghostSearchParameters);
        informationModeHandler = new InformationModeHandler(sender);
    }

    public void replyToStart(long chatId) {
        SendMessage message = new SendMessage();
        message.setText(Constants.SELECT_MESSAGE);
        message.setChatId(chatId);
        message.setReplyMarkup(new SelectKeyboardFactory().generateKeyboard());

        sender.execute(message);
    }

    public void replyToButtons(long chatId, String buttonCallbackData) {
        switch (buttonCallbackData.split(" ")[0]) {
            case Constants.START_MODE:
                replyToStart(chatId);
                break;

            case Constants.INFORMATION_MODE_CALLBACK:
                informationModeHandler.replyToInformationMode(chatId);
                break;
            case Constants.INFORMATION_MODE_EVIDENCE_CALLBACK:
                informationModeHandler.replyToInformationEvidenceMode(chatId);
                break;
            case Constants.INFORMATION_MODE_GHOST_CALLBACK:
                informationModeHandler.replyToInformationGhostMode(chatId);
                break;
            case Constants.INFORMATION_MODE_SELECTED_EVIDENCE_CALLBACK:
                informationModeHandler.replyToSelectedEvidence(chatId, buttonCallbackData.split(" ")[1]);
                break;
            case Constants.INFORMATION_MODE_SELECTED_GHOST_CALLBACK:
                informationModeHandler.replyToSelectedGhost(chatId, buttonCallbackData.split(" ")[1]);
                break;

            case Constants.GHOST_SOLVER_CALLBACK:
                ghostSolverModeHandler.replyToGhostSolverSelected(chatId);
                break;
            case Constants.GHOST_SOLVER_SET_SPEED_MODE_CALLBACK:
                ghostSolverModeHandler.replyToSetSpeedMode(chatId);
                break;
            case Constants.GHOST_SOLVER_SET_SPEED_ACTION_CALLBACK:
                ghostSolverModeHandler.replyToChangeSpeedAction(chatId, buttonCallbackData.split(" ")[1]);
                break;
            case Constants.GHOST_SOLVER_SET_BLINK_FREQUENCY_MODE_CALLBACK:
                ghostSolverModeHandler.replyToSetBlinkFrequencyMode(chatId);
                break;
            case Constants.GHOST_SOLVER_SET_BLINK_FREQUENCY_ACTION_CALLBACK:
                ghostSolverModeHandler.replyToChangeBlinkFrequencyAction(chatId, buttonCallbackData.split(" ")[1]);
                break;
            case Constants.GHOST_SOLVER_SET_CURRENT_SANITY_MODE_CALLBACK:
                ghostSolverModeHandler.replyToSetSanityMode(chatId);
                break;
            case Constants.GHOST_SOLVER_SET_CURRENT_SANITY_ACTION_CALLBACK:
                ghostSolverModeHandler.replyToSetSanityAction(chatId, buttonCallbackData.split(" ")[1]);
                break;
            case Constants.GHOST_SOLVER_SET_EVIDENCES_MODE_CALLBACK:
                ghostSolverModeHandler.replyToSetEvidences(chatId);
                break;
            case Constants.GHOST_SOLVER_SET_EVIDENCES_ACTION_CALLBACK:
                ghostSolverModeHandler.replyToSetEvidenceAction(chatId, buttonCallbackData.split(" ")[1]);
                break;
            case Constants.GHOST_SOLVER_GET_POSSIBLE_GHOSTS_CALLBACK:
                ghostSolverModeHandler.replyToGetGhosts(chatId);
                break;
        }
    }
}
