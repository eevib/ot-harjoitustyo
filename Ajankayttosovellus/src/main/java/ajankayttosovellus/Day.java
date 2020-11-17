package ajankayttosovellus;

public class Day {

    TimeSlot[] day;
    String name;

    public Day(String name) {
        this.day = new TimeSlot[24];
        this.name = name;

    }

    public void putTodo(Todo todo) {
        if (this.day[todo.getTime()] == null) {
            this.day[todo.getTime()].setTodo(todo);
        }
    }
}
