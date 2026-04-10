/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package business.order;

import java.util.ArrayList;
import java.util.Date;

public class Order {

    private String orderId;
    private String customerName;
    private String customerEmail;
    private String country;
    private String status;
    private String shipmentStatus;
    private Date orderDate;
    private ArrayList<OrderItem> items;

    public Order() {
        this.items = new ArrayList<>();
        this.orderDate = new Date();
        this.status = "Created";
        this.shipmentStatus = "Pending";
    }



    public void addItem(OrderItem item) {
        items.add(item);
    }

    public ArrayList<OrderItem> getItems() {
        return items;
    }

    public double getTotalPrice() {
        double total = 0;
        for (OrderItem item : items) {
            total += item.getTotal();
        }
        return total;
    }

    public String getRiskFlag() {

         if (getTotalPrice() > 1000) {
        return "High Value";
    }

    long days = (new Date().getTime() - orderDate.getTime()) / (1000 * 60 * 60 * 24);
    if (days > 3 && !shipmentStatus.equals("Shipped") && !shipmentStatus.equals("Delivered")) {
        return "Delay Risk";
    }

    return "Normal";
    }

  
    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public String getShipmentStatus() {
    return shipmentStatus;
}

public void setShipmentStatus(String shipmentStatus) {
    this.shipmentStatus = shipmentStatus;
}
    
    

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    // JTable 显示用
    @Override
    public String toString() {
        return orderId;
    }
}