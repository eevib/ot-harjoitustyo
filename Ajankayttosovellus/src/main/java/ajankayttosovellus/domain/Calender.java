package ajankayttosovellus.domain;

import java.util.ArrayList;
import java.util.HashMap;

public class Calender {

    Day[] week;
    String name;
    ArrayList<Todo> todos;
    HashMap<String, Day> days;

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
        this.days = new HashMap<>();
        this.days.put("ma", mon);
        this.days.put("ti", tue);
        this.days.put("ke", wed);
        this.days.put("to", tue);
        this.days.put("pe", fri);
        this.days.put("la", sat);
        this.days.put("su", sun);
    }

    public void addTodoToList(Todo todo) {
        todos.add(todo);
    }

    public boolean scheduleTodo(String day, int time, Todo todo) {
        if (!days.containsKey(day)) {
            System.out.println("Päivä ei ollut ymmärrettävä.");
            return false;
        }
        Day tododay = getDay(day);
        TimeSlot timeslot = tododay.getTimeslot(time);
        if (timeslot.isFree()) {
            timeslot.setTodo(todo);
            todo.setScheduled(true);
            todo.setTime(time);
            return true;
        }
        return false;
    }

    private Day getDay(String whatDay) {
        return days.get(whatDay);
    }

    public String todosToString() {
        return todos.toString();
    }

    public Day[] getWeek() {
        return week;
    }

    public void printTodos() {
        todos.forEach((n) -> System.out.println(n.toString()));
        
    }

    public void printScheduledTodos() {
        for (int i = 0; i < todos.size(); i++) {
            if (todos.get(i).scheduled = true) {
                System.out.println(todos.get(i).toString());
            }
            i++;
        }
    }

    @Override
    public String toString() {
        return "Calender " + this.name + " has " + this.todos.size() + " todo(s).";
    }

}
