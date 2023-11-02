package com.phasmoghostbot.telegrambot.impl.filter;

import java.util.List;

import com.phasmoghostbot.telegrambot.api.filter.BasicFilter;
import com.phasmoghostbot.telegrambot.models.Ghost;

public class FilterByUnusualBlinkFrequency implements BasicFilter {

    private static final String DEFAULT_FREQUENCY = "Default";

    @Override
    public List<Ghost> filter(List<Ghost> ghosts) {

        return ghosts.stream()
                .filter(FilterByUnusualBlinkFrequency::isFrequencyUnusual)
                .toList();
    }

    private static boolean isFrequencyUnusual(Ghost ghost) {
        return !ghost.getBlinkFrequency().toLowerCase().equals(DEFAULT_FREQUENCY.toLowerCase());
    }
}
