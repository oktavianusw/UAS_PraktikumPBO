package model;

import java.util.ArrayList;

public class StaffOnline extends User {
    private ArrayList<String> adminType = new ArrayList<>();

    public StaffOnline(int uid, String username, String password, ArrayList<String> adminType) {
        super(uid, username, password);
        this.adminType = adminType;
    }

    public ArrayList<String> getAdminType() {
        return adminType;
    }

    public void setAdminType(ArrayList<String> adminType) {
        this.adminType = adminType;
    }
}