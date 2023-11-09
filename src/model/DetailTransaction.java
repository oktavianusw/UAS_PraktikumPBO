package model;

import java.util.Date;

public class DetailTransaction extends Transaction {
    private double transactionAmount;
    private double productPrice;
    private int quantity;

    public DetailTransaction(String transactionType, Date transactionDate, int prodID, int transactionID,
            double transactionAmount, double productPrice, int quantity) {
        super(transactionType, transactionDate, prodID, transactionID);
        this.transactionAmount = transactionAmount;
        this.productPrice = productPrice;
        this.quantity = quantity;
    }

    public double getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(double transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
