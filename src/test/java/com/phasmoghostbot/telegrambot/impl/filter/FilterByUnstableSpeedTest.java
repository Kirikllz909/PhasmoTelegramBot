package com.phasmoghostbot.telegrambot.impl.filter;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.phasmoghostbot.telegrambot.constants.Constants;
import com.phasmoghostbot.telegrambot.models.Ghost;

public class FilterByUnstableSpeedTest {

    private final Integer EXPECTED_GHOST_COUNT = 9;

    @Test
    void testFilter() {
        FilterByUnstableSpeed filter = new FilterByUnstableSpeed();

        List<Ghost> filteredGhost = filter.filter(Constants.GHOST_LIST);

        Assertions.assertEquals(EXPECTED_GHOST_COUNT, filteredGhost.size());
    }
}
