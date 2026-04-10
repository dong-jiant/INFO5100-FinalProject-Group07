package business;

import business.enterprise.PlatformEnterprise;
import business.enterprise.SupplierEnterprise;
import business.network.Network;
import business.user.Person;

public class ConfigureSystem {

    public static Network initialize() {

        Network network = new Network("Global E-Commerce Network");

        // ===== Supplier Enterprise =====
        SupplierEnterprise supplier = new SupplierEnterprise(
                "Global Supplier Hub",
                "SUP-001",
                "China"
        );

        Person p1 = new Person("Admin", "admin@test.com");
        Person p2 = new Person("Platform Manager", "platform@test.com");
        Person p3 = new Person("Customer Service", "service@test.com");
        Person p4 = new Person("Supplier Manager", "supplier@test.com");

        supplier.getUserAccountDirectory().addUserAccount(
                "admin",
                "123",
                "SYSTEM_ADMIN",
                p1
        );

        supplier.getUserAccountDirectory().addUserAccount(
                "platform_mgr",
                "123",
                "PLATFORM_MGR",
                p2
        );

        supplier.getUserAccountDirectory().addUserAccount(
                "customer_service",
                "123",
                "CUSTOMER_SERVICE",
                p3
        );

        supplier.getUserAccountDirectory().addUserAccount(
                "supplier",
                "Supplier@123",
                "SUPPLIER_MANAGER",
                p4
        );

        supplier.getProductDirectory().addProduct(
                "Smartphone X1",
                "Electronics",
                799.99,
                120,
                supplier.getSupplierId()
        );
        supplier.getProductDirectory().addProduct(
                "Wireless Earbuds Pro",
                "Accessories",
                149.99,
                80,
                supplier.getSupplierId()
        );
        supplier.getProductDirectory().addProduct(
                "Portable Charger 20000mAh",
                "Electronics",
                59.90,
                45,
                supplier.getSupplierId()
        );

        FakerDataGenerator.generateSupplierProducts(supplier, 25);

        // ===== Platform Enterprise =====
        PlatformEnterprise platform = new PlatformEnterprise(
                "ShopGlobal Platform",
                "PLT-001",
                "USA"
        );

        platform.getOrderDirectory().addOrder("Alice Chen", "alice@test.com", "USA");
        platform.getOrderDirectory().addOrder("Bob Wang", "bob@test.com", "Canada");
        platform.getOrderDirectory().addOrder("Cathy Liu", "cathy@test.com", "Australia");

        // ===== Add to Network =====
        network.addEnterprise(supplier);
        network.addEnterprise(platform);

        return network;
    }
}