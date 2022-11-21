package com.panchuk.tax.dao.xml;

import com.panchuk.tax.DAOException;
import com.panchuk.tax.constant.ProjectConstant;
import com.panchuk.tax.constant.XMLConstant;
import com.panchuk.tax.dao.TaxDAO;
import com.panchuk.tax.dao.xml.util.XMLWriter;
import com.panchuk.tax.dao.xml.util.sax_parser.SAXParser;
import com.panchuk.tax.model.TaxType;
import com.panchuk.tax.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class XMLTaxDAO implements TaxDAO {

    @Override
    public boolean insertUser(User user) throws DAOException {
        List<User> userList = findUserByFilter(XMLConstant.GET_ALL_USERS);
        user.setId(generateUserId(userList));
        userList.add(user);

        XMLWriter.buildXML(userList, "xml/user.xml");

        return true;
    }

    @Override
    public User getUser(int id) throws DAOException {

        List<User> userList = findUserByFilter(XMLConstant.GET_ALL_USERS);

        for (User user : userList) {
            if (user.getId() == id) {
                return user;
            }
        }

        return null;
    }

    @Override
    public List<TaxType> getUserTaxes(int id) throws DAOException {
        return getUser(id).getTax();
    }

    @Override
    public boolean addTaxForUser(User user, List<TaxType> taxes) throws DAOException {
        return false;
    }

    @Override
    public boolean updateUser(User user) throws DAOException  {
        List<User> userList = findUserByFilter(XMLConstant.GET_ALL_USERS);
        userList.remove(user.getId());
        userList.add(user);

        XMLWriter.buildXML(userList, "xml/user.xml");

        return true;
    }

    @Override
    public boolean deleteUser(User user) throws DAOException {
        List<User> userList = findUserByFilter(XMLConstant.GET_ALL_USERS);
        userList.remove(user);

        XMLWriter.buildXML(userList, "xml/user.xml");

        return true;
    }

    @Override
    public boolean deleteUserTax(int idNumberTax) throws DAOException {
        List<User> userList = findUserByFilter(XMLConstant.GET_ALL_USERS);
        for (User u : userList) {
            List<TaxType> userTaxes = u.getTax();
            for (TaxType tt : userTaxes) {
                if (tt.getIdNumber() == idNumberTax) {
                    userTaxes.remove(tt);
                    u.setTax(userTaxes);
                    return true;
                }
            }
        }
        return false; //todo not working
    }

    @Override
    public List<TaxType> findTaxByFilter(String query) throws DAOException {
        List<User> userList = findUserByFilter(XMLConstant.GET_ALL_USERS);
        List<TaxType> taxList = new ArrayList<>();
        for (User u: userList) {
            List<TaxType> userTaxes = u.getTax();
            taxList.addAll(userTaxes);
        }

        if (query.equals(XMLConstant.GET_ALL_TAXES)) return taxList;

        return null;
    }

    @Override
    public List<User> findUserByFilter(String query) throws DAOException {

        SAXParser saxParser = new SAXParser();

        List<User> userList = saxParser.parse();

        if (query.equals(XMLConstant.GET_ALL_USERS)) return userList;

        if (query.startsWith(ProjectConstant.FIND_BY_ID)) {
            int id = Integer.parseInt(query.replace(ProjectConstant.FIND_BY_ID, ""));
            List<User> uList = new ArrayList<>();
            for (User u : userList) {
                if (u.getId() == id) uList.add(u);
            }
            return uList;
        }

        // some filters (stream API) //todo

        return userList;

    }

    private int generateUserId(List<User> userList) {
        Random random = new Random();
        int idUser;
        boolean isOk;
        while (true) {
            idUser = random.nextInt(1, 1_000_000);
            isOk = true;
            for (User u : userList) {
                if (u.getId() == idUser) {
                    isOk = false;
                    break;
                }
            }
            if (isOk) return idUser;
        }
    }
}
