/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package business.user;

/**
 *
 * @author stelladong
 */
public class UserAccount {
    private String username;
    private String password;
    private Person person;

    public UserAccount(String username, String password) {
        this.username = username;
        this.password = password;
        this.person = person;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
    
    public Person getPerson() {
        return person;
    }
}
