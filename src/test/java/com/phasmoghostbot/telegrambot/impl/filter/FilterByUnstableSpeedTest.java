package com.phasmoghostbot.telegrambot.impl.filter;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.phasmoghostbot.telegrambot.api.dataLoader.GenerateXMLFilePath;
import com.phasmoghostbot.telegrambot.impl.dataLoader.GhostLoader;
import com.phasmoghostbot.telegrambot.models.Ghost;

public class FilterByUnstableSpeedTest {

    private final Integer EXPECTED_GHOST_COUNT = 9;

    @Test
    void testFilter() {
        List<Ghost> ghostList = new GhostLoader().load(GenerateXMLFilePath.getFileLocation());
        FilterByUnstableSpeed filter = new FilterByUnstableSpeed();

        List<Ghost> filteredGhost = filter.filter(ghostList);

        Assertions.assertEquals(EXPECTED_GHOST_COUNT, filteredGhost.size());
    }
}
