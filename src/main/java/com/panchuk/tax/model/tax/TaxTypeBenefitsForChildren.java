package com.panchuk.tax.model.tax;

import com.panchuk.tax.constant.ProjectConstant;
import com.panchuk.tax.model.TaxType;

public class TaxTypeBenefitsForChildren extends TaxType {

    private static final int TYPE = 6;

    public TaxTypeBenefitsForChildren() {
        super(0, "Податок пільг на дітей", ProjectConstant.TAX_BENEFITS_FOR_CHILDREN);
    }

    public int getType() { return TYPE; }
}
