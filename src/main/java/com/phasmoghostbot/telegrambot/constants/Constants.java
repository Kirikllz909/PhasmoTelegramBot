package com.phasmoghostbot.telegrambot.constants;

import java.util.List;

import com.phasmoghostbot.telegrambot.api.dataLoader.GenerateXMLFilePath;
import com.phasmoghostbot.telegrambot.impl.dataLoader.EvidenceLoader;
import com.phasmoghostbot.telegrambot.impl.dataLoader.GhostLoader;
import com.phasmoghostbot.telegrambot.models.Evidence;
import com.phasmoghostbot.telegrambot.models.Ghost;

public interface Constants {
    List<Ghost> GHOST_LIST = new GhostLoader().load(GenerateXMLFilePath.getFileLocation());
    List<Evidence> EVIDENCE_LIST = new EvidenceLoader().load(GenerateXMLFilePath.getFileLocation());

    String BACK_BUTTON_TEXT = "<< Back";

    String CHAT_SEARCH_PARAMS = "GHOST_SEARCH_PARAMS";

    String START_MODE = "START";
    String START_DESCRIPTION = "Start ghost hunting or check the information about each ghost or evidence";

    String SELECT_MODE_MESSAGE = "Select mode";

    String SELECT_MODE_BUTTON_INFORMATION = "Information";
    String SELECT_WHICH_MODE_INFORMATION_MESSAGE = "Select what information do you need";

    String SELECTED_MODE_BUTTON_INFORMATION_EVIDENCE = "Evidence";
    String SELECTED_MODE_BUTTON_INFORMATION_EVIDENCE_MESSAGE = "Here you can check information about any evidence";
    String SELECTED_EVIDENCE = "Selected_evidence";

    String SELECTED_MODE_BUTTON_INFORMATION_GHOST = "Ghost";
    String SELECTED_MODE_BUTTON_INFORMATION_GHOST_MESSAGE = "Here you can check information about any ghost";
    String SELECTED_GHOST = "Selected_ghost";

    String SELECT_MODE_BUTTON_GHOST_SOLVER = "Ghost_solver";
}
