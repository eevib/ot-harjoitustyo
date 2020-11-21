package ajankayttosovellus.domain;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.TreeMap;

public class Calender {

    String name;
    TreeMap<String, TypeOfTime> calender;
    ArrayList<Todo> todos;
    HashMap<Todo, String> todoList;
    int todoIdCalc;

    public Calender(String name) {
        this.todoIdCalc = 0;
        this.name = name;
        this.todos = new ArrayList();
        calender = new TreeMap<>();
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
                this.todos.remove(todo);
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
    
    public void printUnScheduledTodos() {
        todos.forEach((n) -> System.out.println(n.toString()));

    }

    public boolean reserveTimeSlot(String day, String time) {
        String dt = day + time;
        Reserved reserved = new Reserved();
        if (calender.containsKey(dt)) {
            if (isFree(dt)) {
                calender.put(dt, reserved);
                return true;
            }
        }
        return false;
    }

    public void printScheduledTodos() {
        this.calender.entrySet().stream()
                .filter(entry -> entry.getValue().getTypeOfTime().equals("todo"))
                .forEach(entry -> System.out.println(entry.getKey() + " " + entry.getValue().toString()));
    }

    public void printCalender() {
        this.calender.entrySet().forEach(e -> {
            System.out.println(e.getKey() + " " + e.getValue());
        });
    }

    public Todo getUnScheduledTodo(int todoId) {
        if (todos.isEmpty()) {
            return null;
        }
        for (int i = 0; i < todos.size(); i++) {
            Todo todo = todos.get(i);
            if (todo.getTodoId() == todoId) {
                return todo;
            }
        }
        return null;
    }

    public int getTodoIdCalc() {
        this.todoIdCalc++;
        return this.todoIdCalc;
    }

    @Override
    public String toString() {
        return "Calender " + this.name + " has " + this.todos.size() + " todo(s).";
    }

}
