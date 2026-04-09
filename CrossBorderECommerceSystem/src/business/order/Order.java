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
public class Order {
    private ArrayList<OrderItem> items;
    private String orderId;
    private String status;

    public Order() {
        this.items = new ArrayList<>();
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
@Override
public String toString() {
    return orderId;
}
    
}
