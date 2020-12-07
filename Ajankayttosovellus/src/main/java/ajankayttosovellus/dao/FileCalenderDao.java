//package ajankayttosovellus.dao;
//
//import ajankayttosovellus.domain.Calender;
//import ajankayttosovellus.domain.Todo;
//import ajankayttosovellus.domain.TypeOfTime;
//import ajankayttosovellus.domain.User;
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.FileWriter;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//import java.util.Scanner;
//import java.util.TreeMap;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//
//public class FileCalenderDao implements CalenderDao {
//
//    public List<Calender> calenders;
//    private String file;
//    private final UserDao userDao;
//    private FileWriter writer;
//
//    public FileCalenderDao(String file, UserDao userDao) {
//        this.file = file;
//        createFile();
//        this.calenders = new ArrayList<>();
//        this.userDao = userDao;
//        load();
//
//    }
//
//    private void createFile() {
//        try {
//            this.writer = new FileWriter(new File(this.file));
//        } catch (Exception exception) {
//            exception.printStackTrace();
//        }
//    }
//
//    private void load() {
//        List<String> usersAndCalender = new ArrayList<>();
//        List<String> timeSlotParts = new ArrayList<>();
//        try {
//            Scanner reader = new Scanner(new File(file));
//            while (reader.hasNextLine()) {
//                usersAndCalender.add(reader.nextLine());
//                String[] parts = reader.nextLine().split(":");
//                String[] userParts = parts[0].split(",");
//                String[] timeAndTodoParts = parts[1].split(";");
//                User user = new User(userParts[0], userParts[1]);
//                Calender calender = new Calender("Calender", user);
//                TreeMap<String, Todo> calenderMap = new TreeMap<>();
//                for (int i = 0; i < timeAndTodoParts.length; i++) {
//                    String[] mapParts = timeAndTodoParts[i].split(",");
//                    String dt = mapParts[0];
//                    Todo todo = new Todo(mapParts[1],calender.getTodoIdCalc());
//                    calenderMap.put(dt, todo);
//                    calender.setCalender(calenderMap);
//                }
//                
//  
//                timeSlotParts.add(parts[1]);
//            }
//            for (int j = 0; j < timeSlotParts.size(); j++) {
//                String[] parts = timeSlotParts.get(j).split(",");
//                String time = parts[0];
//                String type = parts[1];
//
//            }
//
//        } catch (Exception exception) {
//            exception.printStackTrace();
//        }
//    }
//
//    private void saveCalenders() {
//        try {
//            for (Calender calender : calenders) {
//                String user = calender.getUser().getName() + "," + calender.getUser().getPasswod();
//                String timesAndTodos = "";
//                List<String> times = calender.calenderToList();
//                for (int i = 0; i < times.size(); i++) {
//                    timesAndTodos = timesAndTodos + times.get(i);
//                }
//                writer.write(calender.getUser().getName() + "," + calender.getUser().getPasswod() + ":" + timesAndTodos + "\n");
//
//            }
//            writer.close();
//
//        } catch (Exception exception) {
//            exception.printStackTrace();
//        }
//    }
//
//    @Override
//    public Calender create(Calender calender) {
//        calenders.add(calender);
//        saveCalenders();
//        return calender;
//    }
//
//    @Override
//    public List<Calender> getAll() {
//         return this.calenders;
//    }
//
//    public void addTodo() {
//        
//    }
//}
