package model;

public class Users {

    String userID, fullName, password;
    int roleID;
    String address, phone, email;
    int activate;

    public Users() {
    }

    public Users(String userID, String fullName, String password, int roleID, String address, String phone, String email, int activate) {
        this.userID = userID;
        this.fullName = fullName;
        this.password = password;
        this.roleID = roleID;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.activate = activate;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRoleID() {
        return roleID;
    }

    public void setRoleID(int roleID) {
        this.roleID = roleID;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getActivate() {
        return activate;
    }

    public void setActivate(int activate) {
        this.activate = activate;
    }

    @Override
    public String toString() {
        return "Users{" + "userID=" + userID + ", fullName=" + fullName + ", password=" + password + ", roleID=" + roleID + ", address=" + address + ", phone=" + phone + ", email=" + email + ", activate=" + activate + '}';
    }

}
