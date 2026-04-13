package business.workrequest;

public class ReturnRequest extends WorkRequest {
    private final String orderId;
    private final String reason;

    public ReturnRequest(String sourceEnterprise, String targetEnterprise,
            String sourceOrganization, String targetOrganization,
            String message, String orderId, String reason) {
        super("ReturnRequest", sourceEnterprise, targetEnterprise,
              sourceOrganization, targetOrganization, message);
        this.orderId = orderId;
        this.reason = reason;
    }

    @Override
    public String getBusinessPayload() {
        return "Order: " + orderId + ", Reason: " + reason;
    }

    public String getOrderId() { return orderId; }
    public String getReason() { return reason; }
}
