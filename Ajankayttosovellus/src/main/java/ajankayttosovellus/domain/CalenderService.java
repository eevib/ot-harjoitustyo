package ajankayttosovellus.domain;

import java.util.List;

public class CalenderService {

    private Calender calender;
    String day;

    public CalenderService() {
        this.calender = new Calender("Calender");
        this.day = "";
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
