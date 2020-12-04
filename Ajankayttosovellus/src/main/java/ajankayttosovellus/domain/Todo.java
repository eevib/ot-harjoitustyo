package ajankayttosovellus.domain;

public class Todo implements TypeOfTime {

    String type;
    String name;
    int id;

    public Todo(String type, int id) {
        this.type = type;
        this.id = id;
    }
    public Integer getTodoId() {
        return this.id;
    }
    public void setName(String name) {
        this.name = name;
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

    @Override
    public String toString() {
        return "Todo: " + this.name + ". Id: " + this.id;
    }

}
