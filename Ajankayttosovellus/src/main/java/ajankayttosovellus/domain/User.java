
package ajankayttosovellus.domain;

public class User {
    private String name;
    private String password;
    
    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }
    public String getName() {
        return this.name;
    }
    public String getPasswod() {
        return this.password;
    }
    public String toString() {
        return "User: " + this.name;
    }
}
