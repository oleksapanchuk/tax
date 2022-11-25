package com.panchuk.tax.util;

import org.apache.log4j.Logger;

import java.io.IOException;

public class LoggerController {

    public static <T> void daoException(Exception e, Class<T> clazz) {
        Logger logger = Logger.getLogger(clazz);
        try {
            EmailSender.sendMessage(e.toString());
        } catch (Exception ex) {
            logger.error(ex);
        }
        logger.error(e);
        System.out.println("\u26D4 DAO exception. Check logger or mail.");
    }

    public static <T> void daoInstantiatingException(Exception e, Class<T> clazz) {
        Logger logger = Logger.getLogger(clazz);
        try {
            EmailSender.sendMessage("Error is instantiating DAOFactory!" + e);
        } catch (Exception ex) {
            logger.fatal(ex);
        }
        logger.error(e);
        System.out.println("\u26D4 Error is instantiating DAOFactory!");
        System.exit(-1);
    }

    public static void inputException(IOException e) {
        Logger logger = Logger.getLogger(Reader.class);
        logger.error(e);
        System.out.println("\u26D4 IOException");
    }

}
