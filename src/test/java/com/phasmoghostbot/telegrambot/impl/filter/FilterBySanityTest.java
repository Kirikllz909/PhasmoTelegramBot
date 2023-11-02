package com.phasmoghostbot.telegrambot.impl.filter;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.phasmoghostbot.telegrambot.constants.Constants;
import com.phasmoghostbot.telegrambot.models.Ghost;

public class FilterBySanityTest {

    private final Integer EXPECTED_GHOST_COUNT = 7;
    private final Integer CURRENT_SANITY = 60;

    @Test
    void testFilter() {
        FilterBySanity filter = new FilterBySanity(CURRENT_SANITY);

        List<Ghost> filteredGhosts = filter.filter(Constants.GHOST_LIST);

        Assertions.assertEquals(EXPECTED_GHOST_COUNT, filteredGhosts.size());
    }
}
