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

    public boolean login(String name, String Password) {
        User user = userDao.findByName(name);
        if (user == null) {
            return true;
        }
        this.loggedUser = user;
        return true;
    }

    public void createTodo(String name) {
        int id = calender.getTodoIdCalc();
        calender.addTodoToList(new Todo(name, id));
    }

    public List getUnScheduledTodos() {
        return this.calender.getUnScheduledTodos();
    }

    public boolean scheduleTodo(String day, String time, Todo todo) {
        if (getDay(day) == false || checkTime(time) == false) {
            return false;
        }
        getDay(day);
        return calender.scheduleTodo(this.day, time, todo);
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
