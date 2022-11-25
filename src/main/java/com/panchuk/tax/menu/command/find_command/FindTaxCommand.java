package com.panchuk.tax.menu.command.find_command;

import com.panchuk.tax.DAOException;
import com.panchuk.tax.constant.ProjectConstant;
import com.panchuk.tax.dao.DAOFactory;
import com.panchuk.tax.menu.MenuItem;
import com.panchuk.tax.util.LoggerController;
import com.panchuk.tax.util.PrettyConsolePrinting;
import com.panchuk.tax.util.Reader;
import org.apache.log4j.Logger;

public class FindTaxCommand implements MenuItem {
    static final Logger logger = Logger.getLogger(FindTaxCommand.class);
    private static DAOFactory daoFactory;

    static {
        try {
            DAOFactory.setDAOFactoryFQN(ProjectConstant.DAO_FACTORY_FQN);
            daoFactory = DAOFactory.getInstance();
        } catch (Exception e) {
            LoggerController.daoInstantiatingException(e, FindTaxCommand.class);
        }
    }

    private final String PARAM;

    public FindTaxCommand(String param) {
        PARAM = param;
    }

    @Override
    public void execute() {

        logger.info("Execute \"Find Tax\" Command");

        try {

            String query = PARAM;

            switch (PARAM) {
                case ProjectConstant.FIND_TAX_BY_ID_PAYMENT -> {
                    query += Reader.inputInt("Input id payment to find: ");
                    PrettyConsolePrinting.printTaxList(daoFactory.getTaxDAO().findTaxByFilter(query), false);
                }
                case ProjectConstant.FIND_BY_TYPE -> {
                    query += Reader.inputTaxType();
                    PrettyConsolePrinting.printTaxList(daoFactory.getTaxDAO().findTaxByFilter(query), true);
                }
                case ProjectConstant.FIND_BY_RANGE -> {
                    query += Reader.inputDouble("Input set start of range: ");
                    query += "-" + Reader.inputDouble("Input set end of range: ");
                    PrettyConsolePrinting.printTaxList(daoFactory.getTaxDAO().findTaxByFilter(query), true);
                }
            }

        } catch (DAOException e) {
            LoggerController.daoException(e, FindTaxCommand.class);
        }

        logger.info("End execution \"Find Tax\" Command");
    }

    @Override
    public String toString() {

        return switch (PARAM) {
            case ProjectConstant.FIND_TAX_BY_ID_PAYMENT -> "1. Find by ID Payment";
            case ProjectConstant.FIND_BY_TYPE -> "2. Find by type";
            case ProjectConstant.FIND_BY_RANGE -> "3. Find by range";
            default -> "error";
        };

    }
}
