package com.phasmoghostbot.telegrambot.impl.filter;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.phasmoghostbot.telegrambot.api.dataLoader.GenerateXMLFilePath;
import com.phasmoghostbot.telegrambot.impl.dataLoader.GhostLoader;
import com.phasmoghostbot.telegrambot.models.Ghost;

public class FilterByNameTest {

    private final Integer EXPECTED_ONE_GHOST = 1;
    private final Integer EXPECTED_NONE_GHOST = 0;
    private final String GHOST_NAME = "Banshee";
    private final String NON_EXISTENT_GHOST_NAME = "Abe";
    private final List<Ghost> GHOST_LIST = new GhostLoader().load(GenerateXMLFilePath.getFileLocation());

    @Test
    void testFilter() {
        FilterByName filterByName = new FilterByName(GHOST_NAME);

        List<Ghost> filteredGhost = filterByName.filter(GHOST_LIST);

        Assertions.assertEquals(EXPECTED_ONE_GHOST, filteredGhost.size());
    }

    @Test
    void testFilterByNonExistentGhost() {
        FilterByName filterByName = new FilterByName(NON_EXISTENT_GHOST_NAME);

        List<Ghost> filteredGhost = filterByName.filter(GHOST_LIST);

        Assertions.assertEquals(EXPECTED_NONE_GHOST, filteredGhost.size());
    }
}
