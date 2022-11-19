package com.panchuk.tax.menu.command.user_command;

import com.panchuk.tax.DAOException;
import com.panchuk.tax.constant.ProjectConstant;
import com.panchuk.tax.dao.DAOFactory;
import com.panchuk.tax.menu.MenuItem;
import com.panchuk.tax.model.TaxType;
import com.panchuk.tax.model.User;
import com.panchuk.tax.service.pretty_print.PrettyConsolePrinting;
import com.panchuk.tax.util.Reader;

import java.util.List;

public class AddTaxCommand implements MenuItem {

    private final User user;

    private static final DAOFactory daoFactory;

    static {
        try {
            DAOFactory.setDAOFactoryFQN(ProjectConstant.DAO_FACTORY_FQN);
            daoFactory = DAOFactory.getInstance();
        } catch (Exception e) {
            throw new RuntimeException("Error is instantiating DAOFactory.", e);
        }
    }

    public AddTaxCommand(User user) {
        this.user = user;
    }

    @Override
    public void execute() {

        List<TaxType> taxes = Reader.inputTax();

        if (taxes == null || taxes.size() == 0) {
            System.out.println("You do not add any tax or something went wrong!");
            return;
        }

        try {
            if (daoFactory.getTaxDAO().addTaxForUser(user, taxes)) {
                if (taxes.size() > 1) {
                    System.out.println("\n" + ProjectConstant.EMO_GREEN_CHECKBOX + " Taxes were added.\n");
                } else {
                    System.out.println("\n" + ProjectConstant.EMO_GREEN_CHECKBOX + " Tax was added.\n");
                }
            } else {
                System.out.println(ProjectConstant.TEXT_WENT_WRONG);
            }

            PrettyConsolePrinting.printUser(daoFactory.getTaxDAO().getUser(user.getId()));

        } catch (DAOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public String toString() {
        return "\u2747 Add Tax";
    }
}
