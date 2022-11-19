package com.panchuk.tax.model.tax;

import com.panchuk.tax.constant.ProjectConstant;
import com.panchuk.tax.model.TaxType;

public class TaxTypeTransferFromAbroad extends TaxType {
    private static final int TYPE = 5;

    public TaxTypeTransferFromAbroad() {
        super(0, "Податок на перекази з-за кордону", ProjectConstant.TAX_TRANSFER_FROM_ABROAD);
    }

    public int getType() { return TYPE; }

}
