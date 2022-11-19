package com.panchuk.tax.dao.xml;

import com.panchuk.tax.DAOException;
import com.panchuk.tax.constant.ProjectConstant;
import com.panchuk.tax.constant.XMLConstant;
import com.panchuk.tax.dao.TaxDAO;
import com.panchuk.tax.dao.xml.util.XMLWriter;
import com.panchuk.tax.dao.xml.util.sax_parser.SAXParser;
import com.panchuk.tax.model.TaxType;
import com.panchuk.tax.model.User;
import com.panchuk.tax.service.pretty_print.PrettyConsolePrinting;

import java.util.ArrayList;
import java.util.List;

public class XMLTaxDAO implements TaxDAO {

    @Override
    public boolean insertUser(User user) throws DAOException {
        List<User> list = findUserByFilter(XMLConstant.GET_ALL_USERS);
        list.add(user);

        XMLWriter.buildXML(list, "xml/user_01.xml");

        return false;
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
    public boolean updateUser(User user) throws DAOException {
        return false;
    }

    @Override
    public boolean deleteUser(User user) throws DAOException {
        return false;
    }

    @Override
    public boolean deleteUserTax(int idNumberTax) throws DAOException {
        return false;
    }

    @Override
    public List<TaxType> findTaxByFilter(String query) throws DAOException {
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
}
