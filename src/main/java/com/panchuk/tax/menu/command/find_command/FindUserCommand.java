package com.panchuk.tax.menu.command.find_command;

import com.panchuk.tax.DAOException;
import com.panchuk.tax.constant.ProjectConstant;
import com.panchuk.tax.dao.DAOFactory;
import com.panchuk.tax.menu.MenuItem;
import com.panchuk.tax.util.LoggerController;
import com.panchuk.tax.util.PrettyConsolePrinting;
import com.panchuk.tax.util.Reader;
import org.apache.log4j.Logger;

public class FindUserCommand implements MenuItem {
    static final Logger logger = Logger.getLogger(FindUserCommand.class);
    private static DAOFactory daoFactory;

    static {
        try {
            DAOFactory.setDAOFactoryFQN(ProjectConstant.DAO_FACTORY_FQN);
            daoFactory = DAOFactory.getInstance();
        } catch (Exception e) {
            LoggerController.daoInstantiatingException(e, FindUserCommand.class);
        }
    }

    private final String PARAM;

    public FindUserCommand(String param) {
        PARAM = param;
    }

    @Override
    public void execute() {

        logger.info("Execute \"Find User\" Command");

        try {

            String query = PARAM + switch (PARAM) {
                case ProjectConstant.FIND_BY_FIRST_NAME ->
                        Reader.inputString("Input first name to find: ", ProjectConstant.VALID_FIRST_NAME);
                case ProjectConstant.FIND_BY_LAST_NAME ->
                        Reader.inputString("Input last name to find: ", ProjectConstant.VALID_LAST_NAME);
                case ProjectConstant.FIND_BY_EMAIL ->
                        Reader.inputString("Input email name to find: ", ProjectConstant.VALID_EMAIL);
                case ProjectConstant.SORT_BY_FIRST_NAME ->
                        Reader.inputIsTrue("Do you want to sort first name by ascending order (\"yes\" or \"no\"): ")
                                ?
                                "a"
                                :
                                "d";
                case ProjectConstant.SORT_BY_LAST_NAME ->
                        Reader.inputIsTrue("Do you want to sort last name by ascending order (\"yes\" or \"no\"): ")
                                ?
                                "a"
                                :
                                "d";
                case ProjectConstant.SORT_BY_AMOUNT ->
                        Reader.inputIsTrue("Do you want to sort amount of tax by ascending order (\"yes\" or \"no\"): ")
                                ?
                                "a"
                                :
                                "d";
                default -> "error";
            };


            PrettyConsolePrinting.printUserList(daoFactory.getTaxDAO().findUserByFilter(query));

        } catch (DAOException e) {
            LoggerController.daoException(e, FindUserCommand.class);
        }

        logger.info("End execution \"Find User\" Command");
    }

    @Override
    public String toString() {

        return switch (PARAM) {
            case ProjectConstant.FIND_BY_FIRST_NAME -> "1. Find by First Name";
            case ProjectConstant.FIND_BY_LAST_NAME -> "2. Find by Last Name";
            case ProjectConstant.FIND_BY_EMAIL -> "3. Find by Email";
            case ProjectConstant.SORT_BY_FIRST_NAME -> "4. Sort by First Name";
            case ProjectConstant.SORT_BY_LAST_NAME -> "5. Sort by Last Name";
            case ProjectConstant.SORT_BY_AMOUNT -> "6. Sort by Amount";
            default -> "error";

        };
    }
}
