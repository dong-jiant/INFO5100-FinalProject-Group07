/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package business.enterprise;

import business.order.OrderDirectory;

/**
 *
 * @author stelladong
 */

    public class PlatformEnterprise extends Enterprise {

    private final String platformId;
    private final String country;
    private final OrderDirectory orderDirectory;

    public PlatformEnterprise(String name, String platformId, String country) {
        super(name);
        this.platformId = platformId;
        this.country = country;
        this.orderDirectory = new OrderDirectory();
    }

    public String getPlatformId() {
        return platformId;
    }

    public String getCountry() {
        return country;
    }

    public OrderDirectory getOrderDirectory() {
        return orderDirectory;
    }
}
