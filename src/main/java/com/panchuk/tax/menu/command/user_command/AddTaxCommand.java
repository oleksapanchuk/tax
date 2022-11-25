package com.panchuk.tax.menu.command.user_command;

import com.panchuk.tax.DAOException;
import com.panchuk.tax.constant.ProjectConstant;
import com.panchuk.tax.dao.DAOFactory;
import com.panchuk.tax.menu.MenuItem;
import com.panchuk.tax.model.TaxType;
import com.panchuk.tax.model.User;
import com.panchuk.tax.util.EmailSender;
import com.panchuk.tax.util.LoggerController;
import com.panchuk.tax.util.PrettyConsolePrinting;
import com.panchuk.tax.util.Reader;
import org.apache.log4j.Logger;

import java.util.List;

public class AddTaxCommand implements MenuItem {

    private final User user;

    private static DAOFactory daoFactory;
    static final Logger logger = Logger.getLogger(AddTaxCommand.class);


    static {
        try {
            DAOFactory.setDAOFactoryFQN(ProjectConstant.DAO_FACTORY_FQN);
            daoFactory = DAOFactory.getInstance();
        } catch (Exception e) {
            LoggerController.daoInstantiatingException(e, AddTaxCommand.class);
        }
    }

    public AddTaxCommand(User user) {
        this.user = user;
    }

    @Override
    public void execute() {

        logger.info("Execute \"Add Tax\" Command");

        List<TaxType> taxes = Reader.inputTax();


        if (taxes == null || taxes.size() == 0) {
            logger.warn("You do not add any tax or something went wrong!");
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
            LoggerController.daoException(e, AddTaxCommand.class);
            return;
        }
        logger.info("End execution \"Add Tax\" Command");
    }

    @Override
    public String toString() {
        return "\u2747 Add Tax";
    }
}
