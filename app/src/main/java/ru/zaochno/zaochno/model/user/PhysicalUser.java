package ru.zaochno.zaochno.model.user;

/**
 * Created by Anton_MoApps on 08.06.2017.
 */

public class PhysicalUser extends User {
    private int userId;
    private static final UserType userType=UserType.PHYSICAL_USER;

    public PhysicalUser(String userName, String userEmail, String userPassword, String userRegion, String userPhone, int userId, String userToken) {
        super(userType, userName, userEmail, userPassword, userRegion, userPhone,userToken);
        this.userId = userId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

}
