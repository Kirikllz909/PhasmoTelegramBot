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

    String SELECT_MESSAGE = "Select mode";

    String INFORMATION_MODE_CALLBACK = "Information";
    String INFORMATION_MODE_MESSAGE = "Select what information do you need";

    String INFORMATION_MODE_EVIDENCE_CALLBACK = "Evidence";
    String INFORMATION_MODE_EVIDENCE_MESSAGE = "Here you can check information about any evidence";
    String INFORMATION_MODE_SELECTED_EVIDENCE_CALLBACK = "Selected_evidence";

    String INFORMATION_MODE_GHOST_CALLBACK = "Ghost";
    String INFORMATION_MODE_GHOST_MESSAGE = "Here you can check information about any ghost";
    String INFORMATION_MODE_SELECTED_GHOST_CALLBACK = "Selected_ghost";

    String GHOST_SOLVER_CALLBACK = "Ghost_solver";
}
