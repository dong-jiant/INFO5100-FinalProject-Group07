package business.workrequest;

import business.enterprise.Enterprise;
import business.organization.Organization;

public class ShipmentRequest extends WorkRequest {
    private final String orderId;
    private final String origin;
    private final String destination;
    private final String notes;

    
    public ShipmentRequest( String sourceEnterprise,
            String targetEnterprise,
            String sourceOrganization,
            String targetOrganization,
                           String orderId, String origin, String destination, String notes) {
        super("ShipmentRequest", sourceEnterprise, targetEnterprise, sourceOrganization, targetOrganization,
              "Ship order " + orderId + " from " + origin + " to " + destination);
        this.orderId = orderId;
        this.origin = origin;
        this.destination = destination;
        this.notes = notes;
    }

    @Override
    public String getBusinessPayload() {
        return "Order: " + orderId + ", " + origin + " → " + destination;
    }

    // Getter methods
    public String getOrderId() { return orderId; }
    public String getOrigin() { return origin; }
    public String getDestination() { return destination; }
    public String getNotes() { return notes; }
}