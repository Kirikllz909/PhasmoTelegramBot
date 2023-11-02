package com.phasmoghostbot.telegrambot.impl.filter;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.phasmoghostbot.telegrambot.api.filter.BasicFilter;
import com.phasmoghostbot.telegrambot.constants.Constants;
import com.phasmoghostbot.telegrambot.models.Ghost;

public class FilterByUnusualBlinkFrequencyTest {

    private final Integer GHOST_COUNT = 3;

    @Test
    void testFilter() {
        BasicFilter filter = new FilterByUnusualBlinkFrequency();

        List<Ghost> filteredGhosts = filter.filter(Constants.GHOST_LIST);

        Assertions.assertEquals(GHOST_COUNT, filteredGhosts.size());
    }
}
