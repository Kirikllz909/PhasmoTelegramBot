package com.phasmoghostbot.telegrambot.impl.dataLoader;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.phasmoghostbot.telegrambot.api.dataLoader.GenerateXMLFilePath;
import com.phasmoghostbot.telegrambot.models.Evidence;

public class EvidenceLoadTest {

    private final Integer EVIDENCE_COUNT = 7;

    @Test
    void testLoad() {
        List<Evidence> evidenceList = new EvidenceLoader()
                .load(GenerateXMLFilePath.getFileLocation());

        Assertions.assertEquals(EVIDENCE_COUNT, evidenceList.size());
    }
}
