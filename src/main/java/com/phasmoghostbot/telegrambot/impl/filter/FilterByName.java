package com.phasmoghostbot.telegrambot.impl.filter;

import java.util.ArrayList;
import java.util.List;

import com.phasmoghostbot.telegrambot.api.filter.BasicFilter;
import com.phasmoghostbot.telegrambot.api.filter.exceptions.GhostNameNotDefinedException;
import com.phasmoghostbot.telegrambot.models.Ghost;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class FilterByName implements BasicFilter {

    private String Name;

    @Override
    public List<Ghost> filter(List<Ghost> ghosts) {
        if (Name == null || Name.trim().equals(""))
            throw new GhostNameNotDefinedException("Name of the ghost isn't defined");

        List<Ghost> filteredGhosts = new ArrayList<Ghost>();
        for (Ghost ghost : ghosts) {
            if (ghost.getName().toLowerCase().equals(Name.toLowerCase()))
                filteredGhosts.add(ghost);
        }

        return filteredGhosts;
    }
}
