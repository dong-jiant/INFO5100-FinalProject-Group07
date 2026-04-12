/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package business.enterprise;

import business.organization.Organization;
import business.user.UserAccountDirectory;
import java.util.ArrayList;

/**
 *
 * @author stelladong
 */
public class Enterprise {
    private String name;
    private ArrayList<Organization> organizations;
    private UserAccountDirectory userAccountDirectory;
 

    


    public Enterprise() {
        organizations = new ArrayList<>();
        userAccountDirectory = new UserAccountDirectory();
    }

    public Enterprise(String name) {
        this.name = name;
        organizations = new ArrayList<>();
        userAccountDirectory = new UserAccountDirectory();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Organization> getOrganizations() {
        return organizations;
    }

    public void addOrganization(Organization org) {
        organizations.add(org);
    }

    public UserAccountDirectory getUserAccountDirectory() {
        return userAccountDirectory;
    }

    public void setUserAccountDirectory(UserAccountDirectory userAccountDirectory) {
        this.userAccountDirectory = userAccountDirectory;
    }
}
