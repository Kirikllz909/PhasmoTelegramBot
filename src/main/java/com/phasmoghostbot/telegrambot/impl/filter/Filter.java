package com.phasmoghostbot.telegrambot.impl.filter;

import java.util.ArrayList;
import java.util.List;

import com.phasmoghostbot.telegrambot.api.filter.BasicFilter;
import com.phasmoghostbot.telegrambot.models.Ghost;

public class Filter {
    public static List<Ghost> filter(List<Ghost> ghosts, List<BasicFilter> rules) {
        List<Ghost> ghostsAfterFilter = new ArrayList<>(ghosts);
        for (BasicFilter rule : rules) {
            ghostsAfterFilter = rule.filter(ghostsAfterFilter);
        }
        return ghostsAfterFilter;
    }
}
