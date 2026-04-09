package business.product;

import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Yiru Chen
 */
public class ProductDirectory {
    private final ArrayList<Product> productList;
    private int nextId;

    public ProductDirectory() {
        this.productList = new ArrayList<>();
        this.nextId = 1001;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public Product addProduct(String name, String category, double price, int stockQty, String supplierId) {
        Product product = new Product(generateProductId(), name, category, price, stockQty, supplierId);
        productList.add(product);
        return product;
    }

    public void removeProduct(Product product) {
        productList.remove(product);
    }

    public Product findById(String productId) {
        for (Product product : productList) {
            if (product.getProductId().equals(productId)) {
                return product;
            }
        }
        return null;
    }

    private String generateProductId() {
        return "PRD-" + nextId++;
    }
}
