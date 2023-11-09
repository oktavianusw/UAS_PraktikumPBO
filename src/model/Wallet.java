package model;

public class Wallet {
    private int userID;
    private int transactionCount;
    private double walletAmount;

    public Wallet(int userID, int transactionCount, double walletAmount) {
        this.userID = userID;
        this.transactionCount = transactionCount;
        this.walletAmount = walletAmount;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int gettransactionCount() {
        return transactionCount;
    }

    public void settransactionCount(int transactionCount) {
        this.transactionCount = transactionCount;
    }

    public double getWalletAmount() {
        return walletAmount;
    }

    public void setWalletAmount(double walletAmount) {
        this.walletAmount = walletAmount;
    }

}
