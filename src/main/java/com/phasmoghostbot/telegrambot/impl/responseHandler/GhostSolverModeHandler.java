package com.phasmoghostbot.telegrambot.impl.responseHandler;

import java.util.List;
import java.util.Map;

import org.telegram.abilitybots.api.sender.SilentSender;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import com.phasmoghostbot.telegrambot.constants.Constants;
import com.phasmoghostbot.telegrambot.impl.keyboardFactory.ghostSolver.BlinkFrequencyEditKeyboard;
import com.phasmoghostbot.telegrambot.impl.keyboardFactory.ghostSolver.CurrentSanityEditKeyboard;
import com.phasmoghostbot.telegrambot.impl.keyboardFactory.ghostSolver.EvidenceEditKeyboard;
import com.phasmoghostbot.telegrambot.impl.keyboardFactory.ghostSolver.GhostSolverActionsKeyboard;
import com.phasmoghostbot.telegrambot.impl.keyboardFactory.ghostSolver.SpeedEditKeyboard;
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
        messageText.append("\nEvidences:  + \n");
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

    public static void replyToSetSpeedMode(SilentSender sender, long chatId,
            Map<Long, GhostSearchParameters> ghostSearchParameters) {
        SendMessage message = new SendMessage();
        GhostSearchParameters parameters = ghostSearchParameters.get(chatId);

        message.setChatId(chatId);
        message.setText("Current Speed: " + parameters.getSpeed());
        message.setReplyMarkup(new SpeedEditKeyboard().generateKeyboard());

        sender.execute(message);
    }

    public static void changeSpeed(long chatId, String newSpeed,
            Map<Long, GhostSearchParameters> ghostSearchParameters) {
        GhostSearchParameters parameters = ghostSearchParameters.get(chatId);

        parameters.setSpeed(newSpeed);

        ghostSearchParameters.put(chatId, parameters);
    }

    public static void replyToSetBlinkFrequencyMode(SilentSender sender, long chatId,
            Map<Long, GhostSearchParameters> ghostSearchParameters) {
        SendMessage message = new SendMessage();
        GhostSearchParameters parameters = ghostSearchParameters.get(chatId);

        message.setChatId(chatId);
        message.setText("Current blink frequency: " + parameters.getBlinkFrequency());
        message.setReplyMarkup(new BlinkFrequencyEditKeyboard().generateKeyboard());

        sender.execute(message);
    }

    public static void changeBlinkFrequency(long chatId, String newBlinkFrequency,
            Map<Long, GhostSearchParameters> ghostSearchParameters) {

        GhostSearchParameters parameters = ghostSearchParameters.get(chatId);

        parameters.setBlinkFrequency(newBlinkFrequency);

        ghostSearchParameters.put(chatId, parameters);
    }

    public static void replyToSetSanityMode(SilentSender sender, long chatId,
            Map<Long, GhostSearchParameters> ghostSearchParameters) {
        SendMessage message = new SendMessage();
        GhostSearchParameters parameters = ghostSearchParameters.get(chatId);

        message.setChatId(chatId);
        message.setText("Current sanity: " + parameters.getCurrentSanity());
        message.setReplyMarkup(new CurrentSanityEditKeyboard().generateKeyboard());

        sender.execute(message);
    }

    public static void changeSanity(long chatId, String newSanity,
            Map<Long, GhostSearchParameters> ghostSearchParameters) {

        GhostSearchParameters parameters = ghostSearchParameters.get(chatId);

        parameters.setCurrentSanity(Integer.valueOf(newSanity));

        ghostSearchParameters.put(chatId, parameters);
    }

    public static void replyToSetEvidences(SilentSender sender, long chatId,
            Map<Long, GhostSearchParameters> ghostSearchParameters) {
        GhostSearchParameters parameters = ghostSearchParameters.get(chatId);
        SendMessage message = new SendMessage();

        StringBuilder messageText = new StringBuilder();

        messageText.append("Current evidences: \n");
        if (parameters.getEvidences().size() == 0) {
            messageText.append("No evidences");
        } else {
            for (Evidence ev : parameters.getEvidences()) {
                messageText.append(ev.getName() + "\n");
            }
        }

        message.setChatId(chatId);
        message.setText(messageText.toString());
        message.setReplyMarkup(new EvidenceEditKeyboard().generateKeyboard());

        sender.execute(message);
    }

    public static void changeEvidence(long chatId, String evidenceId,
            Map<Long, GhostSearchParameters> ghostSearchParameters) {
        GhostSearchParameters parameters = ghostSearchParameters.get(chatId);
        List<Evidence> evidenceList = parameters.getEvidences();

        for (int i = 0; i < evidenceList.size(); i++) {
            Evidence ev = evidenceList.get(i);
            if (ev.getId() == evidenceId) {
                evidenceList.remove(i);
                break;
            }
        }
        parameters.setEvidences(evidenceList);

        ghostSearchParameters.put(chatId, parameters);
    }
}
