package business;

import java.util.Date;

import business.enterprise.LogisticsEnterprise;
import business.enterprise.PlatformEnterprise;
import business.enterprise.SupplierEnterprise;
import business.network.Network;
import business.organization.Organization;
import business.shipment.Shipment;
import business.shipment.ShipmentDirectory;
import business.user.Person;
import business.workrequest.ProcurementRequest;
import business.workrequest.RestockRequest;
import business.workrequest.FulfillmentAssignment;
import business.workrequest.DeliveryAssignment;
import business.workrequest.ReturnRequest;

public class ConfigureSystem {

    public static Network initialize() {

        Network network = new Network("Global E-Commerce Network");

        // ===== Supplier Enterprise =====
        SupplierEnterprise supplier = new SupplierEnterprise(
                "Global Supplier Hub",
                "SUP-001",
                "China"
        );

        // ===== Platform Enterprise =====
        PlatformEnterprise platform = new PlatformEnterprise(
                "ShopGlobal Platform",
                "PLT-001",
                "USA"
        );

        Person p1 = new Person("Admin", "admin@test.com");

        // ===== Organizations (6 total, 2 per enterprise) =====
        supplier.addOrganization(new Organization("Product Management Org"));
        supplier.addOrganization(new Organization("Order Processing Org"));

        platform.addOrganization(new Organization("Order Management Org"));
        platform.addOrganization(new Organization("Customer Service Org"));

        // (Logistics orgs added after logistics enterprise is created below)

        Person p2 = new Person("Platform Manager", "platform@test.com");
        Person p3 = new Person("Customer Service", "service@test.com");
        Person p4 = new Person("Supplier Manager", "supplier@test.com");

        // ===== User Accounts =====

        // Admin -> supplier
        supplier.getUserAccountDirectory().addUserAccount(
                "admin",
                "123",
                "SYSTEM_ADMIN",
                p1
        );

        // Platform Manager -> platform
        platform.getUserAccountDirectory().addUserAccount(
                "platform_mgr",
                "123",
                "PLATFORM_MGR",
                p2
        );

        // Customer Service -> platform
        platform.getUserAccountDirectory().addUserAccount(
                "customer_service",
                "123",
                "CUSTOMER_SERVICE",
                p3
        );

        // Supplier Manager -> supplier
        supplier.getUserAccountDirectory().addUserAccount(
                "supplier",
                "Supplier@123",
                "SUPPLIER_MANAGER",
                p4
        );

        // Supplier Staff -> supplier
        Person p5 = new Person("Supplier Staff", "staff@test.com");
        supplier.getUserAccountDirectory().addUserAccount(
                "supplier_staff",
                "123",
                "SUPPLIER_STAFF",
                p5
        );

        // Data Analyst -> platform
        Person p6 = new Person("Data Analyst", "analyst@test.com");
        platform.getUserAccountDirectory().addUserAccount(
                "analyst",
                "123",
                "DATA_ANALYST",
                p6
        );

        // Customer -> platform
        Person p7 = new Person("Customer User", "customer@test.com");
        platform.getUserAccountDirectory().addUserAccount(
                "customer",
                "123",
                "CUSTOMER",
                p7
        );

        // ===== Supplier Products =====
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

        // ===== Platform Orders=====
        FakerDataGenerator.generatePlatformOrders(platform, 10);

        // ===== Add to Network =====
        network.addEnterprise(supplier);
        network.addEnterprise(platform);

        // ===== Cross-enterprise Work Requests to Supplier =====
        supplier.getWorkRequestDirectory().addRequest(
                new ProcurementRequest(
                        platform.getName(),
                        supplier.getName(),
                        "Platform Procurement Organization",
                        "Supplier Sales Organization",
                        "Urgent procurement for seasonal campaign",
                        "PRD-1001",
                        "Smartphone X1",
                        60
                )
        );

        supplier.getWorkRequestDirectory().addRequest(
                new RestockRequest(
                        platform.getName(),
                        supplier.getName(),
                        "Platform Customer Service Organization",
                        "Supplier Warehouse Organization",
                        "Restock due to rising complaints about stock-outs",
                        "PRD-1002",
                        120,
                        "Low stock risk"
                )
        );

        // ===== Cross-organization Work Request: Fulfillment Assignment (within Supplier) =====
        supplier.getWorkRequestDirectory().addRequest(
                new FulfillmentAssignment(
                        supplier.getName(),
                        supplier.getName(),
                        "Product Management Org",
                        "Order Processing Org",
                        "Assign fulfillment of order ORD-1001 to warehouse staff",
                        "ORD-1001",
                        "supplier_staff"
                )
        );

        // ===== Cross-enterprise Work Request: Return Request (Platform -> Supplier) =====
        supplier.getWorkRequestDirectory().addRequest(
                new ReturnRequest(
                        platform.getName(),
                        supplier.getName(),
                        "Customer Service Org",
                        "Order Processing Org",
                        "Customer requested return for defective product",
                        "ORD-1003",
                        "Product arrived damaged"
                )
        );

     // ===== Logistics Enterprise =====
        LogisticsEnterprise logisticsEnt = new LogisticsEnterprise("SwiftShip Logistics", "LOG-001", "USA");
        logisticsEnt.addOrganization(new Organization("Shipment Org"));
        logisticsEnt.addOrganization(new Organization("Delivery Org"));
        network.addEnterprise(logisticsEnt);

        // Logistics users
        Person pLogCoord = new Person("Logistics Coordinator", "logistics1@test.com");
        Person pDeliver1 = new Person("Delivery Staff 1", "delivery1@test.com");
        Person pDeliver2 = new Person("Delivery Staff 2", "delivery2@test.com");
        logisticsEnt.getUserAccountDirectory().addUserAccount("logistics1", "123", "LOGISTICS_COORDINATOR", pLogCoord);
        logisticsEnt.getUserAccountDirectory().addUserAccount("delivery1", "123", "DELIVERY_STAFF", pDeliver1);
        logisticsEnt.getUserAccountDirectory().addUserAccount("delivery2", "123", "DELIVERY_STAFF", pDeliver2);

        // ===== Cross-organization Work Request: Delivery Assignment (within Logistics) =====
        logisticsEnt.getWorkRequestDirectory().addRequest(
                new DeliveryAssignment(
                        logisticsEnt.getName(),
                        logisticsEnt.getName(),
                        "Shipment Org",
                        "Delivery Org",
                        "Assign delivery of SHP-1005 to delivery staff",
                        "SHP-1005",
                        "delivery1"
                )
        );

        // Pre-populate shipment test data (10 records, evenly distributed statuses)
        ShipmentDirectory shipDir = logisticsEnt.getShipmentDirectory();

        // 2 Created
        Shipment s1 = shipDir.addShipment();
        s1.setOrderId("ORD-1001"); s1.setOrigin("Shanghai"); s1.setDestination("New York");
        s1.setCarrier("FedEx"); s1.setTrackingNumber("FX-100001"); s1.setEta("2026-04-20");
        s1.setStatus("Created"); s1.setCreatedDate(new Date());

        Shipment s2 = shipDir.addShipment();
        s2.setOrderId("ORD-1002"); s2.setOrigin("Beijing"); s2.setDestination("Los Angeles");
        s2.setCarrier("UPS"); s2.setTrackingNumber("UP-200002"); s2.setEta("2026-04-22");
        s2.setStatus("Created"); s2.setCreatedDate(new Date());

        // 2 Processing
        Shipment s3 = shipDir.addShipment();
        s3.setOrderId("ORD-1003"); s3.setOrigin("Tokyo"); s3.setDestination("Chicago");
        s3.setCarrier("DHL"); s3.setTrackingNumber("DH-300003"); s3.setEta("2026-04-18");
        s3.setStatus("Processing"); s3.setCreatedDate(new Date());

        Shipment s4 = shipDir.addShipment();
        s4.setOrderId("ORD-1004"); s4.setOrigin("Seoul"); s4.setDestination("Boston");
        s4.setCarrier("FedEx"); s4.setTrackingNumber("FX-400004"); s4.setEta("2026-04-19");
        s4.setStatus("Processing"); s4.setCreatedDate(new Date());

        // 3 Shipped（其中2条分配给 delivery1, 1条分配给 delivery2）
        Shipment s5 = shipDir.addShipment();
        s5.setOrderId("ORD-1005"); s5.setOrigin("Guangzhou"); s5.setDestination("San Francisco");
        s5.setCarrier("UPS"); s5.setTrackingNumber("UP-500005"); s5.setEta("2026-04-15");
        s5.setAssignedDeliveryStaff("delivery1"); s5.setStatus("Shipped"); s5.setCreatedDate(new Date());

        Shipment s6 = shipDir.addShipment();
        s6.setOrderId("ORD-1006"); s6.setOrigin("Shenzhen"); s6.setDestination("Seattle");
        s6.setCarrier("DHL"); s6.setTrackingNumber("DH-600006"); s6.setEta("2026-04-16");
        s6.setAssignedDeliveryStaff("delivery1"); s6.setStatus("Shipped"); s6.setCreatedDate(new Date());

        Shipment s7 = shipDir.addShipment();
        s7.setOrderId("ORD-1007"); s7.setOrigin("Mumbai"); s7.setDestination("Houston");
        s7.setCarrier("FedEx"); s7.setTrackingNumber("FX-700007"); s7.setEta("2026-04-17");
        s7.setAssignedDeliveryStaff("delivery2"); s7.setStatus("Shipped"); s7.setCreatedDate(new Date());

        // 2 Delivered
        Shipment s8 = shipDir.addShipment();
        s8.setOrderId("ORD-1008"); s8.setOrigin("Bangkok"); s8.setDestination("Miami");
        s8.setCarrier("UPS"); s8.setTrackingNumber("UP-800008"); s8.setEta("2026-04-05");
        s8.setAssignedDeliveryStaff("delivery1"); s8.setStatus("Delivered");
        s8.setCreatedDate(new Date()); s8.setDeliveredDate(new Date());

        Shipment s9 = shipDir.addShipment();
        s9.setOrderId("ORD-1009"); s9.setOrigin("Taipei"); s9.setDestination("Dallas");
        s9.setCarrier("DHL"); s9.setTrackingNumber("DH-900009"); s9.setEta("2026-04-06");
        s9.setAssignedDeliveryStaff("delivery2"); s9.setStatus("Delivered");
        s9.setCreatedDate(new Date()); s9.setDeliveredDate(new Date());

        // 1 Returned
        Shipment s10 = shipDir.addShipment();
        s10.setOrderId("ORD-1010"); s10.setOrigin("Singapore"); s10.setDestination("Phoenix");
        s10.setCarrier("FedEx"); s10.setTrackingNumber("FX-101010"); s10.setEta("2026-04-08");
        s10.setAssignedDeliveryStaff("delivery1"); s10.setStatus("Returned");
        s10.setNotes("Delivery failed: recipient not home"); s10.setCreatedDate(new Date());
        

        return network;
    }
}