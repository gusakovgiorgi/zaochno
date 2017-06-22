package ru.zaochno.zaochno.rest.register;

import ru.zaochno.zaochno.model.user.UserType;
import ru.zaochno.zaochno.model.user.UserUtils;

/**
 * Created by Anton_MoApps on 22.06.2017.
 */

public class RegisterSendData {

    private String type;
    private String email;
    private String password;
    private String name;
    private String region;
    private String phone;

    public RegisterSendData() {
    }

    public RegisterSendData(UserType type, String email, String password, String name, String region, String phone) {
        this.type = UserUtils.getUserTypeString(type);
        this.email = email;
        this.password = password;
        this.name = name;
        this.region = region;
        this.phone = phone;
    }

    public String getType() {
        return type;
    }

    public void setType(UserType type) {
        this.type = UserUtils.getUserTypeString(type);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    //        "type": "Физ. лицо",
//                "email" : "email@gmail.com",
//                "password" : "qwerty1234" ,
//                "name" : "Имя",
//                "region" : "region",
//                "phone" : "9586469458"
}
