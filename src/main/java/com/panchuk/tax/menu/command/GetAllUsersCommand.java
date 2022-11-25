package com.panchuk.tax.menu.command;

import com.panchuk.tax.DAOException;
import com.panchuk.tax.Main;
import com.panchuk.tax.constant.ProjectConstant;
import com.panchuk.tax.dao.DAOFactory;
import com.panchuk.tax.menu.MenuItem;
import com.panchuk.tax.util.EmailSender;
import com.panchuk.tax.util.PrettyConsolePrinting;
import org.apache.log4j.Logger;

public class GetAllUsersCommand implements MenuItem {

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
        try {
            PrettyConsolePrinting.printUserList(daoFactory.getTaxDAO().findUserByFilter(ProjectConstant.FIND_ALL_USERS));
        } catch (DAOException e) {
            try {
                EmailSender.sendMessage(e.toString());
            } catch (Exception ex) {
                logger.error(ex);
            }
            logger.error(e);
            System.out.println("\u26D4 DAOException occurred! Details: " + e);
        }
    }

    @Override
    public String toString() {
        return "\uD83D\uDCD5 Show Users";
    }
}
