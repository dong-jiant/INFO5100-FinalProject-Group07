/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package business.network;

import business.enterprise.Enterprise;
import java.util.ArrayList;

/**
 *
 * @author stelladong
 */
public class Network {
   

    private String name;
    private ArrayList<Enterprise> enterprises;

    public Network(String name) {
        this.name = name;
        this.enterprises = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public ArrayList<Enterprise> getEnterprises() {
        return enterprises;
    }

    public void addEnterprise(Enterprise enterprise) {
        enterprises.add(enterprise);
    }

    
}
