package com.panchuk.tax.menu.command.user_command;

import com.panchuk.tax.DAOException;
import com.panchuk.tax.constant.ProjectConstant;
import com.panchuk.tax.dao.DAOFactory;
import com.panchuk.tax.menu.MenuItem;
import com.panchuk.tax.model.User;
import com.panchuk.tax.util.LoggerController;
import com.panchuk.tax.util.PrettyConsolePrinting;
import com.panchuk.tax.util.Reader;
import org.apache.log4j.Logger;

public class DeleteTaxCommand implements MenuItem {

    static final Logger logger = Logger.getLogger(DeleteTaxCommand.class);

    private final User user;

    private static DAOFactory daoFactory;

    static {
        try {
            DAOFactory.setDAOFactoryFQN(ProjectConstant.DAO_FACTORY_FQN);
            daoFactory = DAOFactory.getInstance();
        } catch (Exception e) {
            LoggerController.daoInstantiatingException(e, DeleteTaxCommand.class);
        }
    }

    public DeleteTaxCommand(User user) {
        this.user = user;
    }

    @Override
    public void execute() {

        logger.info("Execute \"Delete Tax\" Command");

        try {

            PrettyConsolePrinting.printString("DELETE TAX");

            if (daoFactory.getTaxDAO().deleteUserTax(Reader.inputIdPayment())) {
                System.out.println(ProjectConstant.EMO_GREEN_CHECKBOX + " Tax was deleted successfully!\n");
            }

            PrettyConsolePrinting.printUser(daoFactory.getTaxDAO().getUser(user.getId()));

        } catch (DAOException e) {
            LoggerController.daoException(e, DeleteTaxCommand.class);
        }
    }

    @Override
    public String toString() {
        return "\uD83D\uDCDB Delete Tax";
    }
}
