package com.panchuk.tax.menu.command.user_command;

import com.panchuk.tax.DAOException;
import com.panchuk.tax.Main;
import com.panchuk.tax.constant.ProjectConstant;
import com.panchuk.tax.dao.DAOFactory;
import com.panchuk.tax.menu.MenuItem;
import com.panchuk.tax.model.User;
import com.panchuk.tax.util.EmailSender;
import com.panchuk.tax.util.LoggerController;
import com.panchuk.tax.util.PrettyConsolePrinting;
import com.panchuk.tax.util.Reader;
import org.apache.log4j.Logger;

import static com.panchuk.tax.constant.ProjectConstant.EMO_GREEN_CHECKBOX;

public class UpdateUserCommand implements MenuItem {
    static final Logger logger = Logger.getLogger(UpdateUserCommand.class);
    private final User user;
    private static DAOFactory daoFactory;

    static {
        try {
            DAOFactory.setDAOFactoryFQN(ProjectConstant.DAO_FACTORY_FQN);
            daoFactory = DAOFactory.getInstance();
        } catch (Exception e) {
            LoggerController.daoInstantiatingException(e, UpdateUserCommand.class);
        }
    }

    public UpdateUserCommand(User user) {
        this.user = user;
    }

    @Override
    public void execute() {

        logger.info("Execute \"Update User\" Command");

        PrettyConsolePrinting.printString("UPDATE USER");

        if (Reader.inputIsTrue("Do you want to change first name (\"yes\" or \"no\"): ")) {
            user.setFirstName(Reader.inputString("Input new first name: ", ProjectConstant.VALID_FIRST_NAME));
            logger.info("New First Name Added");
        }

        if (Reader.inputIsTrue("Do you want to change last name (\"yes\" or \"no\"): ")) {
            user.setLastName(Reader.inputString("Input new last name: ", ProjectConstant.VALID_LAST_NAME));
            logger.info("New Last Name Added");
        }

        if (Reader.inputIsTrue("Do you want to change email (\"yes\" or \"no\"): ")) {
            user.setEmail(Reader.inputString("Input new email: ", ProjectConstant.VALID_EMAIL));
            logger.info("New Email Added");
        }

        try {

           if (daoFactory.getTaxDAO().updateUser(user)) {
               logger.info("User was updated successfully");
               System.out.println(EMO_GREEN_CHECKBOX + " User was updated successfully!\n");
           }

            PrettyConsolePrinting.printUser(daoFactory.getTaxDAO().getUser(user.getId()));

        } catch (DAOException e) {
            LoggerController.daoException(e, UpdateUserCommand.class);
        }

        logger.info("End execution \"Update User\" Command");
    }

    @Override
    public String toString() {
        return ProjectConstant.EMO_UPDATE_USER + " Update User";
    }
}
