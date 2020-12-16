package ajankayttosovellus.domain;

import ajankayttosovellus.dao.CalenderDao;
import ajankayttosovellus.dao.FileUserDao;
import ajankayttosovellus.dao.UserDao;
import java.util.ArrayList;
import java.util.List;

public class CalenderService {

    private Calender calender;
    String day;
    private UserDao userDao;
    private CalenderDao calenderDao;
    private User loggedUser;

    public CalenderService(CalenderDao calenderDao, UserDao userDao) {
        this.userDao = userDao;
        this.calenderDao = calenderDao;
        this.day = "";
    }

    public boolean login(String name, String password) {
        User user = userDao.findByName(name);
        if (user == null) {
            return false;
        } else if (user.getPasswod().equals(password)) {
            this.loggedUser = user;
            this.calender = getCalender();
            return true;
        }
        return false;
    }

    public Calender getCalender() {
        List<Calender> calenders = this.calenderDao.getAll();
        for (Calender userCalender : calenders) {
            if (userCalender.getUser().equals(this.loggedUser)) {
                return userCalender;
            }
        }
        Calender newCalender = new Calender("");
        return newCalender;
    }

    public List<String> drawCalender() {
        return getCalender().calenderToList();
    }

    public boolean createUser(String name, String password) {
        if (userDao.findByName(name) != null) {
            return false;
        }
        User newUser = new User(name, password);
        try {
            userDao.create(newUser);
        } catch (Exception exception) {
            return false;
        }
        this.loggedUser = newUser;
        Calender newCalender = new Calender("Calender");
        newCalender.setUser(newUser);
        this.calenderDao.create(newCalender);
        return true;
    }

    public void createTodo(String name) {
        int id = this.calender.getTodoIdCalc();
        calender.addTodoToList(new Todo(name, id));
    }

    public List getUnScheduledTodos() {
        if (this.calender.getUnScheduledTodos() == null) {
            return null;
        }
        return this.calender.getUnScheduledTodos();
    }

    public boolean scheduleTodo(String day, String time, Todo todo) {
        if (getDay(day) == false || checkTime(time) == false) {
            return false;
        }
        getDay(day);
        Boolean schedulingIsPossible = calender.scheduleTodo(this.day, time, todo);

        return schedulingIsPossible;
    }

    public void logout() {
        this.calenderDao.saveCalender(this.calender);
    }

    public Todo getTodo(int todoId) {
        return this.calender.getTodo(todoId);
    }

    public Todo getLastTodo() {
        return this.calender.getLastTodo();
    }

    public int getCalenderSize() {
        return this.calender.getUnScheduledTodos().size();
    }

    public List getScheduledTodos() {
        return this.calender.getScheduledTodos();
    }

    private boolean checkTime(String time) {
        for (int i = 0; i < 24; i++) {
            if (time.equals("" + i)) {
                return true;
            }
        }
        return false;
    }

    private boolean getDay(String day) {
        if (day.equals("Mon")) {
            this.day = "0";
            return true;
        } else if (day.equals("Tue")) {
            this.day = "1";
            return true;
        } else if (day.equals("Wed")) {
            this.day = "2";
            return true;
        } else if (day.equals("Thu")) {
            this.day = "3";
            return true;
        } else if (day.equals("Fri")) {
            this.day = "4";
            return true;
        } else if (day.equals("Sat")) {
            this.day = "5";
            return true;
        } else if (day.equals("Sun")) {
            this.day = "6";
            return true;
        }
        return false;
    }
}
