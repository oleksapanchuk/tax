package com.panchuk.tax.menu.command;

import com.panchuk.tax.DAOException;
import com.panchuk.tax.Main;
import com.panchuk.tax.constant.ProjectConstant;
import com.panchuk.tax.dao.DAOFactory;
import com.panchuk.tax.menu.MenuItem;
import com.panchuk.tax.model.User;
import com.panchuk.tax.util.EmailSender;
import com.panchuk.tax.util.PrettyConsolePrinting;
import com.panchuk.tax.util.Reader;
import org.apache.log4j.Logger;


public class AddUserCommand implements MenuItem {

    private static final DAOFactory daoFactory;

    static {
        try {
            DAOFactory.setDAOFactoryFQN(ProjectConstant.DAO_FACTORY_FQN);
            daoFactory = DAOFactory.getInstance();
        } catch (Exception e) {
            throw new RuntimeException("Error is instantiating DAOFactory.", e);
        }
    }

    static final Logger logger = Logger.getLogger(Main.class);


    @Override
    public void execute() {

        logger.info("creation user command");

        PrettyConsolePrinting.printString("CREATION USER");

        User user = new User();

        user.setFirstName(Reader.inputString("Input user first name: ", ProjectConstant.VALID_FIRST_NAME));
        user.setLastName(Reader.inputString("Input user last name: ", ProjectConstant.VALID_LAST_NAME));
        user.setSex(Reader.inputSex("Input user sex: "));
        user.setEmail(Reader.inputString("Input user email: ", ProjectConstant.VALID_EMAIL));
        user.setDateOfBirth(Reader.inputString("Input user date of birth: ", ProjectConstant.VALID_DATE));

        try {
            if (daoFactory.getTaxDAO().insertUser(user)) {
                System.out.println(ProjectConstant.EMO_GREEN_CHECKBOX + " User was added successfully!\n");
            }

            if (Reader.inputIsTrue("Do you want to add tax (\"yes\" or \"no\"): ")) {
                if (daoFactory.getTaxDAO().addTaxForUser(user, Reader.inputTax())) {
                    System.out.println(ProjectConstant.EMO_GREEN_CHECKBOX + " Taxes were added successfully!\n");
                }
            }

        } catch (DAOException e) {
            try {
                EmailSender.sendMessage(e.toString());
            } catch (Exception ex) {
                logger.error(ex);
            }
            logger.error(e);
            System.out.println("\u26D4 Failed!");
        }

    }

    @Override
    public String toString() {
        return "\uD83E\uDD35 Add User";
    }
}
