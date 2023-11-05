package model;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.util.Date;

/**
 *
 * @author gregopublic class DetailTransaction extends Transaction{

 */

public class DetailTransaction extends Transaction{    
    private String transactionAmount;
    private String productPrice;
    private int quantity;
    
    public DetailTransaction(String transactionType, Date transactionDate, int prodID, int transactionID,
        String transactionAmount, String productPrice, int quantity) {
        super(transactionType, transactionDate, prodID, transactionID);
        this.transactionAmount = transactionAmount;
        this.productPrice = productPrice;
        this.quantity = quantity;
    }

    public String getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(String transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
}
