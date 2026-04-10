package business.workrequest;

public class ProcurementRequest extends WorkRequest {
    private final String productId;
    private final String productName;
    private final int quantity;

    public ProcurementRequest(
            String sourceEnterprise,
            String targetEnterprise,
            String sourceOrganization,
            String targetOrganization,
            String message,
            String productId,
            String productName,
            int quantity
    ) {
        super("ProcurementRequest", sourceEnterprise, targetEnterprise, sourceOrganization, targetOrganization, message);
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
    }

    @Override
    public String getBusinessPayload() {
        return productName + " (" + productId + "), qty=" + quantity;
    }
}
