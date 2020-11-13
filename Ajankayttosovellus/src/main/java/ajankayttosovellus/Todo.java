package ajankayttosovellus;

public class Todo {

    String todo;
    String time;
    Boolean scheduled;

    public Todo(String todo, String time) {
        this.todo = todo;
        this.time = time;
        this.scheduled = false;
    }

    public Todo(String todo) {
        this.todo = todo;
        this.scheduled = false;

    }

    public String getTodo() {
        return todo;
    }

    public void setTodo(String todo) {
        this.todo = todo;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

}
