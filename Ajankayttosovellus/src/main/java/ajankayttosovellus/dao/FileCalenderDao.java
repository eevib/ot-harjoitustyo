package ajankayttosovellus.dao;

import ajankayttosovellus.domain.Calender;
import ajankayttosovellus.domain.Todo;
import ajankayttosovellus.domain.User;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileCalenderDao implements CalenderDao {

    public List<Calender> calenders;
    private String file;
    private FileWriter writer;

    /**
     * Konstruktori, jolle annetaan tiedoston nimi merkkijonona.
     *
     * @param file merkkijonona tiedoston nimi, johon tallennetaan.
     * @throws IOException
     */
    public FileCalenderDao(String file) throws IOException {
        this.file = file;
        this.calenders = new ArrayList<>();
        load();
    }

    /**
     * Lataa tiedostosta kalenterit ja tallentaa ne kalentereina.
     *
     * @throws IOException Jos kalentereita ei ole olemassa luodaan uusi
     * tiedosto.
     */
    private void load() throws IOException {
        List<String> usersAndCalender = new ArrayList<>();
        List<String> timeSlotParts = new ArrayList<>();
        try {
            Scanner reader = new Scanner(new File(file));
            while (reader.hasNextLine()) {
                usersAndCalender.add(reader.nextLine());
            }
        } catch (Exception exception) {
            FileWriter writer = new FileWriter(new File(this.file));
            writer.close();
        }

        for (int i = 0; i < usersAndCalender.size(); i++) {
            String[] parts = usersAndCalender.get(i).split(":");
            String[] userParts = parts[0].split(",");
            User user = new User(userParts[0], userParts[1]);
            Calender calender = new Calender(user.getName() + "'s calender");
            calender.setUser(user);
            String[] timeAndTodoParts = parts[1].split(";");
            TreeMap<String, Todo> calenderMap = new TreeMap<>();
            int l = 0;
            for (int j = 0; j < 7; j++) {
                for (int k = 0; k < 24; k++) {
                    String dt = "" + j + k;
                    if (k < 10) {
                        dt = j + "0" + k;
                    } else {
                        dt = "" + j + k;
                    }
                    Todo todo = new Todo(timeAndTodoParts[l], 0);
                    calenderMap.put(dt, todo);
                    l++;
                }
            }
            calender.setCalender(calenderMap);
            this.calenders.add(calender);
        }
    }

    /**
     * Käy läpi kalenterit listasta ja kirjoittaa ne tiedostoon.
     */
    private void saveCalenders() {
        try (FileWriter fileWriter = new FileWriter(new File(this.file))) {
            for (Calender calender : calenders) {
                String user = calender.getUser().getName() + "," + calender.getUser().getPassword();
                List<String> times = calender.calenderToList();
                String timesAndTodos = times.get(0);
                for (int i = 1; i < times.size(); i++) {
                    timesAndTodos = timesAndTodos + ";" + times.get(i);
                }
                fileWriter.write(user + ":" + timesAndTodos + "\n");
            }
            fileWriter.close();

        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    /**
     * Käy läpi kalenterilistan ja etsii siellä User parametrina saadun
     * käyttäjän ja palauttaa käyttäjän kalenterin.
     *
     * @param user User muotoinen käyttäjä, jonka kalenteri etsii.
     * @return Annetun käyttäjän kalenterin, jos sellainen on olemassa, muuten
     * palauttaa null.
     */
    @Override
    public Calender findByUser(User user) {
        for (Calender calender : calenders) {
            if (calender.getUser().getName().equals(user.getName())) {
                return calender;
            }
        }
        return null;
    }

    /**
     * Luo uuden kalenterin ja tallentaa kalenterit tiedostoon kutsumalla
     * metodia saveCalenders().
     *
     * @param calender Calender kalenteri joka luodaan.
     * @return Palauttaa saman kalenterin, joka annetaan tallennettavaksi.
     */
    @Override
    public Calender create(Calender calender) {
        calenders.add(calender);
        saveCalenders();
        return calender;
    }

    /**
     * Palauttaa listan kaikista tallennetuista kalentereista.
     *
     * @return Calender muotoisen listan kaikista kalentereista.
     */
    @Override
    public List<Calender> getAll() {
        return this.calenders;
    }

    /**
     * Tallentaa / päivittää kalenterin.
     *
     * @param calender Calender kalenteri, joka tallennetaan, tai päivitetään,
     * jos kallenteri on jo olemassa.
     * @return parametrina annettu kaleteri palautetaan onnistuneen tallennuksen
     * jälkeen.
     */
    @Override
    public Calender saveCalender(Calender calender) {
        Calender c = findByUser(calender.getUser());
        if (c == null) {
            this.calenders.add(calender);
            saveCalenders();
        } else {
            c = calender;
            saveCalenders();
        }

        return calender;
    }
}
