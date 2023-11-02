package model;

public class Owner extends User {
    public Owner(int uid, String username, String password) {
        super(uid, username, password);
    }

    @Override
    public String toString() {
        return "Owner: uid = " + getUid() + ", password = " + getPassword();
    }
}