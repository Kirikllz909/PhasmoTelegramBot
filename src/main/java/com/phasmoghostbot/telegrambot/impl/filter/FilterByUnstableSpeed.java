package com.phasmoghostbot.telegrambot.impl.filter;

import java.util.List;

import com.phasmoghostbot.telegrambot.api.filter.BasicFilter;
import com.phasmoghostbot.telegrambot.models.Ghost;

public class FilterByUnstableSpeed implements BasicFilter {

    @Override
    public List<Ghost> filter(List<Ghost> ghosts) {
        return ghosts.stream()
                .filter(FilterByUnstableSpeed::isSpeedUnstable)
                .toList();
    }

    // ====Implementations====

    private static boolean isSpeedUnstable(Ghost ghost) {
        return !ghost.getSpeed().toLowerCase().equals("default");
    }
}
