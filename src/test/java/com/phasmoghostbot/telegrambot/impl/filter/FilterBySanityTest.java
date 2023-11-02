package com.phasmoghostbot.telegrambot.impl.filter;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.phasmoghostbot.telegrambot.api.filter.exceptions.SanityValueIsNegativeException;
import com.phasmoghostbot.telegrambot.constants.Constants;
import com.phasmoghostbot.telegrambot.models.Ghost;

public class FilterBySanityTest {

    private final Integer EXPECTED_60_SANITY_GHOST_COUNT = 7;
    private final Integer CURRENT_SANITY_60 = 60;

    private final Integer EXPECTED_ZERO_SANITY_GHOST_COUNT = 24;
    private final Integer CURRENT_SANITY_0 = 0;

    private final Integer NEGATIVE_SANITY = -1;

    @Test
    void testFilter() {
        FilterBySanity filter = new FilterBySanity(CURRENT_SANITY_60);

        List<Ghost> filteredGhosts = filter.filter(Constants.GHOST_LIST);

        Assertions.assertEquals(EXPECTED_60_SANITY_GHOST_COUNT, filteredGhosts.size());
    }

    @Test
    void testFilterZeroSanity() {
        FilterBySanity filter = new FilterBySanity(CURRENT_SANITY_0);

        List<Ghost> filteredGhosts = filter.filter(Constants.GHOST_LIST);

        Assertions.assertEquals(EXPECTED_ZERO_SANITY_GHOST_COUNT, filteredGhosts.size());
    }

    @Test
    void testFilterNegativeSanity() {
        FilterBySanity filter = new FilterBySanity(NEGATIVE_SANITY);

        Assertions.assertThrows(SanityValueIsNegativeException.class, () -> filter.filter(Constants.GHOST_LIST));
    }
}
