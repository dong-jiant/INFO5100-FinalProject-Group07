package business.enterprise;


import business.shipment.ShipmentDirectory;
import business.workrequest.WorkRequestDirectory;

public class LogisticsEnterprise extends Enterprise {
    private final String logisticsId;
    private final String country;
    private final ShipmentDirectory shipmentDirectory;
    private final WorkRequestDirectory workRequestDirectory;

    public LogisticsEnterprise(String name, String logisticsId, String country) {
        super(name);
        this.logisticsId = logisticsId;
        this.country = country;
        this.shipmentDirectory = new ShipmentDirectory();
        this.workRequestDirectory = new WorkRequestDirectory();
    }

    // Getter methods
    public String getLogisticsId() { return logisticsId; }
    public String getCountry() { return country; }
    public ShipmentDirectory getShipmentDirectory() { return shipmentDirectory; }
    public WorkRequestDirectory getWorkRequestDirectory() { return workRequestDirectory; }
}