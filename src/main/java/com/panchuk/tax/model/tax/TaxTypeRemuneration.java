package com.panchuk.tax.model.tax;

import com.panchuk.tax.constant.ProjectConstant;
import com.panchuk.tax.model.TaxType;

public class TaxTypeRemuneration extends TaxType {

    private static final int TYPE = 3;

    public TaxTypeRemuneration() {
        super(0, "Податок на авторські винагороди", ProjectConstant.TAX_REMUNERATION);
    }

    public int getType() { return TYPE; }
}
