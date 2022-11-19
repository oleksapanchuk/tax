package com.panchuk.tax.dao.xml.util.sax_parser;

import com.panchuk.tax.constant.XMLConstant;
import com.panchuk.tax.model.TaxType;
import com.panchuk.tax.model.User;
import com.panchuk.tax.model.tax.*;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class SAXParserHandler extends DefaultHandler {

    private final List<User> userList = new ArrayList<>();
    private User user = new User();
    private List<TaxType> taxList = new ArrayList<>();
    private TaxType tax = null;


    private String currentTagName;


    public List<User> getUserList() { return userList; }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {

        currentTagName = qName;

        switch (qName) {
            case XMLConstant.TAG_USER -> user = new User();
            case XMLConstant.TAG_TAXES -> taxList = new ArrayList<>();
            case XMLConstant.TAG_TAX -> {
                String idTax = attributes.getValue("idTax");

                switch (idTax) {
                    case "1" -> tax = new TaxTypeIncome();
                    case "2" -> tax = new TaxTypePresent();
                    case "3" -> tax = new TaxTypeRemuneration();
                    case "4" -> tax = new TaxTypeSale();
                    case "5" -> tax = new TaxTypeTransferFromAbroad();
                    case "6" -> tax = new TaxTypeBenefitsForChildren();
                    case "7" -> tax = new TaxTypeFinancialAid();
                }
            }
        }

    }

    @Override
    public void endElement(String uri, String localName, String qName) {

        currentTagName = null;

        switch (qName) {
            case XMLConstant.TAG_USER -> userList.add(user);
            case XMLConstant.TAG_TAXES -> user.setTax(taxList);
            case XMLConstant.TAG_TAX -> {
                tax.setValue(tax.getAmountOfTax() * tax.getMultiplier());
                taxList.add(tax);
            }
        }

    }

    @Override
    public void characters(char[] ch, int start, int length) {

        if (currentTagName == null) return;

        switch (currentTagName) {
            case XMLConstant.TAG_ID_USER -> user.setId(Integer.parseInt(new String(ch, start, length)));
            case XMLConstant.TAG_FIRST_NAME -> user.setFirstName(new String(ch, start, length));
            case XMLConstant.TAG_LAST_NAME -> user.setLastName(new String(ch, start, length));
            case XMLConstant.TAG_EMAIL -> user.setEmail(new String(ch, start, length));
            case XMLConstant.TAG_TAX -> tax.setAmountOfTax(Double.parseDouble(new String(ch, start, length)));
        }

    }
}
