package business.enterprise;

import business.product.ProductDirectory;
import business.workrequest.WorkRequestDirectory;
/**
 *
 * @author Yiru Chen
 */
public class SupplierEnterprise extends Enterprise {
    private final String supplierId;
    private final String country;
    private final ProductDirectory productDirectory;
    private final WorkRequestDirectory workRequestDirectory;

    public SupplierEnterprise(String name, String supplierId, String country) {
        super(name);
        this.supplierId = supplierId;
        this.country = country;
        this.productDirectory = new ProductDirectory();
        this.workRequestDirectory = new WorkRequestDirectory();
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

    public WorkRequestDirectory getWorkRequestDirectory() {
        return workRequestDirectory;
    }
}
