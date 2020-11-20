package ajankayttosovellus.domain;

import java.util.ArrayList;

public class Calender {

    Day[] week;
    String name;
    ArrayList<Todo> todos;

    public Calender(String name) {
        this.week = new Day[7];
        Day mon = new Day("Monday");
        Day tue = new Day("Tuesday");
        Day wed = new Day("Wednesday");
        Day thu = new Day("Thursday");
        Day fri = new Day("Friday");
        Day sat = new Day("saturday");
        Day sun = new Day("sunday");
        this.name = name;
        this.week[0] = mon;
        this.week[1] = tue;
        this.week[2] = wed;
        this.week[3] = thu;
        this.week[4] = fri;
        this.week[5] = sat;
        this.week[6] = sun;
        this.todos = new ArrayList();
    }

    public void addTodoToList(Todo todo) {
        todos.add(todo);
    }

    public boolean scheduleTodo(String day, int time, Todo todo) {
        Day tododay = getDay(day);
        TimeSlot timeslot = tododay.getTimeslot(time);
        if (timeslot.isFree()) {
            timeslot.setTodo(todo);
            return true;
        }
        return false;
    }

    private Day getDay(String whatDay) {
        if (whatDay.contains("ma")) {
            return this.week[0];
        } else if (whatDay.equals("ti")) {
            return this.week[1];
        } else if (whatDay.equals("ke")) {
            return this.week[2];
        } else if (whatDay.equals("to")) {
            return this.week[3];
        } else if (whatDay.equals("pe")) {
            return this.week[4];
        } else if (whatDay.equals("la")) {
            return this.week[5];
        } else if (whatDay.equals("su")) {
            return this.week[6];
        }
        return this.week[0];
    }

    public String todosToString() {
        return todos.toString();
    }

    public Day[] getWeek() {
        return week;
    }

    @Override
    public String toString() {
        return "Calender " + this.name + " has " + this.todos.size() + " todo(s).";
    }

}
