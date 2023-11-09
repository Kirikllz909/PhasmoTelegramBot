package com.phasmoghostbot.telegrambot.impl.responseHandler;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.telegram.abilitybots.api.db.DBContext;
import org.telegram.abilitybots.api.sender.SilentSender;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

import com.phasmoghostbot.telegrambot.constants.Constants;
import com.phasmoghostbot.telegrambot.impl.keyboardFactory.selection.SelectKeyboardFactory;
import com.phasmoghostbot.telegrambot.models.GhostSearchParameters;

public class ResponseHandler {

    private final SilentSender sender;
    private final Map<Long, GhostSearchParameters> ghostSearchParameters;
    private final Map<Long, Integer> firstMessagesId;

    private InformationModeHandler informationModeHandler;
    private GhostSolverModeHandler ghostSolverModeHandler;

    public ResponseHandler(SilentSender sender, DBContext db) {
        this.sender = sender;
        ghostSearchParameters = new HashMap<>();
        firstMessagesId = db.getMap(Constants.FIRST_MESSAGES);
        ghostSolverModeHandler = new GhostSolverModeHandler(sender, ghostSearchParameters);
        informationModeHandler = new InformationModeHandler(sender);
    }

    public void replyToStart(long chatId) {
        boolean isFirstMessageWasSent = firstMessagesId.get(chatId) == null ? false : true;

        if (isFirstMessageWasSent) {
            EditMessageText message = new EditMessageText();

            message.setChatId(chatId);
            message.setReplyMarkup((InlineKeyboardMarkup) new SelectKeyboardFactory().generateKeyboard());
            message.setText(Constants.SELECT_MESSAGE);
            message.setMessageId(firstMessagesId.get(chatId));

            sender.execute(message);
        } else {
            SendMessage messageToSend = new SendMessage();

            messageToSend.setText(Constants.SELECT_MESSAGE);
            messageToSend.setChatId(chatId);
            messageToSend.setReplyMarkup(new SelectKeyboardFactory().generateKeyboard());

            Optional<Message> sendedMessage = sender.execute(messageToSend);

            if (sendedMessage.isPresent()) {
                int messageId = sendedMessage.get().getMessageId();
                firstMessagesId.put(chatId, messageId);
            }
        }
    }

    public void replyToButtons(Update update) {
        long chatId = update.getCallbackQuery().getMessage().getChatId();
        int messageId = firstMessagesId.get(chatId);
        String buttonCallbackData = update.getCallbackQuery().getData();

        String[] callback = buttonCallbackData.split(" ");
        String callbackAction = callback[0];
        String callbackData = "";
        if (callback.length > 1) {
            for (int i = 1; i < callback.length; i++) {
                callbackData += callback[i] + " ";
            }
            callbackData = callbackData.trim();
        }

        switch (callbackAction) {
            case Constants.START_MODE:
                replyToStart(chatId);
                break;

            case Constants.INFORMATION_MODE_CALLBACK:
                informationModeHandler.replyToInformationMode(chatId, messageId);
                break;
            case Constants.INFORMATION_MODE_EVIDENCE_CALLBACK:
                informationModeHandler.replyToInformationEvidenceMode(chatId, messageId);
                break;
            case Constants.INFORMATION_MODE_GHOST_CALLBACK:
                informationModeHandler.replyToInformationGhostMode(chatId, messageId);
                break;
            case Constants.INFORMATION_MODE_SELECTED_EVIDENCE_CALLBACK:
                informationModeHandler.replyToSelectedEvidence(chatId, messageId, callbackData);
                break;
            case Constants.INFORMATION_MODE_SELECTED_GHOST_CALLBACK:
                informationModeHandler.replyToSelectedGhost(chatId, messageId, callbackData);
                break;

            case Constants.GHOST_SOLVER_CALLBACK:
                ghostSolverModeHandler.replyToGhostSolverSelected(chatId, messageId);
                break;
            case Constants.GHOST_SOLVER_SET_SPEED_MODE_CALLBACK:
                ghostSolverModeHandler.replyToSetSpeedMode(chatId, messageId);
                break;
            case Constants.GHOST_SOLVER_SET_SPEED_ACTION_CALLBACK:
                ghostSolverModeHandler.replyToChangeSpeedAction(chatId, messageId, callbackData);
                break;
            case Constants.GHOST_SOLVER_SET_BLINK_FREQUENCY_MODE_CALLBACK:
                ghostSolverModeHandler.replyToSetBlinkFrequencyMode(chatId, messageId);
                break;
            case Constants.GHOST_SOLVER_SET_BLINK_FREQUENCY_ACTION_CALLBACK:
                ghostSolverModeHandler.replyToChangeBlinkFrequencyAction(chatId,
                        messageId, callbackData);
                break;
            case Constants.GHOST_SOLVER_SET_CURRENT_SANITY_MODE_CALLBACK:
                ghostSolverModeHandler.replyToSetSanityMode(chatId, messageId);
                break;
            case Constants.GHOST_SOLVER_SET_CURRENT_SANITY_ACTION_CALLBACK:
                ghostSolverModeHandler.replyToSetSanityAction(chatId, messageId, callbackData);
                break;
            case Constants.GHOST_SOLVER_SET_EVIDENCES_MODE_CALLBACK:
                ghostSolverModeHandler.replyToSetEvidences(chatId, messageId);
                break;
            case Constants.GHOST_SOLVER_SET_EVIDENCES_ACTION_CALLBACK:
                ghostSolverModeHandler.replyToSetEvidenceAction(chatId, messageId, callbackData);
                break;
            case Constants.GHOST_SOLVER_GET_POSSIBLE_GHOSTS_CALLBACK:
                ghostSolverModeHandler.replyToGetGhosts(chatId, messageId);
                break;
        }
    }
}
