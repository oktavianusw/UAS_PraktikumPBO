package model;

import java.util.ArrayList;

public class Membership {
    private int userID;
    private ArrayList<String> userMembershipType = new ArrayList<>();
    private int membershipPrice;
    private String membershipDesc;

    public Membership(int userID, ArrayList<String> userMembershipType, int membershipPrice, String membershipDesc) {
        this.userID = userID;
        this.userMembershipType = userMembershipType;
        this.membershipPrice = membershipPrice;
        this.membershipDesc = membershipDesc;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public ArrayList<String> getUserMembershipType() {
        return userMembershipType;
    }

    public void setUserMembershipType(ArrayList<String> userMembershipType) {
        this.userMembershipType = userMembershipType;
    }

    public int getMembershipPrice() {
        return membershipPrice;
    }

    public void setMembershipPrice(int membershipPrice) {
        this.membershipPrice = membershipPrice;
    }

    public String getMembershipDesc() {
        return membershipDesc;
    }
}
