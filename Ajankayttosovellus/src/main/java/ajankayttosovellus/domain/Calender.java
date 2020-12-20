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

    /**
     * Asettaa todoIdCalc 0, ja alustaa kalenterin ja muut tarvittavat listat ja mapit.
     * @param name Merkkijononen kalenterin nimi.
     */
    public Calender(String name) {
        this.todoIdCalc = 0;
        this.name = name;
        this.todos = new ArrayList();
        calender = new TreeMap<>();
        this.todoList = new HashMap<>();
        String time = "";
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 24; j++) {
                if (j < 10) {
                    Todo todo = new Todo(" ", 0);
                    time = "" + i + "0" + j;
                    calender.put(time, todo);
                } else {
                    Todo todo = new Todo(" ", 0);
                    time = "" + i + "" + j;
                    calender.put(time, todo);
                }

            }
        }
    }

    public void setUser(User user) {
        this.user = user;
    }
    public User getUser() {
        return this.user;
    }
    public String getCalenderName() {
        return this.name;
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

    public void setCalender(TreeMap<String, Todo> calender) {
        this.calender = calender;
    }

    /**
     * Metodi tarkistaa, jos aikaikkuna on vapaa.
     * @param dt Merkkijonomuotoinen ajanjakso, joss d on päivä 0-6 ja t aika
     * 0-23.
     * @return true jos aika on vapaa ja muuten false.
     */
    public boolean isFree(String dt) {
        if (calender.get(dt).getTodoName().equals(" ")) {
            return true;
        }
        return false;
    }

    /**
     * Metodi palauttaa listan ajattomista tehtävistä.
     * @return ajastamattomien todoiden lista.
     */
    public List getUnScheduledTodos() {
        if (this.todos.isEmpty()) {
            return null;
        }
        return this.todos;
    }

    /** 
     * Metodi palauttaa todon.
     * @param todoId kokonaisluku todon id.
     * @return Todo jos id:llä löytyy sellainen, muuten palauttaa null.
     */
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

    /**
     * Varaa ajan lisäämällä Reserved nimisen Todon pyydettyyn ajankohtaan.
     * @param day Päivä, jolle aika varataan
     * @param time Aika johon aika varataan.
     * @return True jos ajan varaaminen onnistuu, muuten false.
     */
    public boolean reserveTimeSlot(String day, String time) {
        String dt = day + time;
        Todo todo = new Todo("Reserved", this.todoIdCalc);
        if (calender.containsKey(dt)) {
            if (isFree(dt)) {
                calender.put(dt, todo);
                return true;
            }
        }
        return false;
    }

    /**
     * Metodi palauttaa merkkimuotoisen listan kalenterin todoista.
     * @return Merkkijonomuuotoisen listan kalentereiden todoista.
     */
    public List<String> calenderToList() {
        ArrayList<String> calenderList = new ArrayList<>();
        this.calender.entrySet().forEach(e -> {
            calenderList.add(e.getValue().getTodoName());
        });
        return calenderList;
    }

    /**
     * Metodi palauttaa listan ajastetuista todoista.
     * @return Palauttaa listan ajastetuista todoista 
     */
    public List<Todo> getScheduledTodos() {
        List<Todo> scheduledTodos = new ArrayList<>();
        scheduledTodos.addAll(this.todoList.keySet());
        return scheduledTodos;
    }

    /**
     * Metodi kasvattaa todoIdCalc, eli todoiden yksilöivän koodin yhdellä ja palauttaa tämän.
     * @return Kokonaisluku todoIdCalc
     */
    public int getTodoIdCalc() {
        this.todoIdCalc++;
        return this.todoIdCalc;
    }
/**
 * Metodi etsii listasta viimeiseksi tallennetun Todon ja palauttaa sen.
 * @return Viimeiseksi tallennettu Todo palautetaan. 
 */
    public Todo getLastTodo() {
        return this.todos.get(todos.size() - 1);
    }

}
