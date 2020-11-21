package ajankayttosovellus.domain;

public class Todo implements TypeOfTime {

    String type;
    String name;
    Boolean scheduled;
    int id;

    public Todo(String name, int id) {
        this.type = "todo";
        this.name = name;
        this.id = id;
        this.scheduled = false;
        
    }
    public Integer getTodoId() {
        return this.id;
    }

    public String getTodoName() {
        return name;
    }

    public void setTodoName(String todo) {
        this.name = todo;
    }

    @Override
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
        return "Todo: " + this.name + ". Id: " + this.id;
    }

}
