package ru.zaochno.zaochno.model.user;

/**
 * Created by Anton_MoApps on 08.06.2017.
 */

public abstract class User {
    protected UserType userType;
    protected String userName;
    protected String userEmail;
    protected String userPassword;
    protected String userRegion;
    protected String userPhone;
    protected String userToken;

    public User(UserType userType, String userName, String userEmail, String userPassword, String userRegion, String userPhone,String userToken) {
        this.userType = userType;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.userRegion = userRegion;
        this.userPhone = userPhone;
        this.userToken=userToken;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserRegion() {
        return userRegion;
    }

    public void setUserRegion(String userRegion) {
        this.userRegion = userRegion;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserToken() {
        return userToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }
}

