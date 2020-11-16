package ajankayttosovellus;

import java.util.ArrayList;

public class Calender {

    Day[] week;
    String name;
    ArrayList<Todo> todos;

    public Calender(String name) {
        Day[] week = new Day[7];
        Day mon = new Day("Monday");
        Day tue = new Day("Tuesday");
        Day wed = new Day("Wednesday");
        Day thu = new Day("Thursday");
        Day fri = new Day("Friday");
        Day sat = new Day("saturday");
        Day sun = new Day("sunday");
        this.name = name;
        week[0] = mon;
        week[1] = tue;
        week[2] = wed;
        week[3] = thu;
        week[4] = fri;
        week[5] = sat;
        week[6] = sun;
        this.todos = new ArrayList();
    }
    public void addTodo(Todo todo) {
        todos.add(todo);
    }

    @Override
    public String toString() {
        return "Calender " + this.name + " has " + this.todos.size() + " todo(s).";
    }

}
