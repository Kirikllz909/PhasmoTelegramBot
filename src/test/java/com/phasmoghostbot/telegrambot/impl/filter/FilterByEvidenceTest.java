package com.phasmoghostbot.telegrambot.impl.filter;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.phasmoghostbot.telegrambot.api.filter.BasicFilter;
import com.phasmoghostbot.telegrambot.constants.Constants;
import com.phasmoghostbot.telegrambot.models.Evidence;
import com.phasmoghostbot.telegrambot.models.Ghost;

public class FilterByEvidenceTest {

    private final Integer EXPECTED_EMF_GHOST_COUNT = 10;
    private final Integer EXPECTED_EMF_ORB_GHOST_COUNT = 2;
    private final Integer EXPECTED_EMF_ORB_SPIRIT_BOX_GHOST_COUNT = 0;
    private final Integer EXPECTED_NONE_EVIDENCE_GHOST_COUNT = 24;
    private final Integer EXPECTED_ORB_GHOST_COUNT = 11;

    @Test
    void testFilterEMF() {
        List<Evidence> expectedEvidences = new ArrayList<>();
        expectedEvidences.add(Constants.EVIDENCE_LIST.get(0));
        BasicFilter filter = new FilterByEvidence(expectedEvidences);

        List<Ghost> filteredGhosts = filter.filter(Constants.GHOST_LIST);

        Assertions.assertEquals(EXPECTED_EMF_GHOST_COUNT, filteredGhosts.size());
    }

    @Test
    void testFilterEMF_Orb() {
        List<Evidence> expectedEvidences = new ArrayList<>();
        expectedEvidences.add(Constants.EVIDENCE_LIST.get(0));
        expectedEvidences.add(Constants.EVIDENCE_LIST.get(1));
        BasicFilter filter = new FilterByEvidence(expectedEvidences);

        List<Ghost> filteredGhosts = filter.filter(Constants.GHOST_LIST);

        Assertions.assertEquals(EXPECTED_EMF_ORB_GHOST_COUNT, filteredGhosts.size());
    }

    @Test
    void testFilterOrb() {
        List<Evidence> expectedEvidences = new ArrayList<>();
        expectedEvidences.add(Constants.EVIDENCE_LIST.get(1));
        BasicFilter filter = new FilterByEvidence(expectedEvidences);

        List<Ghost> filteredGhosts = filter.filter(Constants.GHOST_LIST);

        Assertions.assertEquals(EXPECTED_ORB_GHOST_COUNT, filteredGhosts.size());
    }

    @Test
    void testFilterEMF_Orb_SpiritBox() {
        List<Evidence> expectedEvidences = new ArrayList<>();
        expectedEvidences.add(Constants.EVIDENCE_LIST.get(0));
        expectedEvidences.add(Constants.EVIDENCE_LIST.get(1));
        expectedEvidences.add(Constants.EVIDENCE_LIST.get(2));
        BasicFilter filter = new FilterByEvidence(expectedEvidences);

        List<Ghost> filteredGhosts = filter.filter(Constants.GHOST_LIST);

        Assertions.assertEquals(EXPECTED_EMF_ORB_SPIRIT_BOX_GHOST_COUNT, filteredGhosts.size());
    }

    @Test
    void testFilterNoneEvidence() {
        List<Evidence> expectedEvidences = new ArrayList<>();
        BasicFilter filter = new FilterByEvidence(expectedEvidences);

        List<Ghost> filteredGhosts = filter.filter(Constants.GHOST_LIST);

        Assertions.assertEquals(EXPECTED_NONE_EVIDENCE_GHOST_COUNT, filteredGhosts.size());
    }

    @Test
    void testFilterNullEvidence() {
        BasicFilter filter = new FilterByEvidence(null);

        List<Ghost> filteredGhosts = filter.filter(Constants.GHOST_LIST);

        Assertions.assertEquals(EXPECTED_NONE_EVIDENCE_GHOST_COUNT, filteredGhosts.size());
    }
}
