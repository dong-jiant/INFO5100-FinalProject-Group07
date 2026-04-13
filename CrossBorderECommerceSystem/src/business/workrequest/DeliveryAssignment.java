package business.workrequest;

public class DeliveryAssignment extends WorkRequest {
    private final String shipmentId;
    private final String deliveryStaff;

    public DeliveryAssignment(String sourceEnterprise, String targetEnterprise,
            String sourceOrganization, String targetOrganization,
            String message, String shipmentId, String deliveryStaff) {
        super("DeliveryAssignment", sourceEnterprise, targetEnterprise,
              sourceOrganization, targetOrganization, message);
        this.shipmentId = shipmentId;
        this.deliveryStaff = deliveryStaff;
    }

    @Override
    public String getBusinessPayload() {
        return "Shipment: " + shipmentId + ", Staff: " + deliveryStaff;
    }

    public String getShipmentId() { return shipmentId; }
    public String getDeliveryStaff() { return deliveryStaff; }
}
