package ajankayttosovellus.dao;

import ajankayttosovellus.domain.Calender;
import ajankayttosovellus.domain.TypeOfTime;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileCalenderDao implements CalenderDao {

    public List<Calender> calenders;
    private String file;
    private final UserDao userDao;

    public FileCalenderDao(String file, UserDao userDao) {
        this.calenders = new ArrayList<>();
        this.file = file;
        this.userDao = userDao;
        load();

    }

    private void load() {
        List<String> timeSlotParts = new ArrayList<>();
        try {
            Scanner reader = new Scanner(new File(file));
            int i = 0;
            while (reader.hasNextLine()) {
                String[] parts = reader.nextLine().split(";");
                timeSlotParts.add(parts[i]);
                i++;
            }
            for (int j = 0; j < timeSlotParts.size(); j++) {
                String[] parts = timeSlotParts.get(j).split(",");
                String time = parts[0];
                String type = parts[1];

            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileCalenderDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Calender create(Calender calender) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Calender> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
