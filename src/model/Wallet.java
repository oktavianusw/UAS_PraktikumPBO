package model;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author grego
 */
public class Wallet {
    private int userID;
    private int transcationCount;
    private double walletAmount;

    public Wallet(int userID, int transcationCount, double walletAmount) {
        this.userID = userID;
        this.transcationCount = transcationCount;
        this.walletAmount = walletAmount;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getTranscationCount() {
        return transcationCount;
    }

    public void setTranscationCount(int transcationCount) {
        this.transcationCount = transcationCount;
    }

    public double getWalletAmount() {
        return walletAmount;
    }

    public void setWalletAmount(double walletAmount) {
        this.walletAmount = walletAmount;
    }
    
    
}
