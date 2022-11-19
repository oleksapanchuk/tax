package com.panchuk.tax.controller;

import com.panchuk.tax.model.TaxType;
import com.panchuk.tax.model.tax.*;

public class TaxController {

    public static TaxType createTax(int idNumber, int type, double money, String date) {
        TaxType tax = null;
        switch (type) {
            case 1 -> tax = new TaxTypeIncome();
            case 2 -> tax = new TaxTypePresent();
            case 3 -> tax = new TaxTypeRemuneration();
            case 4 -> tax = new TaxTypeSale();
            case 5 -> tax = new TaxTypeTransferFromAbroad();
            case 6 -> tax = new TaxTypeBenefitsForChildren();
            case 7 -> tax = new TaxTypeFinancialAid();
        }
        if (tax == null) return null;

        tax.setIdNumber(idNumber);
        tax.setValue(money);
        tax.calculateAmountTax();
        tax.setDatePayment(date);

        return tax;
    }

}
