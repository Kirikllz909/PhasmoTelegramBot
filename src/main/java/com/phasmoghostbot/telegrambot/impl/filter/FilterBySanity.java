package com.phasmoghostbot.telegrambot.impl.filter;

import java.util.ArrayList;
import java.util.List;

import com.phasmoghostbot.telegrambot.api.filter.BasicFilter;
import com.phasmoghostbot.telegrambot.api.filter.exceptions.SanityValueIsNegativeException;
import com.phasmoghostbot.telegrambot.models.Ghost;
import com.phasmoghostbot.telegrambot.models.HuntStartingCondition;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class FilterBySanity implements BasicFilter {

    private Integer CurrentSanity;

    @Override
    public List<Ghost> filter(List<Ghost> ghosts) {

        if (CurrentSanity < 0)
            throw new SanityValueIsNegativeException("Current sanity is negative");

        List<Ghost> filteredGhosts = new ArrayList<Ghost>();

        for (Ghost ghost : ghosts) {
            if (isGhostCanHunt(ghost))
                filteredGhosts.add(ghost);
        }

        return filteredGhosts;
    }

    // ==========================Implementations========================

    /*
     * Checks if the ghost can hunt (current sanity should be lower than the
     * possible hunt starting sanity)
     * 
     * @param ghost current ghost
     */
    private boolean isGhostCanHunt(Ghost ghost) {
        List<HuntStartingCondition> allPossibleHuntStartingSanity = ghost.getHuntStartingSanity();

        for (int i = 0; i < allPossibleHuntStartingSanity.size(); i++) {
            if (allPossibleHuntStartingSanity.get(i).getValue() >= CurrentSanity)
                return true;
        }

        return false;
    }
}
