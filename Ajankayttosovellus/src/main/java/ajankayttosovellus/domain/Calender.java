package ajankayttosovellus.domain;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class Calender {

    String name;
    HashMap<String, TypeOfTime> calender;
    ArrayList<Todo> todos;
    HashMap<Todo, String> todoList;

    public Calender(String name) {
        this.name = name;
        this.todos = new ArrayList();
        calender = new HashMap<>();
        this.todoList = new HashMap<>();
        String time = "";
        TypeOfTime type = new Free();
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 24; j++) {
                time = "" + i + "" + j;
                calender.put(time, type);
            }
        }
    }

    public void addTodoToList(Todo todo) {
        todos.add(todo);
    }

    public boolean scheduleTodo(String day, String time, Todo todo) {
        String dt = day + time;
        if (calender.containsKey(dt)) {
            if (isFree(dt)) {
                calender.put(dt, todo);
                return true;
            }
        }
        return false;
    }

    public boolean isFree(String dt) {
        if (calender.get(dt).getTypeOfTime().equals("free")) {
            return true;
        }
        return false;
    }

    public String todosToString() {
        return todos.toString();
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

    public void printCalender() {
        this.calender.entrySet().forEach(e -> {
            System.out.println(e.getKey() + " " + e.getValue());
        });
    }

    @Override
    public String toString() {
        return "Calender " + this.name + " has " + this.todos.size() + " todo(s).";
    }

}
