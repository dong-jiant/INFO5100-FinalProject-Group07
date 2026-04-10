/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package business.order;

import java.util.ArrayList;

/**
 *
 * @author stelladong
 */
public class OrderDirectory {
     private ArrayList<Order> orderList;
    private int nextId;

    public OrderDirectory() {
        this.orderList = new ArrayList<>();
        this.nextId = 1001;
    }

    public ArrayList<Order> getOrderList() {
        return orderList;
    }

    public Order addOrder(String customerName, String customerEmail, String country) {
        Order order = new Order();
        order.setOrderId(generateOrderId());
        order.setCustomerName(customerName);
        order.setCustomerEmail(customerEmail);
        order.setCountry(country);
        order.setStatus("Created");

        orderList.add(order);
        return order;
    }

    public void removeOrder(Order order) {
        orderList.remove(order);
    }

    public Order findById(String orderId) {
        for (Order order : orderList) {
            if (order.getOrderId().equals(orderId)) {
                return order;
            }
        }
        return null;
    }

    private String generateOrderId() {
        return "ORD-" + nextId++;
    }
}
