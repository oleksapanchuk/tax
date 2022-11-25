package com.panchuk.tax.menu.command.user_command;

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

public class DeleteTaxCommand implements MenuItem {

    private final User user;

    static final Logger logger = Logger.getLogger(Main.class);

    private static final DAOFactory daoFactory;

    static {
        try {
            DAOFactory.setDAOFactoryFQN(ProjectConstant.DAO_FACTORY_FQN);
            daoFactory = DAOFactory.getInstance();
        } catch (Exception e) {
            throw new RuntimeException("Error is instantiating DAOFactory.", e);
        }
    }

    public DeleteTaxCommand(User user) {
        this.user = user;
    }

    @Override
    public void execute() {

        logger.info("deleting tax command");

        try {

            PrettyConsolePrinting.printString("DELETE TAX");

            if (daoFactory.getTaxDAO().deleteUserTax(Reader.inputIdPayment())) {
                System.out.println(ProjectConstant.EMO_GREEN_CHECKBOX + " Tax was deleted successfully!\n");
            }

            PrettyConsolePrinting.printUser(daoFactory.getTaxDAO().getUser(user.getId()));

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
        return "\uD83D\uDCDB Delete Tax";
    }
}
