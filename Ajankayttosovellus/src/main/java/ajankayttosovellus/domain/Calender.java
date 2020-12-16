package ajankayttosovellus.domain;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class Calender {

    String name;
    TreeMap<String, Todo> calender;
    ArrayList<Todo> todos;
    HashMap<Todo, String> todoList;
    int todoIdCalc;
    User user;

    public Calender(String name) {
        this.user = user;
        this.todoIdCalc = 0;
        this.name = name;
        this.todos = new ArrayList();
        calender = new TreeMap<>();
        this.todoList = new HashMap<>();
        String time = "";
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 24; j++) {
                if (j < 10) {
                    Todo todo = new Todo("Free", 0);
                    time = "" + i + "0" + j;
                    calender.put(time, todo);
                } else {
                    Todo todo = new Todo("Free", 0);
                    time = "" + i + "" + j;
                    calender.put(time, todo);
                }

            }
        }
    }

    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Metodi lisää uuden todon ajastamattomien tehtävien listaan.
     *
     * @param todo Anneteaan todo eli tehtävä.
     */
    public void addTodoToList(Todo todo) {
        todos.add(todo);
    }

    /**
     * Metodi ajastaa todon.
     *
     * @param day Aika annetaan numerona 0-6, josta 0 on ma ja 6 su, syöte
     * kuitenkin merkkijonona.
     * @param time Aika annetaan kokonaislukuna 0-23, syöte merkkijonona.
     * @param todo Tehtävä annetaan todona.
     * @return Palautetetaan true jos ajastaminen onnistui ja false, jos
     * ajastaminen ei onnistunut, joko väärän syötteen vuoksi tai aika oli jo
     * varattu.
     */
    public boolean scheduleTodo(String day, String time, Todo todo) {
        int t = Integer.parseInt(time);
        if (t < 10) {
            time = "0" + time;
        }
        String dt = day + time;
        if (calender.containsKey(dt)) {
            if (isFree(dt)) {
                calender.put(dt, todo);
                this.todos.remove(todo);
                this.todoList.put(todo, dt);
                return true;
            }
        }
        return false;
    }

    public Map getCalender() {
        return this.calender;
    }

    public User getUser() {
        return this.user;
    }

    public void setCalender(TreeMap<String, Todo> calender) {
        this.calender = calender;
    }

    /**
     * Metodi tarkistaa, jos aikaikkuna on vapaa.
     *
     * @param dt Merkkijonomuotoinen ajanjakso, joss d on päivä 0-6 ja t aika
     * 0-23.
     * @return true jos aika on vapaa ja muuten false.
     */
    public boolean isFree(String dt) {
        if (calender.get(dt).getTodoName().equals("Free")) {
            return true;
        }
        return false;
    }

    public List getUnScheduledTodos() {
        if (this.todos.isEmpty()) {
            return null;
        }
        return this.todos;
    }

    public Todo getTodo(int todoId) {
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

    public boolean reserveTimeSlot(String day, String time) {
        String dt = day + time;
        // Reserved reserved = new Reserved("Reserved");
        Todo todo = new Todo("Reserved", this.todoIdCalc);
        if (calender.containsKey(dt)) {
            if (isFree(dt)) {
                calender.put(dt, todo);
                return true;
            }
        }
        return false;
    }

    public void printScheduledTodos() {
        this.calender.entrySet().stream()
                .filter(entry -> entry.getValue().getTodoName().equals("todo"))
                .forEach(entry -> System.out.println(entry.getKey() + " " + entry.getValue().toString()));
    }

    public List<String> calenderToList() {
        ArrayList<String> calenderList = new ArrayList<>();
        this.calender.entrySet().forEach(e -> {
            calenderList.add(e.getValue().getTodoName());
        });
        return calenderList;
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

    public List<Todo> getScheduledTodos() {
        List<Todo> scheduledTodos = new ArrayList<>();
        scheduledTodos.addAll(this.todoList.keySet());
        return scheduledTodos;
    }

    public int getTodoIdCalc() {
        this.todoIdCalc++;
        return this.todoIdCalc;
    }

    public Todo getLastTodo() {
        return this.todos.get(todos.size() - 1);
    }

    @Override
    public String toString() {
        return "Calender " + this.name + " has " + this.todos.size() + " todo(s).";
    }

}
