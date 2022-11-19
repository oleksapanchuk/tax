package com.panchuk.tax.menu.command.user_command;

import com.panchuk.tax.DAOException;
import com.panchuk.tax.constant.ProjectConstant;
import com.panchuk.tax.dao.DAOFactory;
import com.panchuk.tax.menu.MenuItem;
import com.panchuk.tax.model.User;

public class DeleteUserCommand implements MenuItem {

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

    public DeleteUserCommand(User user) { this.user = user; }

    @Override
    public void execute() {

        try {
            if (daoFactory.getTaxDAO().deleteUser(user)) {
                System.out.println(ProjectConstant.EMO_GREEN_CHECKBOX + " User was deleted successfully!\n");
            }
        } catch (DAOException e) {
            throw new RuntimeException(e);
        }

        new ExitToMainMenuCommand().execute();
    }

    @Override
    public String toString() {
        return "\u2B55 Delete User";
    }
}
