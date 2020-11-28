package ajankayttosovellus.domain;

import java.util.List;

public class CalenderService {

    private Calender calender;

    public CalenderService() {
        this.calender = new Calender("Calender");
    }

    public void createTodo(String name) {
        calender.addTodoToList(new Todo(name, calender.todoIdCalc));
    }

    public List getUnScheduledTodos() {
        return this.calender.getUnScheduledTodos();
    }

    public boolean scheduleTodo(String day, String time, Todo todo) {
        return calender.scheduleTodo(day, time, todo);
    }

    public Todo getTodo(int todoId) {
        return this.calender.getTodo(todoId);
    }

    public Todo getLastTodo() {
        return this.calender.getLastTodo();
    }
}
