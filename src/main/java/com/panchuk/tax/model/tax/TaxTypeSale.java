package com.panchuk.tax.model.tax;

import com.panchuk.tax.constant.ProjectConstant;
import com.panchuk.tax.model.TaxType;

public class TaxTypeSale extends TaxType {
    private static final int TYPE = 4;

    public TaxTypeSale() {
        super(0, "Податок на продаж майна", ProjectConstant.TAX_SALE);
    }

    public int getType() { return TYPE; }
}
