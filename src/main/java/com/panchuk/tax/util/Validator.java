package com.panchuk.tax.util;

import com.panchuk.tax.DAOException;
import com.panchuk.tax.constant.ProjectConstant;
import com.panchuk.tax.dao.DAOFactory;
import com.panchuk.tax.model.TaxType;

import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {

    private static DAOFactory daoFactory;

    static {
        try {
            DAOFactory.setDAOFactoryFQN(ProjectConstant.DAO_FACTORY_FQN);
            daoFactory = DAOFactory.getInstance();
        } catch (Exception e) {
            throw new RuntimeException("Error is instantiating DAOFactory.", e);
        }
    }

    private static final Scanner scanner;

    static {
        scanner = new Scanner(System.in);
    }

    public static boolean validationIdNumber(Integer inputData, Pattern pattern) {
        Matcher matcher = pattern.matcher(String.valueOf(inputData));
        if (!matcher.matches()) {
            System.out.println("It is not id number!!!");
            return false;
        }

        try {
            if (daoFactory.getTaxDAO().findUserByFilter(ProjectConstant.FIND_BY_ID + inputData).size() == 1) return true;
        } catch (DAOException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public static boolean validationDouble(String inputData) {
        return ProjectConstant.VALID_DOUBLE.matcher(String.valueOf(inputData)).matches();
    }

    public static boolean validationString(String inputData, Pattern pattern) {
        return pattern.matcher(inputData).matches();
    }

    public static boolean validationIdNumberForTax(int idPayment) {
        try {
            List<TaxType> taxList = daoFactory.getTaxDAO().findTaxByFilter(ProjectConstant.FIND_TAX_BY_ID_PAYMENT + idPayment);
            if (taxList.size() == 0) return true;
        } catch (DAOException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

}
