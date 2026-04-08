/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package business.enterprise;

import business.organization.Organization;
import java.util.ArrayList;

/**
 *
 * @author stelladong
 */
public class Enterprise {
     private String name;
     private ArrayList<Organization> organizations;

    public Enterprise(String name) {
        this(name);
    }

    public Enterprise(String name) {
        this(name);
    }

    public Enterprise(String name) {
        this(name);
    }

    public Enterprise(String name) {
        this.name = name;
        this.organizations = new ArrayList<>();
    }

    public String getName() {
        return name;
    }
    public ArrayList<Organization> getOrganizations() {
        return organizations;
    }

    public void addOrganization(Organization org) {
        organizations.add(org);
    
}
}