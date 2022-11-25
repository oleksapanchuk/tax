package com.panchuk.tax.menu.command.user_command;

import com.panchuk.tax.DAOException;
import com.panchuk.tax.constant.ProjectConstant;
import com.panchuk.tax.dao.DAOFactory;
import com.panchuk.tax.menu.MenuItem;
import com.panchuk.tax.model.User;
import com.panchuk.tax.util.LoggerController;
import org.apache.log4j.Logger;

public class DeleteUserCommand implements MenuItem {

    private final User user;

    static final Logger logger = Logger.getLogger(DeleteUserCommand.class);

    private static DAOFactory daoFactory;

    static {
        try {
            DAOFactory.setDAOFactoryFQN(ProjectConstant.DAO_FACTORY_FQN);
            daoFactory = DAOFactory.getInstance();
        } catch (Exception e) {
            LoggerController.daoInstantiatingException(e, DeleteUserCommand.class);
        }
    }

    public DeleteUserCommand(User user) { this.user = user; }

    @Override
    public void execute() {

        logger.info("Execute \"Delete User\" Command");

        try {
            if (daoFactory.getTaxDAO().deleteUser(user)) {
                logger.info("User was deleted successfully");
                System.out.println(ProjectConstant.EMO_GREEN_CHECKBOX + " User was deleted successfully!\n");
            }
        } catch (DAOException e) {
            LoggerController.daoException(e, DeleteUserCommand.class);
        }

        logger.info("End execution \"Delete User\" Command");

        new ExitToMainMenuCommand().execute();
    }

    @Override
    public String toString() {
        return "\u2B55 Delete User";
    }
}
