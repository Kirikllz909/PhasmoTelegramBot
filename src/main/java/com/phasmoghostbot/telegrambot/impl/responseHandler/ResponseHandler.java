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
                replyToInformationMode(chatId);
                break;
            case Constants.SELECTED_MODE_BUTTON_INFORMATION_EVIDENCE:
                replyToInformationEvidenceMode(chatId);
                break;
            case Constants.SELECTED_MODE_BUTTON_INFORMATION_GHOST:
                replyToInformationGhostMode(chatId);
                break;
            case Constants.SELECTED_EVIDENCE:
                replyToSelectedEvidence(chatId, buttonCallbackData.split(" ")[1]);
                break;
            case Constants.SELECTED_GHOST:
                replyToSelectedGhost(chatId, buttonCallbackData.split(" ")[1]);
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

    private void replyToInformationEvidenceMode(long chatId) {
        SendMessage message = new SendMessage();
        message.setText(Constants.SELECTED_MODE_BUTTON_INFORMATION_EVIDENCE_MESSAGE);
        message.setChatId(chatId);
        message.setReplyMarkup(new SelectEvidenceInformationKeyboardFactory().generateKeyboard());

        sender.execute(message);
    }

    private void replyToInformationGhostMode(long chatId) {
        SendMessage message = new SendMessage();
        message.setText(Constants.SELECTED_MODE_BUTTON_INFORMATION_GHOST_MESSAGE);
        message.setChatId(chatId);
        message.setReplyMarkup(new SelectGhostInformationKeyboardFactory().generateKeyboard());

        sender.execute(message);
    }

    private void replyToSelectedEvidence(long chatId, String evidenceName) {
        Evidence evidence = findEvidenceByName(evidenceName);
        StringBuilder messageText = new StringBuilder();

        if (evidence == null) {
            messageText.append("Wrong evidence name" + "\n");
        } else
            messageText.append("Name: " + evidence.getName() + "\n" + "Mechanics: " + evidence.getMechanics() + "\n");
        messageText.append(Constants.SELECTED_MODE_BUTTON_INFORMATION_EVIDENCE_MESSAGE);

        SendMessage message = new SendMessage();
        message.setText(messageText.toString());
        message.setChatId(chatId);
        message.setReplyMarkup(new SelectEvidenceInformationKeyboardFactory().generateKeyboard());

        sender.execute(message);
    }

    private Evidence findEvidenceByName(String evidenceName) {
        Evidence evidence = null;
        for (int i = 0; i < Constants.GHOST_LIST.size(); i++) {
            Evidence currentEvidence = Constants.EVIDENCE_LIST.get(i);
            if (currentEvidence.getName().toLowerCase().equals(evidenceName.toLowerCase())) {
                evidence = currentEvidence;
                break;
            }
        }
        return evidence;
    }

    private void replyToSelectedGhost(long chatId, String ghostName) {
        Ghost ghost = findGhostByName(ghostName);
        StringBuilder messageText = new StringBuilder();

        if (ghost == null) {
            messageText.append("Wrong ghost name" + "\n");
        } else {
            messageText.append("Name: " + ghost.getName() + "\n");
            messageText.append("Blink frequency: " + ghost.getBlinkFrequency() + "\n");
            messageText.append("Speed: " + ghost.getSpeed() + "\n");

            messageText.append("Evidences: " + "\n");
            List<Evidence> evidences = ghost.getGhostEvidences();
            for (Evidence evidence : evidences) {
                messageText.append("  " + evidence.getName() + "\n");
            }

            List<HuntStartingCondition> startingConditions = ghost.getHuntStartingSanity();
            messageText.append("Hunt starting sanity and it's condition: " + "\n");
            for (HuntStartingCondition startingCondition : startingConditions) {
                messageText.append(startingCondition.getName() + ": " + startingCondition.getValue() + "\n");
            }

            List<Ability> ghostAbilities = ghost.getSpecialAbilities();
            messageText.append("Ghost abilities and it's description: " + "\n");
            for (Ability ghostAbility : ghostAbilities) {
                messageText.append(
                        "**" + ghostAbility.getName() + "**" + "\n" + ghostAbility.getDescription() + "\n" + "\n");
            }
        }
        messageText.append(Constants.SELECTED_MODE_BUTTON_INFORMATION_GHOST_MESSAGE);

        SendMessage message = new SendMessage();
        message.setText(messageText.toString());
        message.setChatId(chatId);
        message.setReplyMarkup(new SelectGhostInformationKeyboardFactory().generateKeyboard());

        sender.execute(message);
    }

    private Ghost findGhostByName(String ghostName) {
        Ghost ghost = null;
        for (int i = 0; i < Constants.GHOST_LIST.size(); i++) {
            Ghost currentGhost = Constants.GHOST_LIST.get(i);
            if (currentGhost.getName().toLowerCase().equals(ghostName.toLowerCase())) {
                ghost = currentGhost;
                break;
            }
        }
        return ghost;
    }
}
