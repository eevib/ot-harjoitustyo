package ajankayttosovellus.domain;

public class TimeSlot {

    int time;
    Todo todo;
    boolean isFree;

    public TimeSlot(int time) {
        this.time = time;
        this.isFree = true;
    }

    public void setTodo(Todo todo) {
        this.todo = todo;
        this.isFree = false;
    }

    public Todo getTodo() {
        return this.todo;
    }

    public boolean isFree() {
        if (this.isFree) {
            return true;
        }
        return false;
    }
    public void reserve() {
        this.isFree = false;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "TimeSlot{" + "time=" + time + ", todo=" + todo + '}';
    }

}
