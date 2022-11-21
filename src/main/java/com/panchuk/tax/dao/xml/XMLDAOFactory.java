package com.panchuk.tax.dao.xml;

import com.panchuk.tax.dao.DAOFactory;
import com.panchuk.tax.dao.TaxDAO;

public class XMLDAOFactory extends DAOFactory {

    private TaxDAO taxDAO;

    @Override
    public TaxDAO getTaxDAO() {
        if (taxDAO == null) {
            taxDAO = new XMLTaxDAO();
        }
        return taxDAO;
    }
}
