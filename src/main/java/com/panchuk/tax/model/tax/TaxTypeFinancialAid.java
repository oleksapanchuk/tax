package com.panchuk.tax.model.tax;

import com.panchuk.tax.constant.ProjectConstant;
import com.panchuk.tax.model.TaxType;

public class TaxTypeFinancialAid extends TaxType {

    private static final int TYPE = 7;

    public TaxTypeFinancialAid() {
        super(0, "Податок на матеріальну допомогу", ProjectConstant.TAX_FINANCIAL_AID);
    }

    public int getType() { return TYPE; }
}
