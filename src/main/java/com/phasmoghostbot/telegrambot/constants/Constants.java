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
    String FIRST_MESSAGES = "FIRST_MESSAGES";

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
    String GHOST_SOLVER_MESSAGE = "Current ghost search parameters:";

    String GHOST_SOLVER_SET_SPEED_MODE_MESSAGE = "Change speed";
    String GHOST_SOLVER_SET_SPEED_MODE_CALLBACK = "Set_speed_mode";
    String GHOST_SOLVER_SET_SPEED_ACTION_CALLBACK = "Change_speed";

    String GHOST_SOLVER_SET_CURRENT_SANITY_MODE_MESSAGE = "Change current sanity";
    String GHOST_SOLVER_SET_CURRENT_SANITY_MODE_CALLBACK = "Set_sanity_mode";
    String GHOST_SOLVER_SET_CURRENT_SANITY_ACTION_CALLBACK = "Change_sanity";

    String GHOST_SOLVER_SET_EVIDENCES_MODE_MESSAGE = "Change ghost evidences";
    String GHOST_SOLVER_SET_EVIDENCES_MODE_CALLBACK = "Change_evidences_mode";
    String GHOST_SOLVER_SET_EVIDENCES_ACTION_CALLBACK = "Reverse_evidence_state";

    String GHOST_SOLVER_SET_BLINK_FREQUENCY_MODE_MESSAGE = "Change blink frequency";
    String GHOST_SOLVER_SET_BLINK_FREQUENCY_MODE_CALLBACK = "Change_blink_frequency_mode";
    String GHOST_SOLVER_SET_BLINK_FREQUENCY_ACTION_CALLBACK = "Change_blink_frequency";

    String GHOST_SOLVER_GET_POSSIBLE_GHOSTS_MESSAGE = "Get possible ghosts";
    String GHOST_SOLVER_GET_POSSIBLE_GHOSTS_CALLBACK = "Get_possible_ghosts";
}
