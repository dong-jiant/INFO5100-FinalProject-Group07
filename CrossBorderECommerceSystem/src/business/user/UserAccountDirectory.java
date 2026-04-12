/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package business.user;

import java.util.ArrayList;

/**
 *
 * @author stelladong
 */
public class UserAccountDirectory {
     private ArrayList<UserAccount> userAccountList;

    public UserAccountDirectory() {
        userAccountList = new ArrayList<>();
    }

    public ArrayList<UserAccount> getUserAccountList() {
        return userAccountList;
    }

    public UserAccount addUserAccount(String username, String password, String role, Person person) {
        UserAccount userAccount = new UserAccount(username, password, role);
        userAccount.setPerson(person);
        userAccountList.add(userAccount);
        return userAccount;
    }

    public void deleteUserAccount(UserAccount userAccount) {
        userAccountList.remove(userAccount);
    }
    
    
    
    
}
