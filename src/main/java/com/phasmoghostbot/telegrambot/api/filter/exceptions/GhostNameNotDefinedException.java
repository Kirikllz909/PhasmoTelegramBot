package com.phasmoghostbot.telegrambot.api.filter.exceptions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GhostNameNotDefinedException extends RuntimeException {

    private final Logger logger = LogManager.getLogger(GhostNameNotDefinedException.class);

    public GhostNameNotDefinedException(String message) {
        super(message);
        logger.error(message);
    }

}
