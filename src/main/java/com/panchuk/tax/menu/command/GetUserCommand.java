package com.panchuk.tax.menu.command;

import com.panchuk.tax.DAOException;
import com.panchuk.tax.constant.ProjectConstant;
import com.panchuk.tax.dao.DAOFactory;
import com.panchuk.tax.menu.MenuItem;
import com.panchuk.tax.menu.UserMenu;
import com.panchuk.tax.model.User;
import com.panchuk.tax.util.PrettyConsolePrinting;
import com.panchuk.tax.util.Reader;

import static com.panchuk.tax.util.Reader.inputMenuCommand;

public class GetUserCommand implements MenuItem {
    private static final DAOFactory daoFactory;

    static {
        try {
            DAOFactory.setDAOFactoryFQN(ProjectConstant.DAO_FACTORY_FQN);
            daoFactory = DAOFactory.getInstance();
        } catch (Exception e) {
            throw new RuntimeException("Error is instantiating DAOFactory.", e);
        }
    }
    @Override
    public void execute() {

        int idUser = Reader.inputIdUser("Input user id: ", ProjectConstant.VALID_ID_NUMBER);
        User user;

        try {

            user = daoFactory.getTaxDAO().getUser(idUser);

            PrettyConsolePrinting.printUser(user);

        } catch (DAOException e) {
            throw new RuntimeException(e);
        }

        UserMenu userMenu = new UserMenu(user);
        while (true) {
            userMenu.execute(inputMenuCommand(userMenu.toString()));
        }
    }

    @Override
    public String toString() {
        return "\uD83E\uDDB9 Get User";
    }
}
