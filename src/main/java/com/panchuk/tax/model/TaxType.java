package com.panchuk.tax.model;

public abstract class TaxType {
    protected int idNumber;
    protected int TYPE;
    protected String nameTax;
    protected double multiplier;
    protected double value;
    protected double amountOfTax;
    protected String datePayment;

    protected TaxType(int idNumber, String nameTax, double multiplier) {
        this.idNumber = idNumber;
        this.nameTax = nameTax;
        this.multiplier = multiplier;
    }

    public void calculateAmountTax() {
        amountOfTax = multiplier * value;
    }


    public int getIdNumber() { return idNumber; }

    public void setIdNumber(int idNumber) { this.idNumber = idNumber; }

    public int getType() { return TYPE; }

    public void setNameTax(String nameTax) { this.nameTax = nameTax; }

    public String getNameTax() {
        return nameTax;
    }

    public double getMultiplier() {
        return multiplier;
    }

    public double getValue() { return value; }

    public void setValue(double value) { this.value = value; }

    public double getAmountOfTax() { return amountOfTax; }

    public void setAmountOfTax(double amountOfTax) { this.amountOfTax = amountOfTax; }

    public String getDatePayment() { return datePayment; }

    public void setDatePayment(String datePayment) { this.datePayment = datePayment; }

}
