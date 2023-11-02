package com.phasmoghostbot.telegrambot.impl.filter;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.phasmoghostbot.telegrambot.constants.Constants;
import com.phasmoghostbot.telegrambot.models.Ghost;

public class FilterByDefaultSpeedTest {

    private final Integer EXPECTED_GHOST_COUNT = 15;

    @Test
    void testFilter() {
        FilterByDefaultSpeed filter = new FilterByDefaultSpeed();

        List<Ghost> filteredGhost = filter.filter(Constants.GHOST_LIST);

        Assertions.assertEquals(EXPECTED_GHOST_COUNT, filteredGhost.size());
    }
}
