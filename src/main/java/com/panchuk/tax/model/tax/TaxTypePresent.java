package com.panchuk.tax.model.tax;

import com.panchuk.tax.constant.ProjectConstant;
import com.panchuk.tax.model.TaxType;

public class TaxTypePresent extends TaxType {
    private static final int TYPE = 2;

    public TaxTypePresent() {
        super(0, "Податок на подарунки", ProjectConstant.TAX_PRESENT);
    }

    public int getType() { return TYPE; }
}
