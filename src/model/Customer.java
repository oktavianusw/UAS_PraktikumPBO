package model;

public class Customer extends User {
    private String userType;
    private String userRole;
    private int balance;

    public Customer(int uid, String username, String password, String userType, String userRole, int balance) {
        super(uid, username, password);
        this.userType = userType;
        this.userRole = userRole;
        this.balance = balance;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}
