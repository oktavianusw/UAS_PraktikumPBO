package model;

import java.util.Date;

public class Transaction {
    private String transactionType;
    private Date transactionDate;
    private int prodID;
    private int transactionID;

    public Transaction(String transactionType, Date transactionDate, int prodID, int transactionID) {
        this.transactionType = transactionType;
        this.transactionDate = transactionDate;
        this.prodID = prodID;
        this.transactionID = transactionID;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public int getProdID() {
        return prodID;
    }

    public void setProdID(int prodID) {
        this.prodID = prodID;
    }

    public int getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(int transactionID) {
        this.transactionID = transactionID;
    }

}
