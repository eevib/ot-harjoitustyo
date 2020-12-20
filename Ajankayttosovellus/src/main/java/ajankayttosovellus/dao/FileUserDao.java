package ajankayttosovellus.dao;

import ajankayttosovellus.domain.User;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileUserDao implements UserDao {

    private List<User> users;
    private String file;
    private FileWriter writer;

    /**
     * Konstruktori, jolle annetaan tiedoston nimi.
     * @param file Merkkijonona tiedoston nimi.
     * @throws Exception 
     */
    public FileUserDao(String file) throws Exception {
        this.file = file;
        this.users = new ArrayList<>();
        loadUsers();
    }
    /**
     * Lataa käyttäjät tiedostosta.
     * @throws Exception Jos tulee poikkeus, luodaan uusi tiedosto.
     */
    private void loadUsers() throws Exception {
        try {
            Scanner reader = new Scanner(new File(file));
            while (reader.hasNextLine()) {
                String[] parts = reader.nextLine().split(",");
                User user = new User(parts[0], parts[1]);
                users.add(user);
            }
        } catch (Exception exception) {
            FileWriter writer = new FileWriter(new File(this.file));
            writer.close();
        }
    }

    /**
     * Tallennetaan kaikki käyttäjät, käymällä läpi lista käyttäjistä.
     * @throws Exception Jos tämä ei onnistu tulostetaan virheviesti.
     */
    private void saveUsers() throws Exception {
        try (FileWriter writer = new FileWriter(new File(file))) {
            for (User user : users) {
                writer.write(user.getName() + "," + user.getPassword() + "\n");       
            }
            writer.close();
        } catch (Exception exception) {
            exception.printStackTrace();

        }
    }

    /**
     * Etsitään ja palautetaan käyttäjänimellä etsittävä käyttäjä. 
     * @param userName Merkkijonomuotoinen etsittävän käyttäjän nimi,
     * @return Etsittävä käyttäjä User.
     */
    @Override
    public User findByName(String userName) {
        if (users.isEmpty()) {
            return null;
        }
        return users.stream().filter(user -> user.getName().equals(userName)).findFirst().orElse(null);
    }

    /**
     * Luodaan uusi käyttäjä ja tallennetaan käyttäjät tiedostoon metodilla saveUsers().
     * @param user Tallennettava käyttäjä User.
     * @return Parametrina saatu käyttäjä palautetaan tallennuksen jälkeen. 
     */
    @Override
    public User create(User user) {
        users.add(user);
        try {
            saveUsers();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return user;
    }

    /**
     * Palautetaan lista kaikista käyttäjistä.
     * @return User muotoisen listan kaikista käyttäjistä. 
     */
    @Override
    public List<User> getAll() {
        return this.users;
    }

}
