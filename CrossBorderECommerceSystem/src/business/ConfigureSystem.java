/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package business;

import business.enterprise.Enterprise;
import business.user.UserAccount;
import java.util.ArrayList;

/**
 *
 * @author stelladong
 */
public class ConfigureSystem {
       public static Enterprise initialize() {

       
        Enterprise enterprise = new Enterprise("E-Commerce Platform");

       
        ArrayList<UserAccount> userList = new ArrayList<>();

        UserAccount admin = new UserAccount("admin", "123", "SYSTEM_ADMIN");
        UserAccount platform = new UserAccount("platform", "123", "PLATFORM_MGR");

        userList.add(admin);
        userList.add(platform);

    
        return enterprise;
    }
    }
