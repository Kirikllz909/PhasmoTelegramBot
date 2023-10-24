package com.phasmoghostbot.telegrambot.api.filter;

import java.util.List;

import com.phasmoghostbot.telegrambot.models.Ghost;

public interface BasicFilter {
    public List<Ghost> filter(List<Ghost> ghosts);
}
