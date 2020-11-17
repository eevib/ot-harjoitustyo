package ajankayttosovellus;

public class Todo {

    String todo;
    int time;
    Boolean scheduled;
    TimeSlot timeSlot;

    public Todo(String name, int time) {
        this.todo = name;
        this.time = time;
        this.scheduled = false;
    }

    public Todo(String todo) {
        this.todo = todo;
        this.scheduled = false;

    }

    public TimeSlot getTimeSlot() {
        return this.timeSlot;
    }

    public void putTimeSlot(TimeSlot time) {
        this.timeSlot = time;
    }

    public String getTodo() {
        return todo;
    }

    public void setTodo(String todo) {
        this.todo = todo;
    }

    public Integer getTime() {
        return this.time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Todo{" + "todo=" + todo + ", time=" + time + ", scheduled=" + scheduled + '}';
    }

}
