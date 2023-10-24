package com.phasmoghostbot.telegrambot.api.dataLoader;

import java.io.File;

public class GenerateXMLFilePath {
    private static final String FILE_LOCATION = new File("src\\main\\resources\\data\\PhasmoGhosts.xml")
            .getAbsolutePath();

    public static String getFileLocation() {
        return FILE_LOCATION;
    }
}
