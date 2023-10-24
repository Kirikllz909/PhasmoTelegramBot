package com.phasmoghostbot.telegrambot.impl.dataLoader;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.phasmoghostbot.telegrambot.api.dataLoader.GenerateXMLFilePath;
import com.phasmoghostbot.telegrambot.models.Ghost;

public class GhostLoadTest {

    private final Integer GHOST_COUNT = 24;

    @Test
    void testLoad() {
        List<Ghost> loadedGhosts = new GhostLoader().load(GenerateXMLFilePath.getFileLocation());

        Assertions.assertEquals(GHOST_COUNT, loadedGhosts.size());
    }
}
