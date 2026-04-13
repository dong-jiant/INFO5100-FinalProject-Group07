/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package business.shipment;

import java.util.Date;

/**
 *
 * @author a1
 */
public class Shipment {
	
	    private String shipmentId;
	    private String orderId;
	    private String status;          // Created / Processing / Shipped / Out for Delivery / Delivered / Returned
	    private String eta;             // format: yyyy-MM-dd
	    private String origin;
	    private String destination;
	    private String carrier;         // FedEx / UPS / DHL 等
	    private String trackingNumber;
	    private String assignedDeliveryStaff;  // delivery staff username
	    private Date createdDate;
	    private Date deliveredDate;
	    private String notes;

	    // Status constants
	    public static final String STATUS_CREATED = "Created";
	    public static final String STATUS_PROCESSING = "Processing";
	    public static final String STATUS_SHIPPED = "Shipped";
	    public static final String STATUS_OUT_FOR_DELIVERY = "Out for Delivery";
	    public static final String STATUS_DELIVERED = "Delivered";
	    public static final String STATUS_RETURNED = "Returned";
		public String getShipmentId() {
			return shipmentId;
		}
		public void setShipmentId(String shipmentId) {
			this.shipmentId = shipmentId;
		}
		public String getOrderId() {
			return orderId;
		}
		public void setOrderId(String orderId) {
			this.orderId = orderId;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		public String getEta() {
			return eta;
		}
		public void setEta(String eta) {
			this.eta = eta;
		}
		public String getOrigin() {
			return origin;
		}
		public void setOrigin(String origin) {
			this.origin = origin;
		}
		public String getDestination() {
			return destination;
		}
		public void setDestination(String destination) {
			this.destination = destination;
		}
		public String getCarrier() {
			return carrier;
		}
		public void setCarrier(String carrier) {
			this.carrier = carrier;
		}
		public String getTrackingNumber() {
			return trackingNumber;
		}
		public void setTrackingNumber(String trackingNumber) {
			this.trackingNumber = trackingNumber;
		}
		public Date getCreatedDate() {
			return createdDate;
		}
		public void setCreatedDate(Date createdDate) {
			this.createdDate = createdDate;
		}
		public Date getDeliveredDate() {
			return deliveredDate;
		}
		public void setDeliveredDate(Date deliveredDate) {
			this.deliveredDate = deliveredDate;
		}
		public String getNotes() {
			return notes;
		}
		public void setNotes(String notes) {
			this.notes = notes;
		}
		public String getAssignedDeliveryStaff() {
			return assignedDeliveryStaff;
		}
		public void setAssignedDeliveryStaff(String assignedDeliveryStaff) {
			this.assignedDeliveryStaff = assignedDeliveryStaff;
		}
		@Override
		public String toString() {
			return "Shipment [shipmentId=" + shipmentId + ", orderId=" + orderId + ", status=" + status + ", eta=" + eta
					+ ", origin=" + origin + ", destination=" + destination + ", carrier=" + carrier
					+ ", trackingNumber=" + trackingNumber + ", assignedDeliveryStaff=" + assignedDeliveryStaff
					+ ", createdDate=" + createdDate + ", deliveredDate=" + deliveredDate + ", notes=" + notes + "]";
		}
	    
	    
	    
		 
    
}
