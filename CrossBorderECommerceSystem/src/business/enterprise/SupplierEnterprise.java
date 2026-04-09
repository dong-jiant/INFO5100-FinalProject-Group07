package business.enterprise;

import business.product.ProductDirectory;
/**
 *
 * @author Yiru Chen
 */
public class SupplierEnterprise extends Enterprise {
    private final String supplierId;
    private final String country;
    private final ProductDirectory productDirectory;

    public SupplierEnterprise(String name, String supplierId, String country) {
        super(name);
        this.supplierId = supplierId;
        this.country = country;
        this.productDirectory = new ProductDirectory();
    }

    public String getSupplierId() {
        return supplierId;
    }

    public String getCountry() {
        return country;
    }

    public ProductDirectory getProductDirectory() {
        return productDirectory;
    }
}
