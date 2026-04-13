package business.workrequest;

public class FulfillmentAssignment extends WorkRequest {
    private final String orderId;
    private final String assignedTo;

    public FulfillmentAssignment(String sourceEnterprise, String targetEnterprise,
            String sourceOrganization, String targetOrganization,
            String message, String orderId, String assignedTo) {
        super("FulfillmentAssignment", sourceEnterprise, targetEnterprise,
              sourceOrganization, targetOrganization, message);
        this.orderId = orderId;
        this.assignedTo = assignedTo;
    }

    @Override
    public String getBusinessPayload() {
        return "Order: " + orderId + ", Assigned to: " + assignedTo;
    }

    public String getOrderId() { return orderId; }
    public String getAssignedTo() { return assignedTo; }
}
