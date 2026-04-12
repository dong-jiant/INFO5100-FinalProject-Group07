package business.shipment;

import java.util.ArrayList;

public class ShipmentDirectory {
    private ArrayList<Shipment> shipmentList;
    private int nextId = 1001;

    public ShipmentDirectory() {
        this.shipmentList = new ArrayList<>();
    }

    public Shipment addShipment() {
        Shipment s = new Shipment();
        s.setShipmentId("SHP-" + nextId++);
        shipmentList.add(s);
        return s;
    }

    public void removeShipment(Shipment s) {
        shipmentList.remove(s);
    }

    public Shipment findById(String shipmentId) {
        for (Shipment s : shipmentList) {
            if (s.getShipmentId().equals(shipmentId)) return s;
        }
        return null;
    }

    public ArrayList<Shipment> getAllShipments() {
        return shipmentList;
    }
}