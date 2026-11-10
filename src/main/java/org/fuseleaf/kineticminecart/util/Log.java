package org.fuseleaf.kineticminecart.util;

import org.fuseleaf.kineticminecart.KineticMinecart;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Log {

    private Log() {}

    private static final Logger LOGGER = LoggerFactory.getLogger(KineticMinecart.MOD_ID);

    public static void info(Object object) {
        LOGGER.info(object.toString());
    }

    public static void debug(Object object) {
        LOGGER.debug(object.toString());
    }

    public static void warn(Object object) {
        LOGGER.warn(object.toString());
    }

    public static void error(Object object) {
        LOGGER.error(object.toString());
    }
}
