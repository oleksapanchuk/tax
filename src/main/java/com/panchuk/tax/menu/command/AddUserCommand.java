package com.panchuk.tax.menu.command;

import com.panchuk.tax.DAOException;
import com.panchuk.tax.constant.ProjectConstant;
import com.panchuk.tax.dao.DAOFactory;
import com.panchuk.tax.menu.MenuItem;
import com.panchuk.tax.model.User;
import com.panchuk.tax.util.LoggerController;
import com.panchuk.tax.util.PrettyConsolePrinting;
import com.panchuk.tax.util.Reader;
import org.apache.log4j.Logger;


public class AddUserCommand implements MenuItem {

    static final Logger logger = Logger.getLogger(AddUserCommand.class);

    private static DAOFactory daoFactory;

    static {
        try {
            DAOFactory.setDAOFactoryFQN(ProjectConstant.DAO_FACTORY_FQN);
            daoFactory = DAOFactory.getInstance();
        } catch (Exception e) {
            LoggerController.daoInstantiatingException(e, AddUserCommand.class);
        }
    }

    @Override
    public void execute() {

        logger.info("Execute \"Create User\" Command");

        PrettyConsolePrinting.printString("CREATION USER");

        User user = new User();

        user.setFirstName(Reader.inputString("Input user first name: ", ProjectConstant.VALID_FIRST_NAME));
        user.setLastName(Reader.inputString("Input user last name: ", ProjectConstant.VALID_LAST_NAME));
        user.setSex(Reader.inputSex("Input user sex: "));
        user.setEmail(Reader.inputString("Input user email: ", ProjectConstant.VALID_EMAIL));
        user.setDateOfBirth(Reader.inputString("Input user date of birth: ", ProjectConstant.VALID_DATE));

        try {
            if (daoFactory.getTaxDAO().insertUser(user)) {
                logger.info("User was added successfully!");
                System.out.println(ProjectConstant.EMO_GREEN_CHECKBOX + " User was added successfully!\n");
            }

        } catch (DAOException e) {
            LoggerController.daoException(e, AddUserCommand.class);
        }

        try {

            if (Reader.inputIsTrue("Do you want to add tax (\"yes\" or \"no\"): ")) {
                if (daoFactory.getTaxDAO().addTaxForUser(user, Reader.inputTax())) {
                    logger.info("Taxes were added successfully!");
                    System.out.println(ProjectConstant.EMO_GREEN_CHECKBOX + " Taxes were added successfully!\n");
                }
            }

        } catch (DAOException e) {
            LoggerController.daoException(e, AddUserCommand.class);
        }

        logger.info("End execution \"Create User\" Command");
    }

    @Override
    public String toString() {
        return "\uD83E\uDD35 Add User";
    }
}
