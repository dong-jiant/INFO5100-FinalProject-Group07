package business;

import business.enterprise.SupplierEnterprise;
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

    private static double round2(double value) {
        return Math.round(value * 100.0) / 100.0;
    }
}
