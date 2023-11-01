package com.phasmoghostbot.telegrambot.impl.responseHandler;

import java.util.Map;

import org.telegram.abilitybots.api.sender.SilentSender;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import com.phasmoghostbot.telegrambot.constants.Constants;
import com.phasmoghostbot.telegrambot.impl.keyboardFactory.ghostSolver.GhostSolverActionsKeyboard;
import com.phasmoghostbot.telegrambot.models.Evidence;
import com.phasmoghostbot.telegrambot.models.GhostSearchParameters;

public class GhostSolverModeHandler {
    public static void replyToGhostSolverSelected(SilentSender sender, Long chatId,
            Map<Long, GhostSearchParameters> ghostSearchParameters) {
        SendMessage message = new SendMessage();
        GhostSearchParameters parameters = ghostSearchParameters.get(chatId);

        if (parameters == null) {
            parameters = new GhostSearchParameters();
            ghostSearchParameters.put(chatId, parameters);
        }

        StringBuilder messageText = new StringBuilder();
        messageText.append(Constants.GHOST_SOLVER_MESSAGE + "\n");
        messageText.append("Current sanity = " + parameters.getCurrentSanity() + "\n");
        messageText.append("Speed = " + parameters.getSpeed() + "\n");
        messageText.append("\nEvidences =  + \n");
        if (parameters.getEvidences().size() > 0)
            for (Evidence evidence : parameters.getEvidences()) {
                messageText.append(evidence.getName() + "\n");
            }
        else
            messageText.append("No evidences");

        message.setChatId(chatId);
        message.setText(messageText.toString());
        message.setReplyMarkup(new GhostSolverActionsKeyboard().generateKeyboard());

        sender.execute(message);
    }
}
