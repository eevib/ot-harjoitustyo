package ajankayttosovellus;

public class TimeSlot {

    int time;
    Todo todo;

    public TimeSlot(int time, Day day) {
        this.time = time;
    }

    public void setTodo(Todo todo) {
        this.todo = todo;
    }

    public Todo getTodo() {
        return this.todo;
    }

    public boolean isFree() {
        if (this.todo == null) {
            return true;
        }
        return false;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

}
