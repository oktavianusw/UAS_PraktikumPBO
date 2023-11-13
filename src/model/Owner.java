package model;

public class Owner extends User {
    public Owner(int uid, String username, String password) {
        super(uid, username, password);
    }

    @Override
    public String toString() {
        return "Owner:\n uid = " + getUid() + ", username = " + getUsername() + ", password = " + getPassword();
    }
}