package ajankayttosovellus;

public class TimeSlot {

    boolean free;
    int time;
    String day;
    Todo todo;

    public TimeSlot() {
        this.free = true;
    }

    public TimeSlot(int time, String day) {
        this.free = true;
        this.time = time;
        this.day = day;
    }
    public void setTodo(Todo todo) {
        this.todo = todo;
    }
    public Todo getTodo() {
        return this.todo;
    }

    public boolean isFree() {
        return free;
    }

    public void setFree(boolean free) {
        this.free = free;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }
}
