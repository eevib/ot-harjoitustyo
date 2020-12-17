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
    private final UserDao userDao;
    private FileWriter writer;

    public FileCalenderDao(String file, UserDao userDao) throws IOException {
        this.file = file;
        this.calenders = new ArrayList<>();
        this.userDao = userDao;
        load();
    }

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

    private void saveCalenders() {
        try ( FileWriter fileWriter = new FileWriter(new File(this.file))) {
            for (Calender calender : calenders) {
                String user = calender.getUser().getName() + "," + calender.getUser().getPasswod();
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

    @Override
    public Calender findByUser(User user) {
        for (Calender calender : calenders) {
            if (calender.getUser().getName().equals(user.getName())) {
                return calender;
            }
        }
        return null;
    }

    @Override
    public Calender create(Calender calender) {
        calenders.add(calender);
        saveCalenders();
        return calender;
    }

    @Override
    public List<Calender> getAll() {
        return this.calenders;
    }

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
