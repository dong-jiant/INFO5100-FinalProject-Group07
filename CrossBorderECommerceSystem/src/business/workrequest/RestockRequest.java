package business.workrequest;

public class RestockRequest extends WorkRequest {
    private final String productId;
    private final int restockAmount;
    private final String reason;

    public RestockRequest(
            String sourceEnterprise,
            String targetEnterprise,
            String sourceOrganization,
            String targetOrganization,
            String message,
            String productId,
            int restockAmount,
            String reason
    ) {
        super("RestockRequest", sourceEnterprise, targetEnterprise, sourceOrganization, targetOrganization, message);
        this.productId = productId;
        this.restockAmount = restockAmount;
        this.reason = reason;
    }

    @Override
    public String getBusinessPayload() {
        return "productId=" + productId + ", restock=" + restockAmount + ", reason=" + reason;
    }
}
