package business;

import business.enterprise.PlatformEnterprise;
import business.enterprise.SupplierEnterprise;
import business.order.Order;
import business.order.OrderItem;
import business.product.Product;
import com.github.javafaker.Faker;
import java.util.Locale;
import java.util.Random;

public class FakerDataGenerator {
    private static final String[] CATEGORIES = {
        "Electronics", "Accessories", "Home", "Fashion", "Sports"
    };

    private FakerDataGenerator() {
    }

    public static void generateSupplierProducts(SupplierEnterprise supplierEnterprise, int count) {
        Faker faker = new Faker(new Locale("en-US"));
        Random random = new Random();

        for (int i = 0; i < count; i++) {
            String productName = faker.commerce().productName();
            String category = CATEGORIES[random.nextInt(CATEGORIES.length)];
            double price = 10 + (1500 - 10) * random.nextDouble();
            int stock = 5 + random.nextInt(200);

            supplierEnterprise.getProductDirectory().addProduct(
                productName,
                category,
                round2(price),
                stock,
                supplierEnterprise.getSupplierId()
            );
        }
    }
public static void generatePlatformOrders(PlatformEnterprise platformEnterprise, int count) {
    Faker faker = new Faker(new Locale("en-US"));
    Random random = new Random();

    String[] countries = {"USA", "Canada", "Australia", "UK", "Germany"};
    String[] statuses = {"Created", "Shipped", "Delivered"};

    Product[] products = {
        new Product("iPhone", 1000),
        new Product("Laptop", 2000),
        new Product("Headphones", 300),
        new Product("Tablet", 600),
        new Product("Smart Watch", 400)
    };

    for (int i = 0; i < count; i++) {
        String customerName = faker.name().fullName();
        String customerEmail = "customer" + (i + 1) + "@test.com";
        String country = countries[random.nextInt(countries.length)];

        Order order = platformEnterprise.getOrderDirectory().addOrder(
                customerName,
                customerEmail,
                country
        );

        order.setStatus(statuses[random.nextInt(statuses.length)]);

        int itemCount = 1 + random.nextInt(3);

        for (int j = 0; j < itemCount; j++) {
            Product product = products[random.nextInt(products.length)];
            int quantity = 1 + random.nextInt(3);
            double salesPrice = round2(product.getPrice() * (0.8 + random.nextDouble() * 0.2));

            OrderItem item = new OrderItem(product, quantity, salesPrice);
            order.addItem(item);
        }
    }
}
    private static double round2(double value) {
        return Math.round(value * 100.0) / 100.0;
    }
}
