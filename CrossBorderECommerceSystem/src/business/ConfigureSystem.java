package business;

import business.enterprise.Enterprise;
import business.user.Person;
import business.user.UserAccount;

public class ConfigureSystem {

    public static Enterprise initialize() {

        Enterprise enterprise = new Enterprise("E-Commerce Platform");

        Person p1 = new Person("Admin", "admin@test.com");
        Person p2 = new Person("Platform Manager", "platform@test.com");
        Person p3 = new Person("Customer Service", "service@test.com");

        UserAccount admin = enterprise.getUserAccountDirectory().addUserAccount(
                "admin",
                "123",
                "System Admin",
                p1
        );

        UserAccount platform = enterprise.getUserAccountDirectory().addUserAccount(
                "platform_mgr",
                "123",
                "Platform Manager",
                p2
        );

        UserAccount customerService = enterprise.getUserAccountDirectory().addUserAccount(
                "customer_service",
                "123",
                "Customer Service",
                p3
        );

        return enterprise;
    }
}