package com.panchuk.tax.dao.mysql;

import com.panchuk.tax.dao.DAOFactory;
import com.panchuk.tax.dao.TaxDAO;

public class MysqlDAOFactory extends DAOFactory {

    private TaxDAO taxDAO;

    @Override
    public TaxDAO getTaxDAO() {
        if (taxDAO == null) {
            taxDAO = new MysqlTaxDAO();
        }
        return taxDAO;
    }
}
