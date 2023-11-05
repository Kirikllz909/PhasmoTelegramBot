package com.phasmoghostbot.telegrambot.impl.responseHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import org.telegram.abilitybots.api.sender.SilentSender;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

import com.phasmoghostbot.telegrambot.api.filter.BasicFilter;
import com.phasmoghostbot.telegrambot.constants.Constants;
import com.phasmoghostbot.telegrambot.impl.filter.Filter;
import com.phasmoghostbot.telegrambot.impl.filter.FilterByDefaultBlinkFrequency;
import com.phasmoghostbot.telegrambot.impl.filter.FilterByDefaultSpeed;
import com.phasmoghostbot.telegrambot.impl.filter.FilterByEvidence;
import com.phasmoghostbot.telegrambot.impl.filter.FilterBySanity;
import com.phasmoghostbot.telegrambot.impl.filter.FilterByUnstableSpeed;
import com.phasmoghostbot.telegrambot.impl.filter.FilterByUnusualBlinkFrequency;
import com.phasmoghostbot.telegrambot.impl.keyboardFactory.ghostSolver.BackButtonKeyboard;
import com.phasmoghostbot.telegrambot.impl.keyboardFactory.ghostSolver.BlinkFrequencyEditKeyboard;
import com.phasmoghostbot.telegrambot.impl.keyboardFactory.ghostSolver.CurrentSanityEditKeyboard;
import com.phasmoghostbot.telegrambot.impl.keyboardFactory.ghostSolver.EvidenceEditKeyboard;
import com.phasmoghostbot.telegrambot.impl.keyboardFactory.ghostSolver.GhostSolverActionsKeyboard;
import com.phasmoghostbot.telegrambot.impl.keyboardFactory.ghostSolver.SpeedEditKeyboard;
import com.phasmoghostbot.telegrambot.models.Evidence;
import com.phasmoghostbot.telegrambot.models.Ghost;
import com.phasmoghostbot.telegrambot.models.GhostSearchParameters;

import lombok.AllArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Setter
public class GhostSolverModeHandler {

    private SilentSender sender;
    private Map<Long, GhostSearchParameters> ghostSearchParameters;

    public void replyToGhostSolverSelected(Long chatId, int messageId) {
        EditMessageText message = new EditMessageText();
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
        message.setMessageId(messageId);
        message.setText(messageText.toString());
        message.setReplyMarkup((InlineKeyboardMarkup) new GhostSolverActionsKeyboard().generateKeyboard());

        sender.execute(message);
    }

    public void replyToSetSpeedMode(long chatId, int messageId) {
        EditMessageText message = new EditMessageText();
        GhostSearchParameters parameters = ghostSearchParameters.get(chatId);

        message.setChatId(chatId);
        message.setMessageId(messageId);
        message.setText("Current Speed: " + parameters.getSpeed());
        message.setReplyMarkup((InlineKeyboardMarkup) new SpeedEditKeyboard().generateKeyboard());

        sender.execute(message);
    }

    public void replyToChangeSpeedAction(long chatId, int messageId, String newSpeed) {
        changeSpeed(chatId, newSpeed);
        replyToSetSpeedMode(chatId, messageId);
    }

    private void changeSpeed(long chatId, String newSpeed) {
        GhostSearchParameters parameters = ghostSearchParameters.get(chatId);

        parameters.setSpeed(newSpeed);

        ghostSearchParameters.put(chatId, parameters);
    }

    public void replyToSetBlinkFrequencyMode(long chatId, int messageId) {
        EditMessageText message = new EditMessageText();
        GhostSearchParameters parameters = ghostSearchParameters.get(chatId);

        message.setChatId(chatId);
        message.setMessageId(messageId);
        message.setText("Current blink frequency: " + parameters.getBlinkFrequency());
        message.setReplyMarkup((InlineKeyboardMarkup) new BlinkFrequencyEditKeyboard().generateKeyboard());

        sender.execute(message);
    }

    public void replyToChangeBlinkFrequencyAction(long chatId, int messageId, String newBlinkFrequency) {
        changeBlinkFrequency(chatId, newBlinkFrequency);
        replyToSetBlinkFrequencyMode(chatId, messageId);
    }

