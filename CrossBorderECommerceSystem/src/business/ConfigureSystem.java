package business;

import business.enterprise.Enterprise;
import business.enterprise.SupplierEnterprise;
import business.user.Person;
import business.user.UserAccount;

public class ConfigureSystem {

    public static Enterprise initialize() {

        SupplierEnterprise enterprise = new SupplierEnterprise(
                "Global Supplier Hub",
                "SUP-001",
                "China"
        );

        Person p1 = new Person("Admin", "admin@test.com");
        Person p2 = new Person("Platform Manager", "platform@test.com");
        Person p3 = new Person("Customer Service", "service@test.com");
        Person p4 = new Person("Supplier Manager", "supplier@test.com");

        UserAccount admin = enterprise.getUserAccountDirectory().addUserAccount(
                "admin",
                "123",
                "SYSTEM_ADMIN",
                p1
        );

        UserAccount platform = enterprise.getUserAccountDirectory().addUserAccount(
                "platform_mgr",
                "123",
                "PLATFORM_MGR",
                p2
        );

        UserAccount customerService = enterprise.getUserAccountDirectory().addUserAccount(
                "customer_service",
                "123",
                "CUSTOMER_SERVICE",
                p3
        );

        UserAccount supplierManager = enterprise.getUserAccountDirectory().addUserAccount(
                "supplier",
                "Supplier@123",
                "SUPPLIER_MANAGER",
                p4
        );

        enterprise.getProductDirectory().addProduct("Smartphone X1", "Electronics", 799.99, 120, enterprise.getSupplierId());
        enterprise.getProductDirectory().addProduct("Wireless Earbuds Pro", "Accessories", 149.99, 80, enterprise.getSupplierId());
        enterprise.getProductDirectory().addProduct("Portable Charger 20000mAh", "Electronics", 59.90, 45, enterprise.getSupplierId());
        FakerDataGenerator.generateSupplierProducts(enterprise, 25);

        return enterprise;
    }
}