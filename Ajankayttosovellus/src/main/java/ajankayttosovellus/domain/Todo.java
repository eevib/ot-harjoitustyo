package ajankayttosovellus.domain;

public class Todo {

    String todo;
    int time;
    Boolean scheduled;

    public Todo(String name, int time) {
        this.todo = name;
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

    public Integer getTime() {
        return this.time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return this.todo;
    }

}
