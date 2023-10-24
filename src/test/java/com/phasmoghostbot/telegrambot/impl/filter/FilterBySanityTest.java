package com.phasmoghostbot.telegrambot.impl.filter;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.phasmoghostbot.telegrambot.api.dataLoader.GenerateXMLFilePath;
import com.phasmoghostbot.telegrambot.impl.dataLoader.GhostLoader;
import com.phasmoghostbot.telegrambot.models.Ghost;

public class FilterBySanityTest {

    private final Integer EXPECTED_GHOST_COUNT = 7;
    private final Integer CURRENT_SANITY = 60;
    private final List<Ghost> GHOST_LIST = new GhostLoader().load(GenerateXMLFilePath.getFileLocation());

    @Test
    void testFilter() {
        FilterBySanity filter = new FilterBySanity(CURRENT_SANITY);

        List<Ghost> filteredGhosts = filter.filter(GHOST_LIST);

        Assertions.assertEquals(EXPECTED_GHOST_COUNT, filteredGhosts.size());
    }
}
