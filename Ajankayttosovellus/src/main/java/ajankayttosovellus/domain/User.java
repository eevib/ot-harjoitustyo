
package ajankayttosovellus.domain;

public class User {
    private String name;
    private String password;
    
    /**
     * Konstruktori.
     * @param name Merkkijonona nimi.
     * @param password Merkkijonona salasana.
     */
    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }
    public String getName() {
        return this.name;
    }
    public String getPassword() {
        return this.password;
    }
    
    /**
     * Käyttäjän tulsotusmetodi.
     * @return "User: " ja käyttäjän nimen.
     */
    @Override
    public String toString() {
        return "User: " + this.name;
    }
}
