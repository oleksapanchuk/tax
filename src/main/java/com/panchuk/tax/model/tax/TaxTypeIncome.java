package com.panchuk.tax.model.tax;

import com.panchuk.tax.constant.ProjectConstant;
import com.panchuk.tax.model.TaxType;

public class TaxTypeIncome extends TaxType {
    private static final int TYPE = 1;

    public TaxTypeIncome() {
        super(0, "Податок на дохід з основного та додаткового місць роботи", ProjectConstant.TAX_INCOME);
    }

    public int getType() { return TYPE; }

}
