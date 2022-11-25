package com.panchuk.tax.util;

import com.panchuk.tax.DAOException;
import com.panchuk.tax.Main;
import com.panchuk.tax.constant.ProjectConstant;
import com.panchuk.tax.dao.DAOFactory;
import com.panchuk.tax.model.TaxType;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {

    private static final DAOFactory daoFactory;

    static final Logger logger = Logger.getLogger(Main.class);

    static {
        try {
            DAOFactory.setDAOFactoryFQN(ProjectConstant.DAO_FACTORY_FQN);
            daoFactory = DAOFactory.getInstance();
        } catch (Exception e) {
            try {
                EmailSender.sendMessage(e.toString());
            } catch (Exception ex) {
                logger.error(ex);
            }
            logger.error(e);
            System.out.println("\u26D4 Error is instantiating DAOFactory.!");
            throw new RuntimeException(e);
        }
    }

    public static boolean validationIdNumber(Integer inputData, Pattern pattern) {
        Matcher matcher = pattern.matcher(String.valueOf(inputData));
        if (!matcher.matches()) {
            System.out.println("\u26D4 It is not id number!!!");
            return false;
        }

        if (inputData <= 0) {
            System.out.println("\u26D4 User id must be positive!!!");
        }

        try {
            if (daoFactory.getTaxDAO().findUserByFilter(ProjectConstant.FIND_BY_ID + inputData).size() == 1) return true;
        } catch (DAOException e) {
            try {
                EmailSender.sendMessage(e.toString());
            } catch (Exception ex) {
                logger.error(ex);
            }
            logger.error(e);
            System.out.println("\u26D4 Failed!");
        }
        return false;
    }

    public static boolean validationIdNumberForTax(int idPayment) {
        try {
            if (idPayment <- 0)  return false;
            List<TaxType> taxList = daoFactory.getTaxDAO().findTaxByFilter(ProjectConstant.FIND_TAX_BY_ID_PAYMENT + idPayment);
            if (taxList == null || taxList.size() == 0) return true;
        } catch (DAOException e) {
            try {
                EmailSender.sendMessage(e.toString());
            } catch (Exception ex) {
                logger.error(ex);
            }
            logger.error(e);
            System.out.println("\u26D4 Failed!");
        }
        return false;
    }

    public static boolean validationDouble(String inputData) {
        return ProjectConstant.VALID_DOUBLE.matcher(String.valueOf(inputData)).matches();
    }

    public static boolean validationString(String inputData, Pattern pattern) {
        return pattern.matcher(inputData).matches();
    }

}
