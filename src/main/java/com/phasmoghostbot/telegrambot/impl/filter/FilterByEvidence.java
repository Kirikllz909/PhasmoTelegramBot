package com.phasmoghostbot.telegrambot.impl.filter;

import java.util.ArrayList;
import java.util.List;

import com.phasmoghostbot.telegrambot.api.filter.BasicFilter;
import com.phasmoghostbot.telegrambot.models.Evidence;
import com.phasmoghostbot.telegrambot.models.Ghost;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class FilterByEvidence implements BasicFilter {

    private List<Evidence> expectedEvidences;

    @Override
    public List<Ghost> filter(List<Ghost> ghosts) {
        List<Ghost> filteredGhosts = new ArrayList<>();

        for (Ghost ghost : ghosts) {
            if (isGhostContainsEvidences(ghost))
                filteredGhosts.add(ghost);
        }

        return filteredGhosts;
    }

    private boolean isGhostContainsEvidences(Ghost ghost) {
        if (expectedEvidences == null || expectedEvidences.size() == 0)
            return true;

        int evidencesContained = 0;

        List<Evidence> ghostEvidences = ghost.getGhostEvidences();
        for (int i = 0; i < expectedEvidences.size(); i++) {
            if (ghostEvidences.contains(expectedEvidences.get(i)))
                evidencesContained++;
        }

        int expectedEvidencesContained = expectedEvidences.size();
        if (evidencesContained == expectedEvidencesContained)
            return true;
        else
            return false;
    }
}
