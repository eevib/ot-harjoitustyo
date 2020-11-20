package ajankayttosovellus.domain;

public class Todo implements TypeOfTime {

    String type;
    String name;
    int time;
    Boolean scheduled;

    public Todo(String name) {
        this.type = "todo";
        this.name = name;
        this.time = time;
        this.scheduled = false;
    }

    public String getTodo() {
        return name;
    }

    public void setTodo(String todo) {
        this.name = todo;
    }

    public String getTypeOfTime() {
        return this.type;
    }

    public boolean isScheduled() {
        return this.scheduled;
    }

    public void setScheduled(boolean scheduled) {
        this.scheduled = scheduled;
    }

    @Override
    public String toString() {
        return this.name;
    }

}
