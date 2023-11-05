package com.phasmoghostbot.telegrambot.impl.responseHandler;

import java.util.List;

import org.telegram.abilitybots.api.sender.SilentSender;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

import com.phasmoghostbot.telegrambot.constants.Constants;
import com.phasmoghostbot.telegrambot.impl.keyboardFactory.information_mode.SelectEvidenceInformationKeyboardFactory;
import com.phasmoghostbot.telegrambot.impl.keyboardFactory.information_mode.SelectGhostInformationKeyboardFactory;
import com.phasmoghostbot.telegrambot.impl.keyboardFactory.information_mode.SelectWhichInformationKeyboardFactory;
import com.phasmoghostbot.telegrambot.models.Ability;
import com.phasmoghostbot.telegrambot.models.Evidence;
import com.phasmoghostbot.telegrambot.models.Ghost;
import com.phasmoghostbot.telegrambot.models.HuntStartingCondition;

import lombok.AllArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Setter
public class InformationModeHandler {

    private SilentSender sender;

    public void replyToInformationMode(Long chatId, int messageId) {
        EditMessageText message = new EditMessageText();
        message.setText(Constants.INFORMATION_MODE_MESSAGE);
        message.setReplyMarkup((InlineKeyboardMarkup) new SelectWhichInformationKeyboardFactory().generateKeyboard());
        message.setChatId(chatId);
        message.setMessageId(messageId);

        sender.execute(message);
    }

    public void replyToInformationEvidenceMode(Long chatId, int messageId) {
        EditMessageText message = new EditMessageText();
        message.setText(Constants.INFORMATION_MODE_EVIDENCE_MESSAGE);
        message.setChatId(chatId);
        message.setMessageId(messageId);
        message.setReplyMarkup(
                (InlineKeyboardMarkup) new SelectEvidenceInformationKeyboardFactory().generateKeyboard());

        sender.execute(message);
    }

    public void replyToInformationGhostMode(long chatId, int messageId) {
        EditMessageText message = new EditMessageText();
        message.setText(Constants.INFORMATION_MODE_GHOST_MESSAGE);
        message.setChatId(chatId);
        message.setMessageId(messageId);
        message.setReplyMarkup((InlineKeyboardMarkup) new SelectGhostInformationKeyboardFactory().generateKeyboard());

        sender.execute(message);
    }

    public void replyToSelectedEvidence(long chatId, int messageId, String evidenceName) {
        Evidence evidence = findEvidenceByName(evidenceName);
        StringBuilder messageText = new StringBuilder();

        if (evidence == null) {
            messageText.append("Wrong evidence name" + "\n");
        } else
            messageText.append("Name: " + evidence.getName() + "\n" + "Mechanics: " + evidence.getMechanics() + "\n");
        messageText.append(Constants.INFORMATION_MODE_EVIDENCE_MESSAGE);

        EditMessageText message = new EditMessageText();
        message.setText(messageText.toString());
        message.setChatId(chatId);
        message.setMessageId(messageId);
        message.setReplyMarkup(
                (InlineKeyboardMarkup) new SelectEvidenceInformationKeyboardFactory().generateKeyboard());

        sender.execute(message);
    }

    public void replyToSelectedGhost(long chatId, int messageId, String ghostName) {
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
        messageText.append(Constants.INFORMATION_MODE_GHOST_MESSAGE);

        EditMessageText message = new EditMessageText();
        message.setText(messageText.toString());
        message.setChatId(chatId);
        message.setMessageId(messageId);
        message.setReplyMarkup((InlineKeyboardMarkup) new SelectGhostInformationKeyboardFactory().generateKeyboard());

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
}
