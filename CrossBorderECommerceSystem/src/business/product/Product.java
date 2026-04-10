/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package business.product;

/**
 *
 * @author stelladong
 */
public class Product {
    private String productId;
    private String name;
    private String category;
    private double price;
    private int stockQty;
    private String supplierId;
    private static final int LOW_STOCK_THRESHOLD = 10;

    public Product(String productId, String name, String category, double price, int stockQty, String supplierId) {
        if (productId == null || productId.isEmpty()) throw new IllegalArgumentException("Product ID cannot be empty");
        if (name == null || name.trim().isEmpty()) throw new IllegalArgumentException("Product name cannot be empty");
        if (price < 0) throw new IllegalArgumentException("Price cannot be negative");
        if (stockQty < 0) throw new IllegalArgumentException("Stock quantity cannot be negative");
        this.productId = productId;
        this.name = name.trim();
        this.category = category == null ? "General" : category;
        this.price = price;
        this.stockQty = stockQty;
        this.supplierId = supplierId;
    }

    public Product(String name, double price) {
        this("P-UNKNOWN", name, "General", price, 0, "SUP-UNKNOWN");
    }

    public void updatePrice(double newPrice) {
        if (newPrice < 0) throw new IllegalArgumentException("Price cannot be negative");
        this.price = newPrice;
    }

    public void updateStock(int delta) {
        if (this.stockQty + delta < 0) throw new IllegalArgumentException("Insufficient stock");
        this.stockQty += delta;
    }

    public boolean isAvailable() { return stockQty > 0; }
    public boolean isLowStock() { return stockQty <= LOW_STOCK_THRESHOLD; }

    public String getProductId() { return productId; }
    public String getName() { return name; }
    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) throw new IllegalArgumentException("Name cannot be empty");
        this.name = name.trim();
    }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public double getPrice() { return price; }
    public void setPrice(double price) {
        if (price < 0) throw new IllegalArgumentException("Price cannot be negative");
        this.price = price;
    }
    public int getStockQty() { return stockQty; }
    public void setStockQty(int qty) {
        if (qty < 0) throw new IllegalArgumentException("Stock cannot be negative");
        this.stockQty = qty;
    }
    public String getSupplierId() { return supplierId; }
    public void setSupplierId(String supplierId) { this.supplierId = supplierId; }

    @Override
    public String toString() { return String.format("[%s] %s - $%.2f (Stock: %d)", productId, name, price, stockQty); }
}
