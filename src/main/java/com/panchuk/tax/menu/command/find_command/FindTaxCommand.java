package com.panchuk.tax.menu.command.find_command;

import com.panchuk.tax.DAOException;
import com.panchuk.tax.constant.ProjectConstant;
import com.panchuk.tax.dao.DAOFactory;
import com.panchuk.tax.menu.MenuItem;
import com.panchuk.tax.util.PrettyConsolePrinting;
import com.panchuk.tax.util.Reader;

public class FindTaxCommand implements MenuItem {

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

    public FindTaxCommand(String param) {
        PARAM = param;
    }

    @Override
    public void execute() {
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
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString() {

        return switch (PARAM) {
            case ProjectConstant.FIND_TAX_BY_ID_PAYMENT -> "1. Find by ID Payment";
            case ProjectConstant.FIND_BY_TYPE -> "2. Find by type"; //todo
            case ProjectConstant.FIND_BY_RANGE -> "3. Find by range";
            default -> "error";
        };

    }
}