    private void changeBlinkFrequency(long chatId, String newBlinkFrequency) {

        GhostSearchParameters parameters = ghostSearchParameters.get(chatId);

        parameters.setBlinkFrequency(newBlinkFrequency);

        ghostSearchParameters.put(chatId, parameters);
    }

    public void replyToSetSanityMode(long chatId, int messageId) {
        EditMessageText message = new EditMessageText();
        GhostSearchParameters parameters = ghostSearchParameters.get(chatId);

        message.setChatId(chatId);
        message.setMessageId(messageId);
        message.setText("Current sanity: " + parameters.getCurrentSanity());
        message.setReplyMarkup((InlineKeyboardMarkup) new CurrentSanityEditKeyboard().generateKeyboard());

        sender.execute(message);
    }

    public void replyToSetSanityAction(long chatId, int messageId, String newSanity) {
        changeSanity(chatId, newSanity);
        replyToSetSanityMode(chatId, messageId);
    }

    private void changeSanity(long chatId, String newSanity) {

        GhostSearchParameters parameters = ghostSearchParameters.get(chatId);

        parameters.setCurrentSanity(Integer.valueOf(newSanity));

        ghostSearchParameters.put(chatId, parameters);
    }

    public void replyToSetEvidences(long chatId, int messageId) {
        GhostSearchParameters parameters = ghostSearchParameters.get(chatId);
        EditMessageText message = new EditMessageText();

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
        message.setMessageId(messageId);
        message.setText(messageText.toString());
        message.setReplyMarkup((InlineKeyboardMarkup) new EvidenceEditKeyboard().generateKeyboard());

        sender.execute(message);
    }

    public void replyToSetEvidenceAction(long chatId, int messageId, String evidenceId) {
        changeEvidenceState(chatId, evidenceId);
        replyToSetEvidences(chatId, messageId);
    }

    private void changeEvidenceState(long chatId, String evidenceId) {
        GhostSearchParameters parameters = ghostSearchParameters.get(chatId);
        List<Evidence> evidenceList = parameters.getEvidences();

        boolean isEvidenceInList = false;

        for (int i = 0; i < evidenceList.size(); i++) {
            Evidence ev = evidenceList.get(i);
            if (ev.getId() == evidenceId) {
                evidenceList.remove(i);
                isEvidenceInList = true;
                break;
            }
        }

        if (!isEvidenceInList) {
            evidenceList.add(findEvidenceById(evidenceId));
        }

        parameters.setEvidences(evidenceList);

        ghostSearchParameters.put(chatId, parameters);
    }

    private Evidence findEvidenceById(String id) {
        Predicate<Evidence> equalId = (ev) -> {
            if (ev.getId() == id)
                return true;
            return false;
        };
        return Constants.EVIDENCE_LIST.stream().filter(equalId).toList().get(0);
    }

    public void replyToGetGhosts(long chatId, int messageId) {
        GhostSearchParameters parameters = ghostSearchParameters.get(chatId);
        List<Ghost> filteredGhosts = findGhostsBySearchParameters(parameters);

        StringBuilder messageText = new StringBuilder();

        messageText.append("All possible ghosts: \n");
        for (int i = 0; i < filteredGhosts.size(); i++) {
            messageText.append(filteredGhosts.get(i).getName() + "\n");
        }

        EditMessageText message = new EditMessageText();
        message.setChatId(chatId);
        message.setMessageId(messageId);
        message.setText(messageText.toString());
        message.setReplyMarkup((InlineKeyboardMarkup) new BackButtonKeyboard().generateKeyboard());

        sender.execute(message);
    }

    private List<Ghost> findGhostsBySearchParameters(GhostSearchParameters parameters) {
        List<BasicFilter> filters = new ArrayList<>();

        // Select which filter use for the speed
        if (parameters.getSpeed().toLowerCase().equals("default"))
            filters.add(new FilterByDefaultSpeed());
        else
            filters.add(new FilterByUnstableSpeed());

        // Select which filter use for the blink frequency
        if (parameters.getBlinkFrequency().toLowerCase().equals("default"))
            filters.add(new FilterByDefaultBlinkFrequency());
        else
            filters.add(new FilterByUnusualBlinkFrequency());

        filters.add(new FilterBySanity(parameters.getCurrentSanity()));
        filters.add(new FilterByEvidence(parameters.getEvidences()));

        return Filter.filter(Constants.GHOST_LIST, filters);
    }
}
