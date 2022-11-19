package com.panchuk.tax.menu.command.find_command;

import com.panchuk.tax.DAOException;
import com.panchuk.tax.constant.ProjectConstant;
import com.panchuk.tax.dao.DAOFactory;
import com.panchuk.tax.menu.MenuItem;
import com.panchuk.tax.service.pretty_print.PrettyConsolePrinting;
import com.panchuk.tax.util.Reader;

public class FindUserCommand implements MenuItem {

    private static final DAOFactory daoFactory;

    static {
        try {
            DAOFactory.setDAOFactoryFQN(ProjectConstant.DAO_FACTORY_FQN);
            daoFactory = DAOFactory.getInstance();
        } catch (Exception e) {
            throw new RuntimeException("Error is instantiating DAOFactory.", e);
        }
    }

    private final String PARAM;

    public FindUserCommand(String param) {
        PARAM = param;
    }

    @Override
    public void execute() {
        try {

            String query = PARAM + switch (PARAM) {
                case ProjectConstant.FIND_BY_FIRST_NAME ->
                        Reader.inputString("Input first name to find: ", ProjectConstant.VALID_FIRST_NAME); //todo use like for finding
                case ProjectConstant.FIND_BY_LAST_NAME ->
                        Reader.inputString("Input last name to find: ", ProjectConstant.VALID_LAST_NAME);
                case ProjectConstant.FIND_BY_EMAIL ->
                        Reader.inputString("Input email name to find: ", ProjectConstant.VALID_EMAIL);
                case ProjectConstant.SORT_BY_FIRST_NAME ->
                        Reader.inputIsTrue("Do you want to sort first name by ascending order (\"yes\" or \"no\"): ") //todo
                                ?
                                "a"
                                :
                                "d";
                case ProjectConstant.SORT_BY_LAST_NAME ->
                        Reader.inputIsTrue("Do you want to sort last name by ascending order (\"yes\" or \"no\"): ") //todo
                                ?
                                "a"
                                :
                                "d";
                case ProjectConstant.SORT_BY_AMOUNT ->
                        Reader.inputIsTrue("Do you want to sort amount of tax by ascending order (\"yes\" or \"no\"): ") //todo
                                ?
                                "a"
                                :
                                "d";
                default -> "error";
            };


            PrettyConsolePrinting.printUserList(daoFactory.getTaxDAO().findUserByFilter(query));

        } catch (DAOException e) {
            throw new RuntimeException(e);
        }
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
