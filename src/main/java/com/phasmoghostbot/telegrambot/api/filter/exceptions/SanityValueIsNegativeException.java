package com.phasmoghostbot.telegrambot.api.filter.exceptions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SanityValueIsNegativeException extends RuntimeException {

    Logger logger = LogManager.getLogger(SanityValueIsNegativeException.class);

    public SanityValueIsNegativeException(String message) {
        super(message);
        logger.error(message);
    }
}
